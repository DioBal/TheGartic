package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGartic.powers.PlayerFlightPower;

import java.util.ArrayList;

public class RemoveRandomDebuffAction extends AbstractGameAction {

    int numToRemove;
    public RemoveRandomDebuffAction() {
        actionType = ActionType.REDUCE_POWER;
        startDuration = duration = Settings.ACTION_DUR_XFAST;
        numToRemove = 1;
    }
    public RemoveRandomDebuffAction(int i) {
        actionType = ActionType.REDUCE_POWER;
        startDuration = duration = Settings.ACTION_DUR_XFAST;
        numToRemove = i;
    }

    @Override
    public void update() {
        ArrayList<AbstractPower> powerList = new ArrayList();
        for (int i = 0; i<numToRemove; i++) {
            for (AbstractPower p : AbstractDungeon.player.powers)
                if (p.type.equals(AbstractPower.PowerType.DEBUFF)) {
                    powerList.add(p);
                }
            int toRemove = AbstractDungeon.cardRng.random(0, powerList.size() - 1);
            addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, powerList.remove(toRemove).ID));
        }
        tickDuration();
    }
}
