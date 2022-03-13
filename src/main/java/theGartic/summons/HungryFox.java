package theGartic.summons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.GarticMod;
import theGartic.actions.HungryFoxAction;

import static theGartic.GarticMod.makeOrbPath;
import static theGartic.util.Wiz.*;

public class HungryFox extends AbstractSummonOrb
{
    public static final String ORB_ID = GarticMod.makeID(HungryFox.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String imgPath = "HungryFox.png";

    public HungryFox(int passive)
    {
        super(ORB_ID, orbString.NAME, passive, 0, makeOrbPath(imgPath));
        showEvokeValue = false;
    }

    @Override
    protected void renderText(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(passiveAmount), cX + NUM_X_OFFSET, cY + bobEffect.y / 2.0F + NUM_Y_OFFSET + 20.0F * Settings.scale, c, fontScale);
    }

    @Override
    public void onEndOfTurn() {
        atb(new HungryFoxAction(passiveAmount));
    }

    @Override
    public void updateDescription()
    {
        description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractOrb makeCopy()
    {
        return new HungryFox(passiveAmount);
    }
}