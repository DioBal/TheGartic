package theGartic.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.GamblingChipAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theGartic.util.Wiz;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class GambleNextTurnPower extends AbstractEasyPower implements NonStackablePower {
    public static final String POWER_ID = makeID(GambleNextTurnPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public GambleNextTurnPower() {
        super(NAME, PowerType.BUFF, false, Wiz.adp(), -1);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        atb(new GamblingChipAction(owner, true));
        atb(new RemoveSpecificPowerAction(owner, owner, this));
    }
}
