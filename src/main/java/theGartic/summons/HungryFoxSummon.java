package theGartic.summons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.GarticMod;
import theGartic.actions.HungryFoxAction;
import theGartic.cards.summonOptions.FireImpOption;
import theGartic.cards.summonOptions.HungryFoxOption;
import theGartic.util.OrbTargetArrow;

import static theGartic.util.Wiz.*;
import static theGartic.GarticMod.makeOrbPath;

public class HungryFoxSummon extends AbstractSummonOrb
{
    public static final String ORB_ID = GarticMod.makeID(HungryFoxSummon.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String imgPath = "HungryFox.png";
    public static int BASE_PASSIVE_AMOUNT = 3;

    public HungryFoxSummon()
    {
        this(BASE_PASSIVE_AMOUNT);
    }

    public HungryFoxSummon(int passive)
    {
        super(ORB_ID, orbString.NAME, passive, 0, makeOrbPath(imgPath));
        showEvokeValue = false;
        summonOption = new HungryFoxOption(false, true);
    }

    @Override
    public void updateAnimation() {
        if (AbstractDungeon.screen == GarticMod.Enums.ORB_TARGET_SCREEN && OrbTargetArrow.subscriber.isAcceptableTarget(this) && hb.hovered)
            updateReticle();
        cX = MathHelper.orbLerpSnap(cX, AbstractDungeon.player.animX + tX);
        cY = MathHelper.orbLerpSnap(cY, AbstractDungeon.player.animY + tY);
        if (channelAnimTimer != 0.0F) {
            channelAnimTimer -= Gdx.graphics.getDeltaTime();
            if (channelAnimTimer < 0.0F) {
                channelAnimTimer = 0.0F;
            }
        }

        c.a = Interpolation.pow2In.apply(1.0F, 0.01F, channelAnimTimer / 0.5F);
        scale = Interpolation.swingIn.apply(Settings.scale, 0.01F, channelAnimTimer / 0.5F);
    }

    @Override
    protected void renderText(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L,
                Integer.toString(passiveAmount),cX + NUM_X_OFFSET + 20* Settings.scale,
                cY + NUM_Y_OFFSET - 20* Settings.yScale,
                new Color(1.0f, 0.5f, 0.5f, 1.0f), fontScale);
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
        return new HungryFoxSummon(passiveAmount);
    }
}