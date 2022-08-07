package theGartic.cards.summonOptions;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theGartic.actions.SummonOrbAction;
import theGartic.summons.BaitSummon;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class BaitOption extends AbstractSummonOption
{
    public static final String ID = makeID(BaitOption.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int BASE_AMOUNT = BaitSummon.BASE_AMOUNT;

    public BaitOption()
    {
        this(true, false);
    }

    public BaitOption(boolean summon, boolean addToParty)
    {
        super(ID, CardType.ATTACK, summon, addToParty);
        magicNumber = baseMagicNumber = BASE_AMOUNT;
    }

    @Override
    public void OnSummon()
    {
        atb(new SummonOrbAction(new BaitSummon(BASE_AMOUNT)));
    }
}
