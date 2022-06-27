package theGartic.cards.summonOptions;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theGartic.actions.SummonOrbAction;
import theGartic.summons.DireWolfSummon;
import theGartic.summons.FetchingFoxSummon;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class FetchingFoxOption extends AbstractSummonOption
{
    public static final String ID = makeID(FetchingFoxOption.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int BASE_AMOUNT = FetchingFoxSummon.BASE_AMOUNT;

    public FetchingFoxOption()
    {
        this(true, false);
    }

    public FetchingFoxOption(boolean summon, boolean addToParty)
    {
        super(ID, CardType.SKILL, summon, addToParty);
        magicNumber = baseMagicNumber = BASE_AMOUNT;
    }

    @Override
    public void OnSummon()
    {
        atb(new SummonOrbAction(new FetchingFoxSummon(BASE_AMOUNT, BASE_AMOUNT)));
    }
}
