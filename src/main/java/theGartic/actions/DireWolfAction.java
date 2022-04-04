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

import static theGartic.summons.DireWolfSummon.helperCard;
import static theGartic.util.Wiz.*;

public class DireWolfAction extends AbstractGameAction {
    private final int stacks;
    private DamageInfo info;
    private static final float DURATION = 0.15F;
    private static final boolean muteSfx = false;

    public DireWolfAction(int stacks, int amount) {
        this.amount = amount;
        this.stacks = stacks;
        helperCard.baseDamage = amount;

        AbstractMonster weakestMonster = null;

        for (AbstractMonster m : getEnemies()) {
            if (weakestMonster == null)
                weakestMonster = m;
            else if (m.currentHealth < weakestMonster.currentHealth)
                weakestMonster = m;
        }
        target = weakestMonster;
        source = adp();

        attackEffect = AttackEffect.BLUNT_HEAVY;

        actionType = ActionType.DAMAGE;
        duration = DURATION;
    }

    public void update() {
        AbstractMonster weakestMonster = null;
        for (AbstractMonster m : getEnemies()) {
            if (weakestMonster == null)
                weakestMonster = m;
            else if (m.currentHealth < weakestMonster.currentHealth)
                weakestMonster = m;
        }
        target = weakestMonster;
        helperCard.calculateCardDamage((AbstractMonster) target);
        int damage = helperCard.damage;

        info = new DamageInfo(adp(), damage, DamageType.THORNS);
        if (!shouldCancelAction() && target != null) {
            if (duration == DURATION) {
                if (info.owner.isDying || info.owner.halfDead) {
                    isDone = true;
                    return;
                }
                att(new VFXAction(new BiteEffect(target.hb.cX, target.hb.cY - 40.0F * Settings.scale,
                        Color.SCARLET.cpy()), 0.1F));
                att(new DamageAction(target, info));
                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
                    AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }
            isDone = true;
    }
}