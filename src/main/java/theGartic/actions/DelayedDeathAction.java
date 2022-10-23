package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theGartic.powers.DelayedDeathPower;
import theGartic.util.Wiz;

public class DelayedDeathAction extends AbstractGameAction {
    private final DamageInfo info;

    public DelayedDeathAction(AbstractMonster target, DamageInfo info) {
        duration = startDuration = Settings.ACTION_DUR_FAST;
        this.target = target;
        this.info = info;
    }

    @Override
    public void update() {
        if (shouldCancelAction()) {
            isDone = true;
        } else {
            tickDuration();
            if (isDone) {
                AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY,
                        AttackEffect.SLASH_DIAGONAL, false));
                target.damage(info);
                if (target.lastDamageTaken > 0) {
                    Wiz.applyToEnemyTop((AbstractMonster) target,
                            new DelayedDeathPower((AbstractMonster) target, target.lastDamageTaken));
                }

                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                } else {
                    addToTop(new WaitAction(0.1F));
                }
            }
        }
    }
}
