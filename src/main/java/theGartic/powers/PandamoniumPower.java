package theGartic.powers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGartic.GarticMod;
import theGartic.actions.SummonOrbAction;
import theGartic.summons.CrazyPanda;

import static theGartic.util.Wiz.*;

public class PandamoniumPower extends AbstractEasyPower {
    public static String POWER_ID = GarticMod.makeID("Pandamonium");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PandamoniumPower(int amount, int amount2) {
        super(POWER_ID, AbstractPower.PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
        isTwoAmount = true;
        this.amount2 = amount2;
    }

    @Override
    public void atStartOfTurn() {
        atb(new SummonOrbAction(new CrazyPanda(amount)));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}