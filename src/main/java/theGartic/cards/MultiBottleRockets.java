package theGartic.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.actions.MultiBottleRocketsAction;

import static theGartic.GarticMod.makeID;

public class MultiBottleRockets extends AbstractEasyCard {
    public final static String ID = makeID("MultiBottleRockets");

    public MultiBottleRockets() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 7;
        magicNumber = baseMagicNumber = 1;
        isMultiDamage = true;
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MultiBottleRocketsAction(this));
    }
}
