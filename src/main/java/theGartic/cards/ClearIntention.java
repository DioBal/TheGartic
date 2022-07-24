package theGartic.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.actions.ClearIntentionAction;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class ClearIntention extends AbstractEasyCard {
    public final static String ID = makeID(ClearIntention.class.getSimpleName());
    public final static int MAGIC = 4;
    public final static int UPGRADE_MAGIC = 5;
    private final static int COST = 3;

    public ClearIntention() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ClearIntentionAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}