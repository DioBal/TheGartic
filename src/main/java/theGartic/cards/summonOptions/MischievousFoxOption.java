package theGartic.cards.summonOptions;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theGartic.actions.SummonOrbAction;
import theGartic.summons.FireImpSummon;
import theGartic.summons.MischievousFoxSummon;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class MischievousFoxOption extends AbstractSummonOption
{
    public static final String ID = makeID(MischievousFoxOption.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int BASE_PASSIVE_AMOUNT = MischievousFoxSummon.BASE_PASSIVE_AMOUNT, BASE_STACK = MischievousFoxSummon.BASE_STACK;

    public MischievousFoxOption()
    {
        this(true, false);
    }

    public MischievousFoxOption(boolean summon, boolean addToParty)
    {
        super(ID, CardType.SKILL, summon, addToParty);
        magicNumber = baseMagicNumber = BASE_PASSIVE_AMOUNT;
    }

    @Override
    public void OnSummon()
    {
        atb(new SummonOrbAction(new MischievousFoxSummon(BASE_PASSIVE_AMOUNT, BASE_STACK)));
    }
}
