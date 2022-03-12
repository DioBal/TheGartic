package theGartic.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theGartic.GarticMod;

import static theGartic.util.Wiz.*;

public class SummonCostRefundPower extends AbstractEasyPower {
    public static String POWER_ID = GarticMod.makeID("SummonCostRefund");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SummonCostRefundPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        super.onAfterUseCard(card, action);
        att(new RemoveSpecificPowerAction(adp(), adp(), this));
        if (card.hasTag(GarticMod.Enums.SUMMON))
            att(new GainEnergyAction(amount));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}