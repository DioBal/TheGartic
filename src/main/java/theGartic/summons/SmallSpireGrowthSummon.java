package theGartic.summons;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.GarticMod;

import static theGartic.GarticMod.makeOrbPath;

public class SmallSpireGrowthSummon extends AbstractSummonOrb {

    public static final String ORB_ID = GarticMod.makeID(SmallSpireGrowthSummon.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;

    public SmallSpireGrowthSummon()
    {
        super(ORB_ID, orbString.NAME, 1, 0, makeOrbPath("MischievousFox.png"));
    }

    @Override
    public AbstractOrb makeCopy() {
        return new SmallSpireGrowthSummon();
    }

    //It should deal 5 points of damage at the end of the turn.
    //How do I do that without much work...

}
