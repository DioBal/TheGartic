package theGartic.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import basemod.interfaces.AlternateCardCostModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGartic.GarticMod;
import theGartic.actions.ConsumeFishAction;
import theGartic.cards.fish.AbstractFishCard;

import static theGartic.util.Wiz.atb;

public class ConsumeMod extends AbstractCardModifier implements AlternateCardCostModifier {

    public static final String ID = GarticMod.makeID("ConsumeMod");
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public ConsumeMod() {
        this.priority = -1; //make it more likely to go first
    }

    @Override
    public boolean prioritizeAlternateCost(AbstractCard card) {
        return true;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ConsumeMod();
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public boolean shouldApply(AbstractCard card) {
        return card.costForTurn >= 0 && !CardModifierManager.hasModifier(card, ID);
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return TEXT[0] + rawDescription;
    }

    @Override
    public int getAlternateResource(AbstractCard card) {
        int fishCount = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof AbstractFishCard) {
                fishCount++;
            }
        }
       return fishCount;
    }

    @Override
    public boolean canSplitCost(AbstractCard card) {
        return true;
    }

    @Override
    public int spendAlternateCost(AbstractCard card, int costToSpend) {
        int fishCount = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof AbstractFishCard) {
                fishCount++;
            }
        }

        int resource = fishCount;
        int numFishConsumed = 0;
        if (resource > costToSpend) {
            atb(new ConsumeFishAction(costToSpend));
            numFishConsumed = costToSpend;
            costToSpend = 0;
        } else if (resource > 0) {
            atb(new ConsumeFishAction(resource));
            numFishConsumed = resource;
            costToSpend -= resource;
        }
        if (numFishConsumed > 0 && card instanceof ConsumeCallbackInterface) {
            ((ConsumeCallbackInterface) card).numCardsUsedToConsume(numFishConsumed);
        }
        return costToSpend;
    }
}
