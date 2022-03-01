package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGartic.powers.PlayerFlightPower;

public class LoseWeightAction extends AbstractGameAction {
	public static boolean activated = false;
	public static final String magicPrefix = "$#_#$";

	public LoseWeightAction() {
		actionType = ActionType.DISCARD;
		startDuration = duration = Settings.ACTION_DUR_XFAST;
	}

	@Override
	public void update() {
		if (duration == startDuration) {
			if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
				isDone = true;
				return;
			}

			if (AbstractDungeon.player.hand.group.stream().anyMatch(c -> c.color == AbstractCard.CardColor.COLORLESS)) {
				AbstractDungeon.handCardSelectScreen.open(magicPrefix + DiscardAction.TEXT[0], 99, true, true);
				tickDuration();
			} else {
				isDone = true;
			}
			return;
		}

		if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
			activated = false;
			int count = 0;
			for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
				AbstractDungeon.player.hand.moveToDiscardPile(c);
				c.triggerOnManualDiscard();
				GameActionManager.incrementDiscard(false);
				count++;
			}
			AbstractDungeon.player.hand.refreshHandLayout();

			AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
			AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();

			if (count > 0) {
				addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PlayerFlightPower(AbstractDungeon.player, AbstractDungeon.player, count)));
			}
		}
		tickDuration();
	}
}
