package theGartic.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.powers.DivineInterventionPower;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.applyToSelf;

public class DivineIntervention extends AbstractEasyCard {
    public final static String ID = makeID(DivineIntervention.class.getSimpleName());
    public final static int COST = 3;
    public final static int UPGRADED_COST = 2;
    public final static int MAGIC = 1;

    private static final Color FLAVOR_BOX_COLOR = Color.GOLD.cpy();
    private static final Color FLAVOR_TEXT_COLOR = Color.BLACK.cpy();

    public DivineIntervention() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;

        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DivineInterventionPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}