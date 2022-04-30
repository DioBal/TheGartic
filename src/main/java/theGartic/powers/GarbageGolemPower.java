package theGartic.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theGartic.GarticMod;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.atb;

public class GarbageGolemPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID(GarbageGolemPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public GarbageGolemPower(AbstractCreature owner) {
        super(NAME, PowerType.BUFF, false, owner, -1);
    }

    public void stackPower(int stackAmount) {
    }

    @Override
    public void onVictory() {
        GarticMod.garbageBlock = adp().currentBlock;
    }

    @Override
    public void atEndOfRound() {
        atb(new RemoveSpecificPowerAction(owner, owner, this));
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}