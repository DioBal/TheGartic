package theGartic.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

import static theGartic.util.Wiz.*;

public class HungryFoxAction extends AbstractGameAction {
    private DamageInfo info;
    private static final float DURATION = 0.15F;
    private static final boolean muteSfx = false;

    public HungryFoxAction(int amount) {
        this.amount = amount;
        AbstractMonster weakestMonster = null;

        for (AbstractMonster m : getEnemies()) {
            if (weakestMonster == null)
                weakestMonster = m;
            else if (m.currentHealth < weakestMonster.currentHealth)
                weakestMonster = m;
        }
        target = weakestMonster;
        source = adp();

        attackEffect = AttackEffect.NONE;

        actionType = ActionType.DAMAGE;
        duration = DURATION;
        info = new DamageInfo(adp(), amount, DamageType.THORNS);
    }

    public void update() {
        if (!shouldCancelAction() && target != null) {
            if (duration == DURATION) {
                if (info.owner.isDying || info.owner.halfDead) {
                    isDone = true;
                    return;
                }

                att(new VFXAction(new BiteEffect(target.hb.cX, target.hb.cY - 40.0F * Settings.scale,
                        Color.SCARLET.cpy()), 0.2F));
            }

            tickDuration();
            if (isDone) {
                target.damage(info);
                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
                    AbstractDungeon.actionManager.clearPostCombatActions();
            }

        } else {
            isDone = true;
        }
    }
}