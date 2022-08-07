package theGartic.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.stances.DivinityStance;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.atb;

public class DivinityNextTurnPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID(DivinityNextTurnPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DivinityNextTurnPower(AbstractCreature owner, int amount) {
        super(NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        atb(new ChangeStanceAction(DivinityStance.STANCE_ID));
        atb(new ReducePowerAction(adp(), adp(), this, 1));
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
