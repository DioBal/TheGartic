package theGartic.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.*;

public class CarryOn extends AbstractEasyCard {
    //Carry On - 0 Cost Common Skill - Discard 2 Cards. Draw an(2) extra card(s) next turn.
    public final static String ID = makeID("CarryOn");
    private final static int MAGIC = 2; //Discard
    private final static int SECOND_MAGIC = 1; //Draw
    private final static int UPGRADE_SECOND_MAGIC = 1; //Draw
    private final static int COST = 0;

    public CarryOn() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DiscardAction(p, p, magicNumber, false));
        this.addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, secondMagic), secondMagic));
    }

    public void upp() {
        upSecondMagic(UPGRADE_SECOND_MAGIC);
        uDesc();
    }
}
