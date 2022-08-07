package theGartic.cards.summonOptions;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theGartic.actions.SummonOrbAction;
import theGartic.summons.FireImpSummon;
import theGartic.summons.InariWhiteFoxSummon;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class InariOption extends AbstractSummonOption
{
    public static final String ID = makeID(InariOption.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int BASE_PASSIVE_AMOUNT = InariWhiteFoxSummon.BASE_PASSIVE_AMOUNT;

    public InariOption()
    {
        this(true, false);
    }

    public InariOption(boolean summon, boolean addToParty)
    {
        super(ID, CardType.POWER, summon, addToParty);
        magicNumber = baseMagicNumber = BASE_PASSIVE_AMOUNT;
    }

    @Override
    public void OnSummon()
    {
        atb(new SummonOrbAction(new InariWhiteFoxSummon(BASE_PASSIVE_AMOUNT)));
    }
}
