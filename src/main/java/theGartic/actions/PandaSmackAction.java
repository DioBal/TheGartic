package theGartic.actions;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.mod.stslib.patches.ColoredDamagePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theGartic.summons.CrazyPanda;

import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.att;

public class PandaSmackAction extends AbstractGameAction {
    private static final float DURATION = 1.0F;
    private float thunkTiming;
    private boolean thunkEffect;
    private CrazyPanda panda;
    private float targetX;
    private float targetY;
    private float sourceX;
    private float sourceY;

    public PandaSmackAction(CrazyPanda panda) {
        this.panda = panda;
        amount = panda.passiveAmount;
        actionType = ActionType.DAMAGE;
        duration = DURATION;
        thunkEffect = false;
        sourceX = panda.cX;
        sourceY = panda.cY;
    }

    public void update() {
        if (duration == DURATION) {
            target = AbstractDungeon.getRandomMonster();
            if (target == null) {
                isDone = true;
                return;
            }
            targetX = target.hb.cX;
            targetY = target.hb.cY;
            thunkTiming = (targetX - sourceX)/3200.0f;
            if (thunkTiming > 0.25f)
                thunkTiming = 0.25f;
            if (thunkTiming < 0.1f)
                thunkTiming = 0.1f;

            panda.startShoot();
            targetX = target.hb.cX + AbstractDungeon.miscRng.random(-25.0f*Settings.xScale, 25.0f*Settings.xScale);
            targetY = target.hb.cY + AbstractDungeon.miscRng.random(-25.0f*Settings.yScale, 25.0f*Settings.yScale);
            sourceX = panda.cX;
            sourceY = panda.cY;
        }

        panda.cX = sourceX + (targetX - sourceX)*(DURATION - duration)/ thunkTiming;
        panda.cY = sourceY + (targetY - sourceY)*(DURATION - duration)/ thunkTiming;

        if (duration <= DURATION - thunkTiming && !thunkEffect) {
            thunkEffect = true;
            panda.cX = targetX;
            panda.cY = targetY;
            if (target != null && target.currentHealth > 0 && adp() != null) {
                int x = AbstractDungeon.miscRng.random(0, 1);
                AbstractPower pow;
                if (x == 0) {
                    pow = new VulnerablePower(target, 1, false);
                    ReflectionHacks.setPrivate(pow, VulnerablePower.class, "justApplied", true);
                } else
                    pow = new WeakPower(target, 1, false);
                ApplyPowerAction action =  new ApplyPowerAction(target, adp(), pow);
                ReflectionHacks.setPrivate(action, AbstractGameAction.class, "duration", 0);
                ReflectionHacks.setPrivate(action, ApplyPowerAction.class, "startingDuration", 0);
                att(action);
                DamageAction damageAction = new DamageAction(target, new DamageInfo(adp(), panda.passiveAmount, DamageInfo.DamageType.NORMAL),
                        AttackEffect.BLUNT_HEAVY, true);
                ColoredDamagePatch.DamageActionColorField.rainbow.set(damageAction, true);
                att(damageAction);
            }
            panda.startBounce(targetX, targetY);
            isDone = true;
        }

        tickDuration();
    }
}
