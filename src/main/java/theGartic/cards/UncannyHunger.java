package theGartic.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.powers.UncannyHungerPower;
import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class UncannyHunger extends AbstractEasyCard {
    public final static String ID = makeID(UncannyHunger.class.getSimpleName());
    private static final int MAGIC = 2; //energy gain from unsummon
    private static final int SECOND_MAGIC = 2; //card draw from unsummon
    public static String TIP_STRING;

    public UncannyHunger() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = MAGIC;
        secondMagic = baseSecondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(p, p, new UncannyHungerPower(p, magicNumber, secondMagic), 1));
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }

    public static String getTipString() {
        if (TIP_STRING == null)
            TIP_STRING = CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0];
        return TIP_STRING;
    }
}
