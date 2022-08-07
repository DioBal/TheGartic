package theGartic.cards.summonOptions;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theGartic.actions.SummonOrbAction;
import theGartic.summons.FireImpSummon;
import theGartic.summons.MirroredFoxSummon;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class MirroredFoxOption extends AbstractSummonOption
{
    public static final String ID = makeID(MirroredFoxOption.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int BASE_PASSIVE_AMOUNT = MirroredFoxSummon.BASE_PASSIVE_AMOUNT, BASE_STACK = MirroredFoxSummon.BASE_STACK;

    public MirroredFoxOption()
    {
        this(true, false);
    }

    public MirroredFoxOption(boolean summon, boolean addToParty)
    {
        super(ID, CardType.SKILL, summon, addToParty);
        magicNumber = baseMagicNumber = BASE_PASSIVE_AMOUNT;
    }

    @Override
    public void OnSummon()
    {
        atb(new SummonOrbAction(new MirroredFoxSummon(BASE_PASSIVE_AMOUNT, BASE_STACK)));
    }
}
