package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGartic.cards.ExtendoArm;

import static theGartic.util.Wiz.adp;

public class ExtendoArmAction extends AbstractGameAction {
    private AbstractPlayer p;

    public ExtendoArmAction() {
        p = adp();
        setValues(null, p, amount);
        actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        duration = Settings.ACTION_DUR_FASTER;
    }

    public void update() {
        if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
            isDone = true;
            return;
        }
        if (duration == Settings.ACTION_DUR_FASTER) {
            if (p.discardPile.isEmpty()) {
                isDone = true;
                return;
            }
            if (p.discardPile.size() == 1) {
                AbstractCard tmp = p.discardPile.getTopCard();
                p.discardPile.removeCard(tmp);
                p.discardPile.moveToBottomOfDeck(tmp);
            }
            if (p.discardPile.group.size() > amount) {
                AbstractDungeon.gridSelectScreen.open(p.discardPile, 1, ExtendoArm.EXTENDO_ACTION_STRING,
                        false, false, false, false);
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                p.discardPile.removeCard(c);
                p.discardPile.moveToBottomOfDeck(c);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
        }
        tickDuration();
    }
}
