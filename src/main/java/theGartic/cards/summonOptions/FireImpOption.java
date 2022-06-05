package theGartic.cards.summonOptions;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theGartic.actions.SummonOrbAction;
import theGartic.summons.FireImpSummon;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class FireImpOption extends AbstractSummonOption
{
    public static final String ID = makeID(FireImpOption.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int BASE_PASSIVE_AMOUNT = FireImpSummon.BASE_PASSIVE_AMOUNT;

    public FireImpOption()
    {
        this(true, false);
    }

    public FireImpOption(boolean summon, boolean addToParty)
    {
        super(ID, CardType.ATTACK, summon, addToParty);
        magicNumber = baseMagicNumber = BASE_PASSIVE_AMOUNT;
    }

    @Override
    public void OnSummon()
    {
        atb(new SummonOrbAction(new FireImpSummon(BASE_PASSIVE_AMOUNT, 1)));
    }
}
