package theGartic.actions;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theGartic.summons.CrazyPanda;

import static theGartic.util.Wiz.*;

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
        target = AbstractDungeon.getRandomMonster();
        this.panda = panda;
        amount = panda.passiveAmount;
        actionType = ActionType.DAMAGE;
        duration = DURATION;
        thunkEffect = false;
        targetX = target.hb.cX;
        targetY = target.hb.cY;
        sourceX = panda.cX;
        sourceY = panda.cY;
        thunkTiming = (targetX - sourceX)/1600.0f;
        if (thunkTiming > 0.5f)
            thunkTiming = 0.5f;
        if (thunkTiming < 0.1f)
            thunkTiming = 0.1f;
    }

    public void update() {
        if (target == null) {
            isDone = true;
            return;
        }

        if (duration == DURATION) {
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
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(targetX, targetY, AttackEffect.BLUNT_HEAVY));
            if (target != null && target.currentHealth > 0 && adp() != null) {
                int x = AbstractDungeon.miscRng.random(0, 1);
                if (x == 0) {
                    VulnerablePower pow = new VulnerablePower(target, 1, false);
                    ReflectionHacks.setPrivate(pow, VulnerablePower.class, "justApplied", true);
                    att (new ApplyPowerAction(target, adp(), pow, 1, true));
                } else
                    att (new ApplyPowerAction(target, adp(), new WeakPower(target, 1, false),
                            1, true));
                att(new DamageAction(target, new DamageInfo(adp(), panda.passiveAmount, DamageInfo.DamageType.THORNS),
                        AttackEffect.BLUNT_HEAVY, true));
                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                }
            }
            panda.startBounce(targetX, targetY);
            isDone = true;
        }

        tickDuration();
    }
}
