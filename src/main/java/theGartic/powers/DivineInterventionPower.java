package theGartic.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.*;

public class DivineInterventionPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID(DivineInterventionPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final int BLOCK_AMOUNT = 15;

    public DivineInterventionPower(AbstractCreature owner, int amount) {
        super(NAME, PowerType.BUFF, false, owner, amount);
    }

    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

    public void onTrigger() {
        adp().currentHealth = 0;
        adp().heal(1, true);
        att(new GainBlockAction(adp(), BLOCK_AMOUNT));
        applyToSelfTop(new DivinityNextTurnPower(adp(), 1));
        att(new ReducePowerAction(adp(), adp(), this, 1));
    }
}
