package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.powers.GlassArmorPower;
import theGartic.vfx.GlassWaveEffect;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.applyToSelf;
import static theGartic.util.Wiz.atb;

public class GlassWave extends AbstractEasyCard {
    public final static String ID = makeID(GlassWave.class.getSimpleName());

    public GlassWave() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 8;
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new WaitAction(0.1F));
        if (p != null && m != null)
            atb(new VFXAction(new GlassWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F));
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        applyToSelf(new GlassArmorPower(p, magicNumber));
    }
}
