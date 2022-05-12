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
import theGartic.actions.PandaSmackAction;
import theGartic.util.OrbTargetArrow;

import static java.lang.Math.pow;
import static theGartic.GarticMod.makeOrbPath;
import static theGartic.util.Wiz.atb;

public class CrazyPanda extends AbstractSummonOrb
{
    public static final String ORB_ID = GarticMod.makeID(CrazyPanda.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String imgPath = "CrazyPanda.png";

    // DO NOT SET EITHER OF THESE TO ZERO
    private static final float BOUNCE_DURATION = 1.0f;
    private static final float GRAVITY = 2700.0f;

    private float bounceTime = 0;
    private boolean shooting = false;
    private boolean bouncing = false;
    private float bounceSourceX;
    private float bounceSourceY;
    private float peakY;
    private float peakTime;

    private float rotation;

    public CrazyPanda(int passive)
    {
        super(ORB_ID, orbString.NAME, passive, 0, makeOrbPath(imgPath));
        showEvokeValue = false;
        rotation = 0.0f;
        updateDescription();
    }

    @Override
    public void onEndOfTurn() {
        atb(new PandaSmackAction(this));
    }

    public void startShoot() {
        shooting = true;
        bouncing = false;
    }

    public void endShoot() {
        shooting = false;
        bouncing = false;
    }

    public void startBounce(float sourceX, float sourceY) {
        bounceTime = 0;
        bouncing = true;
        bounceSourceX = sourceX;
        bounceSourceY = sourceY;
        calculatePeak();
    }

    private void calculatePeak() {
        peakTime = (tY - bounceSourceY) / BOUNCE_DURATION / GRAVITY + BOUNCE_DURATION / 2;
        peakY = GRAVITY * peakTime * peakTime / 2.0f + bounceSourceY;
    }

    @Override
    public void updateAnimation() {
        if (AbstractDungeon.screen == GarticMod.Enums.ORB_TARGET_SCREEN && OrbTargetArrow.subscriber.isAcceptableTarget(this) && hb.hovered)
            updateReticle();
        if (channelAnimTimer != 0.0F) {
            channelAnimTimer -= Gdx.graphics.getDeltaTime();
            if (channelAnimTimer < 0.0F) {
                channelAnimTimer = 0.0F;
            }
        }
        if (shooting)
            rotation += 6*Gdx.graphics.getDeltaTime()*360.0f;
        else
            rotation += 0.5f*Gdx.graphics.getDeltaTime()*360.0f;

        c.a = Interpolation.pow2In.apply(1.0F, 0.01F, channelAnimTimer / 0.5F);
        scale = Interpolation.swingIn.apply(Settings.scale, 0.01F, channelAnimTimer / 0.5F);
        if (shooting) {
            if (bouncing) {
                bounceTime += Gdx.graphics.getDeltaTime();
                if (bounceTime > BOUNCE_DURATION) {
                    cX = tX;
                    cY = tY;
                    endShoot();
                } else {
                    cX = bounceSourceX + (tX - bounceSourceX) * (bounceTime / BOUNCE_DURATION);
                    cY = (float)(peakY - pow(bounceTime - peakTime, 2)*GRAVITY/2.0f);
                }
            }
        }
        else {
            cX = MathHelper.orbLerpSnap(cX, tX);
            cY = MathHelper.orbLerpSnap(cY, tY);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if (AbstractDungeon.screen == GarticMod.Enums.ORB_TARGET_SCREEN && OrbTargetArrow.subscriber.isAcceptableTarget(this) && hb.hovered)
            renderReticle(sb);
        sb.setColor(Color.WHITE.cpy());
        sb.setBlendFunction(770, 771);
        sb.draw(img, cX - 48.0F, cY - 48.0F, 48.0F, 48.0F, 96.0F, 96.0F, scale, scale, rotation, 0, 0, 96, 96, false, false);
        renderText(sb);
        hb.render(sb);
    }

    @Override
    protected void renderText(SpriteBatch sb) {
        if (!shooting)
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(passiveAmount),
                    cX + NUM_X_OFFSET + 20*Settings.scale, cY + NUM_Y_OFFSET - 20* Settings.yScale,
                    new Color(1.0f, 0.5f, 0.5f, 1.0f), fontScale);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new CrazyPanda(passiveAmount);
    }
}

/*
    ym - ys = xm*xm*a/2
    ym - yt = (dur - xm)^2 *a/2
    ym = xm*xm*a/2 + ys
    xm*xm*a/2 + ys - yt = (dur - xm)^2 *a/2 = [dur^2 -2dur*xm + xm^2]*a/2
    ys - yt = [dur^2 - 2dur*xm]*a/2
    ys - yt - dur^2 *a/2 = -dur*xm * a
    [ys - yt - dur^2 * a/2] / -dur*a = xm
    xm = (yt - ys)/dur/a + dur/2
 */