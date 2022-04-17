package theGartic.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.actions.DistractingFoxAction;
import theGartic.summons.AbstractSummonOrb;

import static theGartic.util.Wiz.*;
import static theGartic.GarticMod.makeID;

public class DistractingFox extends AbstractEasyCard {
    public final static String ID = makeID(DistractingFox.class.getSimpleName());
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 1;
    public final static String FOX_STRING = CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[1];

    private static final Color FLAVOR_BOX_COLOR = Color.BROWN.cpy();
    private static final Color FLAVOR_TEXT_COLOR = Color.WHITE.cpy();

    public DistractingFox() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;

        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DistractingFoxAction(magicNumber, cardStrings.EXTENDED_DESCRIPTION[2]));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m))
            return false;
        for (AbstractOrb orb : adp().orbs) {
            if (orb instanceof AbstractSummonOrb)
                if (DistractingFoxAction.isOrbTarget((AbstractSummonOrb) orb))
                    return true;
        }
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}