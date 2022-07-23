package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;

import static theGartic.GarticMod.makeID;

public class DivineAdventAction extends AbstractGameAction {
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("DivineAdvent"));
    public static final String[] TEXT = uiStrings.TEXT;
    private boolean retrieveCard;

    public DivineAdventAction() {
        actionType = ActionType.CARD_MANIPULATION;
        duration = startDuration = Settings.ACTION_DUR_FAST;
        retrieveCard = false;
    }

    public void update() {
        ArrayList<AbstractCard> generatedCards = generateCardChoices();

        if (duration == startDuration) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, TEXT[0], false);
            tickDuration();
        } else {
            if (!retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    if (AbstractDungeon.player.hasPower("MasterRealityPower"))
                        disCard.upgrade();

                    disCard.current_x = -1000.0F * Settings.xScale;

                    if (AbstractDungeon.player.hand.size() < 10)
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard,
                                (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    else
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard,
                                (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));

                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }

                retrieveCard = true;
                isDone = true;
            }

            tickDuration();
        }
    }

    private ArrayList<AbstractCard> generateCardChoices() {
        ArrayList<AbstractCard> cards = new ArrayList<>();
        cards.add(new Smite());
        cards.add(new Safety());

        return cards;
    }
}