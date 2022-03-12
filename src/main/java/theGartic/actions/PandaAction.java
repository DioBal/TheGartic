package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.summons.CrazyPanda;

import static theGartic.util.Wiz.*;

public class PandaAction extends AbstractGameAction {
    private static final float DURATION = 0.15F;
    private CrazyPanda panda;

    public PandaAction(CrazyPanda panda) {
        this.panda = panda;

        target = AbstractDungeon.getRandomMonster();
        actionType = ActionType.DAMAGE;
        duration = DURATION;
    }

    public void update() {
        if (!shouldCancelAction() && target != null && target instanceof AbstractMonster) {
            att(new PandaSmackAction((AbstractMonster) target, panda));
            isDone = true;

        } else
            isDone = true;
    }
}