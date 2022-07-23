package theGartic.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.powers.TotalMadnessPower;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.applyToSelf;

public class TotalMadness extends AbstractEasyCard {
    public final static String ID = makeID(TotalMadness.class.getSimpleName());
    public final static int MAGIC = 1;
    public final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public TotalMadness() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new TotalMadnessPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}