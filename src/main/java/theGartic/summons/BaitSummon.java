package theGartic.summons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.GarticMod;

public class BaitSummon extends AbstractSummonOrb{

    public static final String ORB_ID = GarticMod.makeID(BaitSummon.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;

    public BaitSummon(int amount){
        //TODO: Add code to here
    }

    @Override
    protected void renderText(SpriteBatch sb) {

    }

    @Override
    public void onEndOfTurn() {
        //TODO: Create action to summon another summon
        //TODO: Create action to unsummon Bait
    }

    @Override
    public void updateDescription()
    {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new InariWhiteFoxSummon(passiveAmount);
    }

}
