package theGartic.cards.summonOptions;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theGartic.actions.SummonOrbAction;
import theGartic.relics.PartyRelic;
import theGartic.summons.CrazyPanda;
import theGartic.summons.FireImpSummon;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.atb;

public class PandaOption extends AbstractSummonOption
{
    public static final String ID = makeID(PandaOption.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int BASE_PANDA_POWER = 10;

    public PandaOption()
    {
        this(true, false);
    }

    public PandaOption(boolean summon, boolean addToParty)
    {
        super(ID, NAME, DESCRIPTION, CardType.POWER, summon, addToParty);
        magicNumber = baseMagicNumber = BASE_PANDA_POWER;
    }

    @Override
    public void OnSummon()
    {
        atb(new SummonOrbAction(new CrazyPanda(BASE_PANDA_POWER)));
    }
}
