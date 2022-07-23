package theGartic.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.powers.SoaringFormPower;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.applyToSelf;

public class SoaringForm extends AbstractEasyCard {
    public final static String ID = makeID(SoaringForm.class.getSimpleName());
    public final static int COST = 3;
    public final static int MAGIC = 1;

    public SoaringForm() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SoaringFormPower(magicNumber));
    }

    public void upp() {
        isEthereal = false;
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}