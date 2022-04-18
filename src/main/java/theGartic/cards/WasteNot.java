package theGartic.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.GraveField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.vfx.GlassWaveEffect;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class WasteNot extends AbstractEasyCard {
    public final static String ID = makeID(WasteNot.class.getSimpleName());

    public WasteNot() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        exhaust = false;
        }

    @Override
    public void upp() {
        upgradeDamage(2);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        if(this.upgraded) {
            GraveField.grave.set(this, true);
        }
    }

    public void triggerOnUnsummon() {
        this.addToBot(new DiscardToHandAction(this));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

}
