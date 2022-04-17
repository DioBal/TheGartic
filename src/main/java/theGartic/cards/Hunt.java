package theGartic.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.GarticMod;
import theGartic.actions.SummonOrbAction;
import theGartic.summons.HungryFox;

import static theGartic.util.Wiz.*;
import static theGartic.GarticMod.makeID;

public class Hunt extends AbstractEasyCard {
    public final static String ID = makeID(Hunt.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    private static final Color FLAVOR_BOX_COLOR = Color.BROWN.cpy();
    private static final Color FLAVOR_TEXT_COLOR = Color.WHITE.cpy();

    public Hunt() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(GarticMod.Enums.SUMMON);

        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, GarticMod.Enums.GUNSHOT);
        atb (new SummonOrbAction(new HungryFox(magicNumber)));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}