package theGartic.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.actions.ExtendoArmAction;

import static theGartic.GarticMod.makeID;

public class ExtendoArm  extends AbstractEasyCard {
    public final static String ID = makeID(ExtendoArm.class.getSimpleName());

    private static final int BLK = 7, BLK_UPG = 3;

    public ExtendoArm() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        block = baseBlock = BLK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new ExtendoArmAction());
    }

    public void upp() {
        upgradeBlock(BLK_UPG);
    }
}