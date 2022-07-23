package theGartic.cards.summonOptions;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theGartic.actions.SummonOrbAction;
import theGartic.summons.DireWolfSummon;
import theGartic.summons.FireImpSummon;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class DireWolfOption extends AbstractSummonOption
{
    public static final String ID = makeID(DireWolfOption.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int BASE_PASSIVE_AMOUNT = DireWolfSummon.BASE_PASSIVE_AMOUNT, BASE_STACK = DireWolfSummon.BASE_STACK;

    public DireWolfOption()
    {
        this(true, false);
    }

    public DireWolfOption(boolean summon, boolean addToParty)
    {
        super(ID, CardType.SKILL, summon, addToParty);
        magicNumber = baseMagicNumber = BASE_PASSIVE_AMOUNT;
        secondMagic = baseSecondMagic = BASE_STACK;
    }

    @Override
    public void OnSummon()
    {
        atb(new SummonOrbAction(new DireWolfSummon(BASE_PASSIVE_AMOUNT, BASE_STACK)));
    }
}
