package theGartic.actions;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import theGartic.summons.CrazyPanda;
import theGartic.summons.FireImpSummon;

import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.att;

public class FireImpAttackAction extends AbstractGameAction
{
    private FireImpSummon imp;

    public FireImpAttackAction(FireImpSummon imp)
    {
        this.imp = imp;
    }

    public void update()
    {
        AbstractDungeon.actionManager.addToTop(
                new VFXAction(new OrbFlareEffect(imp, OrbFlareEffect.OrbFlareColor.DARK), 0.1f));

        AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);

        if (target != null)
            AbstractDungeon.actionManager.addToTop(new DamageAction(target, new DamageInfo(AbstractDungeon.player, imp.attackAmount, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));

        isDone = true;
    }
}
