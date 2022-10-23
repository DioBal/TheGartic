package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.powers.DoubleDamagePower;

import static theGartic.GarticMod.makeID;

public class Approach extends AbstractEasyCard {
    public final static String ID = makeID("Approach");

    public Approach() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 14;

        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addToBot(new ApplyPowerAction(p, p, new DoubleDamagePower(p, 1), 1));
    }

    public void upp() {
        upgradeDamage(4);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Approach();
    }
}