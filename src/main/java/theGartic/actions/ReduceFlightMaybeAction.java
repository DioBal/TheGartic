package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGartic.powers.HoverPower;
import theGartic.powers.PlayerFlightPower;

import static theGartic.util.Wiz.*;

public class ReduceFlightMaybeAction extends AbstractGameAction {
    public ReduceFlightMaybeAction() {
    }

    @Override
    public void update() {
        if (adp().hasPower(PlayerFlightPower.POWER_ID) && !adp().hasPower(HoverPower.POWER_ID)) {
            AbstractPower pow = adp().getPower(PlayerFlightPower.POWER_ID);
            att(new ReducePowerAction(adp(), adp(), pow, 1));
        }
        isDone = true;
    }
}
