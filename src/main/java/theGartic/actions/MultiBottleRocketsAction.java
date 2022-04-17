package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.handCardSelectScreen;

public class MultiBottleRocketsAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private final AbstractCard card;

    public MultiBottleRocketsAction(AbstractCard c) {
        setValues(AbstractDungeon.player, AbstractDungeon.player);
        card = c;
        actionType = ActionType.DAMAGE;
        duration = startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (duration == startDuration) {
            handCardSelectScreen.open(TEXT[0], 99, true, true);
            tickDuration();
        } else {
            if (!handCardSelectScreen.wereCardsRetrieved) {
                int discarded = handCardSelectScreen.selectedCards.group.size();

                if (discarded > 0) {
                    for (AbstractCard c : handCardSelectScreen.selectedCards.group) {
                        AbstractDungeon.player.hand.moveToDiscardPile(c);
                        GameActionManager.incrementDiscard(false);
                        c.triggerOnManualDiscard();
                    }
                }

                addToTop(new WaitAction(Settings.POST_ATTACK_WAIT_DUR));

                for (int i = 0; i < discarded + card.magicNumber; i++) {
                    addToTop(new AttackDamageRandomEnemyAction(card, AttackEffect.FIRE));
                }

                handCardSelectScreen.wereCardsRetrieved = true;
            }

            tickDuration();
        }
    }
}
