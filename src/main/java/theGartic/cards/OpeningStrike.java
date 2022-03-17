package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theGartic.GarticMod.makeID;

public class OpeningStrike extends AbstractEasyCard {
    public final static String ID = makeID("OpeningStrike");

    public OpeningStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = this.damage = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.damage >= 16) {
            dmg(m, AbstractGameAction.AttackEffect.SMASH);
        } else if (this.damage >= 8) {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        } else {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
    }

    public void upp() {
        upgradeDamage(9);
        uDesc();
    }

}
