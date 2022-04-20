package theGartic.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theGartic.GarticMod.makeID;

@AutoAdd.Ignore
public class DireWolfHelper extends AbstractEasyCard {
    public final static String ID = makeID(DireWolfHelper.class.getSimpleName());

    public DireWolfHelper(int amount) {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseDamage = amount;
    }

    public DireWolfHelper() {
        this(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}