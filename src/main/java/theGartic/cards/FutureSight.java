package theGartic.cards;

import com.megacrit.cardcrawl.actions.unique.ForethoughtAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class FutureSight extends AbstractEasyCard {
    public final static String ID = makeID(FutureSight.class.getSimpleName());
    public final static int COST = 2;
    public final static int BLOCK = 12;

    public FutureSight() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (upgraded)
            atb(new ForethoughtAction(true));
        else
            atb(new ForethoughtAction(false));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}