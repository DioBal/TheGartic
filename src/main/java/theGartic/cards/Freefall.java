package theGartic.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.patches.UnnatePatch;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.atb;

public class Freefall extends AbstractEasyCard {
    public final static String ID = makeID(Freefall.class.getSimpleName());
    public final static int MAGIC = 3;
    public final static int UPGRADE_MAGIC = -1;
    private final static int COST = -2;

    public Freefall() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        UnnatePatch.UnnateFieldPatch.unnate.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void triggerWhenDrawn() {
        atb(new DrawCardAction(magicNumber));
        atb(new ExhaustSpecificCardAction(this, adp().hand));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    public void upp() {
        isInnate = true;
        UnnatePatch.UnnateFieldPatch.unnate.set(this, false);
        upgradeMagicNumber(UPGRADE_MAGIC);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}