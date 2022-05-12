package theGartic.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.atb;

public class LoseWrathStancePower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("LoseWrathStancePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public LoseWrathStancePower(AbstractCreature owner, int amount) {
        super(NAME, PowerType.DEBUFF, false, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean IsPlayer) {
        if (amount == 1)
            atb(new ChangeStanceAction("Neutral"));
        atb(new ReducePowerAction(adp(), adp(), this, 1));
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}
