package theGartic.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.powers.UncannyHungerPower;
import static theGartic.GarticMod.makeID;

public class UncannyHunger extends AbstractEasyCard {
    public final static String ID = makeID("UncannyHunger");
    private int MAGIC = 2; //energy gain from unsummon
    private int SECOND_MAGIC = 2; //card draw from unsummon


    public UncannyHunger() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = MAGIC;
        secondMagic = baseSecondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new UncannyHungerPower(p, magicNumber, secondMagic), 1));
    }

    public void upp() {
        this.isInnate = true;
        uDesc();
    }
}
