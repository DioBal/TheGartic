package theGartic.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theGartic.GarticMod.makeID;

@AutoAdd.Ignore
public class SummonDamageHelper extends AbstractEasyCard {
    public final static String ID = makeID(SummonDamageHelper.class.getSimpleName());

    public SummonDamageHelper(int amount) {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseDamage = amount;
    }

    public SummonDamageHelper() {
        this(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}