package theGartic.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.powers.DynamicEntryPower;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.applyToSelf;

public class DynamicEntry extends AbstractEasyCard {
    public final static String ID = makeID(DynamicEntry.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 1;
    private final static int UPGRADED_COST = 0;

    private static final Color FLAVOR_BOX_COLOR = Color.RED.cpy();
    private static final Color FLAVOR_TEXT_COLOR = Color.WHITE.cpy();

    public DynamicEntry() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;

        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DynamicEntryPower(magicNumber));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
        initializeDescription();
    }
}