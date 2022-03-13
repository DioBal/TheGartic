package theGartic.summons;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.GarticMod;

import static theGartic.GarticMod.makeOrbPath;

public class InariWhiteFoxSummon extends AbstractSummonOrb
{
    public static final String ORB_ID = GarticMod.makeID(InariWhiteFoxSummon.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static int BASE_PASSIVE_AMOUNT = 1, BASE_STACK = 1;

    public InariWhiteFoxSummon(int amount, int stack)
    {
        super(ORB_ID, orbString.NAME, amount, stack, makeOrbPath("MischievousFox.png"));

    }

    @Override
    public AbstractOrb makeCopy() {
        return new InariWhiteFoxSummon(passiveAmount, evokeAmount);
    }
}
