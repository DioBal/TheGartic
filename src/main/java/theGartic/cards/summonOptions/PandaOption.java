package theGartic.cards.summonOptions;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theGartic.actions.SummonOrbAction;
import theGartic.cards.Pandamonium;
import theGartic.summons.CrazyPanda;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class PandaOption extends AbstractSummonOption
{
    public static final String ID = makeID(PandaOption.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int BASE_PANDA_POWER = Pandamonium.MAGIC;

    public PandaOption()
    {
        this(true, false);
    }

    public PandaOption(boolean summon, boolean addToParty)
    {
        super(ID, CardType.POWER, summon, addToParty);
        magicNumber = baseMagicNumber = BASE_PANDA_POWER;
    }

    @Override
    public void OnSummon()
    {
        atb(new SummonOrbAction(new CrazyPanda(BASE_PANDA_POWER)));
    }
}
