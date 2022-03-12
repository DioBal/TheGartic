package theGartic.summons;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.GarticMod;
import theGartic.actions.PandaAction;

import static java.lang.Math.pow;
import static theGartic.GarticMod.makeOrbPath;
import static theGartic.util.Wiz.*;

public class CrazyPanda extends AbstractSummonOrb
{
    public static final String ORB_ID = GarticMod.makeID(CrazyPanda.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String imgPath = "CrazyPanda.png";
    private static final float BOUNCE_DURATION = 0.5f;
    private static final float GRAVITY = 300.0f;

    private float bounceTime = 0;
    private boolean shooting = false;
    private boolean bouncing = false;
    private float bounceSourceX;
    private float bounceSourceY;
    private float peakY;
    private float peakTime;

    public CrazyPanda(int passive)
    {
        super(ORB_ID, orbString.NAME, passive, 0, makeOrbPath(imgPath));
        showEvokeValue = false;
    }

    @Override
    public void onEndOfTurn() {
        atb(new PandaAction(this));
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
        peakTime = BOUNCE_DURATION*BOUNCE_DURATION + (tY - bounceSourceY) / GRAVITY;
        peakY = GRAVITY*peakTime*peakTime + bounceSourceY;
    }

    @Override
    public void updateAnimation() {
        if (shooting) {
            bobEffect.update();
            if (channelAnimTimer != 0.0F) {
                channelAnimTimer -= Gdx.graphics.getDeltaTime();
                if (channelAnimTimer < 0.0F) {
                    channelAnimTimer = 0.0F;
                }
            }
            if (bouncing) {
                bounceTime += Gdx.graphics.getDeltaTime();
                if (bounceTime > BOUNCE_DURATION) {
                    cX = tX;
                    cY = tY;
                    endShoot();
                } else {
                    cX = bounceSourceX + (tX - bounceSourceX) * (bounceTime / BOUNCE_DURATION);
                    cY = (float)(peakY - pow(bounceTime - peakTime, 2));
                }
            }
        }
        else
            super.updateAnimation();
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