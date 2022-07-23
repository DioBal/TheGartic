package theGartic.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

import static theGartic.util.Wiz.*;

public class HungryFoxAction extends AbstractGameAction {
    private static final float DURATION = 0.15F;

    public HungryFoxAction(int amount) {
        this.amount = amount;
        actionType = ActionType.DAMAGE;
        duration = DURATION;
    }

    public void update() {
        if (adp() == null || adp().isDying) {
            isDone = true;
            return;
        }
        if (duration == DURATION) {
            AbstractMonster weakestMonster = null;

            for (AbstractMonster m : getEnemies()) {
                if (weakestMonster == null && m != null && !m.halfDead)
                    weakestMonster = m;
                else if (weakestMonster != null && m != null && m.currentHealth < weakestMonster.currentHealth && !m.halfDead)
                    weakestMonster = m;
            }

            if (weakestMonster == null) {
                isDone = true;
                return;
            }
            target = weakestMonster;
            DamageInfo info = new DamageInfo(adp(), amount, DamageType.THORNS);
            atb(new VFXAction(new BiteEffect(target.hb.cX, target.hb.cY - 40.0F * Settings.scale,
                    Color.SCARLET.cpy()), 0.1F));
            atb(new DamageAction(target, info));
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
                AbstractDungeon.actionManager.clearPostCombatActions();
        }
        isDone = true;
    }
}