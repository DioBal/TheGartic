package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import theGartic.GarticMod;
import theGartic.util.Wiz;

public class ChargeBeam extends AbstractEasyCard {
    public final static String ID = GarticMod.makeID("ChargeBeam");

    public ChargeBeam() {
        super(ID, -2, CardType.ATTACK, CardRarity.RARE, CardTarget.NONE);
        damage = baseDamage = 7;
        magicNumber = baseMagicNumber = 3;
        selfRetain = true;
    }

    public void onRetained() {
        superFlash();
        upgradeDamage(this.magicNumber);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {}

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void triggerOnManualDiscard() {
        addToBot(new VFXAction(new MindblastEffect(Wiz.adp().dialogX, Wiz.adp().dialogY, Wiz.adp().flipHorizontal)));
        addToBot(new DamageAllEnemiesAction(Wiz.adp(), damage, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}