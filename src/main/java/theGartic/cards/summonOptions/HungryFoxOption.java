package theGartic.cards.summonOptions;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theGartic.actions.SummonOrbAction;
import theGartic.cards.Hunt;
import theGartic.summons.HungryFoxSummon;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class HungryFoxOption extends AbstractSummonOption
{
    public static final String ID = makeID(HungryFoxOption.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int BASE_FOX_POWER = Hunt.MAGIC;

    public HungryFoxOption()
    {
        this(true, false);
    }

    public HungryFoxOption(boolean summon, boolean addToParty)
    {
        super(ID, CardType.ATTACK, summon, addToParty);
        magicNumber = baseMagicNumber = BASE_FOX_POWER;
    }

    @Override
    public void OnSummon()
    {
        atb(new SummonOrbAction(new HungryFoxSummon(BASE_FOX_POWER)));
    }
}
