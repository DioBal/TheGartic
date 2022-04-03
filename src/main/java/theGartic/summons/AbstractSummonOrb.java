package theGartic.summons;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbPassiveEffect;
import theGartic.GarticMod;
import theGartic.util.OrbTargetArrow;

import static theGartic.util.Wiz.adp;

public abstract class AbstractSummonOrb extends CustomOrb
{
    // Animation Rendering Numbers - You can leave these at default, or play around with them and see what they change.
    protected float vfxTimer = 1.0f;
    protected float vfxIntervalMin = 0.1f;
    protected float vfxIntervalMax = 0.4f;
    protected float ORB_WAVY_DIST = 0.04f;
    private static final float PI_4 = 12.566371f;

    public float reticleAlpha = 0.0F;
    private Color reticleColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
    private Color reticleShadowColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
    private boolean reticleRendered = false;
    private float reticleOffset = 0.0F;
    private float reticleAnimTimer = 0.0F;
    private static final float RETICLE_OFFSET_DIST = 15.0F * Settings.scale;

    public AbstractSummonOrb(String ID, String name, int amount, int stack, String path)
    {
        super(ID, name, amount, stack, "", "", path);

        showEvokeValue = true;
        updateDescription();

        angle = MathUtils.random(360.0f);
        channelAnimTimer = 0.5f;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
    }

    protected void renderText(SpriteBatch sb)
    {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(evokeAmount),
                cX + NUM_X_OFFSET, cY + bobEffect.y / 2.0F + NUM_Y_OFFSET - 4.0F * Settings.scale,
                new Color(0.2F, 1.0F, 1.0F, c.a), fontScale);
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(passiveAmount),
                cX + NUM_X_OFFSET, cY + bobEffect.y / 2.0F + NUM_Y_OFFSET + 20.0F * Settings.scale, c, fontScale);
    }

    @Override //if you want to ignore Focus
    public void applyFocus()
    {
        passiveAmount = basePassiveAmount;
        evokeAmount = baseEvokeAmount;
    }

    public void atStartOfTurnPostDraw(){
    }

    @Override
    public void onEvoke() {
    }

    public static void unSummon(AbstractOrb orb)
    {
        AbstractPlayer player = adp();
        if(player.orbs.contains(orb))
        {
            adp().orbs.remove(orb);
            if (player.maxOrbs > 0)
                player.maxOrbs--;
            if (player.maxOrbs < 0)
                player.maxOrbs = 0;
            for(int i = 0; i < player.orbs.size(); ++i)
                (player.orbs.get(i)).setSlot(i, player.maxOrbs);
        }
    }

    public void unSummon() {
        unSummon(this);
    }

    @Override
    public void updateAnimation()
    {
        super.updateAnimation();
        if (AbstractDungeon.screen == GarticMod.Enums.ORB_TARGET_SCREEN && OrbTargetArrow.subscriber.isTarget(this))
            updateReticle();
        angle += Gdx.graphics.getDeltaTime() * 45.0f;
        vfxTimer -= Gdx.graphics.getDeltaTime();
        if (vfxTimer < 0.0f)
        {
            AbstractDungeon.effectList.add(new DarkOrbPassiveEffect(cX, cY)); // This is the purple-sparkles in the orb. You can change this to whatever fits your orb.
            vfxTimer = MathUtils.random(vfxIntervalMin, vfxIntervalMax);
        }
    }

    // Render the orb.
    @Override
    public void render(SpriteBatch sb)
    {
        if (AbstractDungeon.screen == GarticMod.Enums.ORB_TARGET_SCREEN && OrbTargetArrow.subscriber.isTarget(this))
            renderReticle(sb);
        sb.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        sb.draw(img, cX - 48.0f, cY - 48.0f + bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, scale, 0, 0, 0, 96, 96, false, false);
        //sb.setColor(new Color(1.0f, 1.0f, 1.0f, c.a / 2.0f));
        //sb.setBlendFunction(770, 1);
        //sb.draw(img, cX - 48.0f, cY - 48.0f + bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, scale, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, -angle, 0, 0, 96, 96, false, false);
        //sb.setBlendFunction(770, 771);
        renderText(sb);
        hb.render(sb);
    }

    @Override
    public void triggerEvokeAnimation()
    {
        AbstractDungeon.effectsQueue.add(new DarkOrbActivateEffect(cX, cY));
    }

    @Override
    public void playChannelSFX()
    {
        CardCrawlGame.sound.play("ORB_DARK_CHANNEL", 0.1F);
    }

    public void renderReticle(SpriteBatch sb) {
        reticleRendered = true;
        renderReticleCorner(sb, -hb.width / 2.0F + reticleOffset, hb.height / 2.0F - reticleOffset, false, false);
        renderReticleCorner(sb, hb.width / 2.0F - reticleOffset, hb.height / 2.0F - reticleOffset, true, false);
        renderReticleCorner(sb, -hb.width / 2.0F + reticleOffset, -hb.height / 2.0F + reticleOffset, false, true);
        renderReticleCorner(sb, hb.width / 2.0F - reticleOffset, -hb.height / 2.0F + reticleOffset, true, true);
    }

    protected void updateReticle() {
        if (reticleRendered) {
            reticleRendered = false;
            reticleAlpha += Gdx.graphics.getDeltaTime() * 3.0F;
            if (reticleAlpha > 1.0F) {
                reticleAlpha = 1.0F;
            }

            reticleAnimTimer += Gdx.graphics.getDeltaTime();
            if (reticleAnimTimer > 1.0F) {
                reticleAnimTimer = 1.0F;
            }

            reticleOffset = Interpolation.elasticOut.apply(RETICLE_OFFSET_DIST, 0.0F, reticleAnimTimer);
        } else {
            reticleAlpha = 0.0F;
            reticleAnimTimer = 0.0F;
            reticleOffset = RETICLE_OFFSET_DIST;
        }
    }

    private void renderReticleCorner(SpriteBatch sb, float x, float y, boolean flipX, boolean flipY) {
        reticleShadowColor.a = reticleAlpha / 4.0F;
        sb.setColor(reticleShadowColor);
        sb.draw(ImageMaster.RETICLE_CORNER, hb.cX + x - 18.0F + 4.0F * Settings.scale, hb.cY + y - 18.0F - 4.0F * Settings.scale, 18.0F, 18.0F, 36.0F, 36.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 36, 36, flipX, flipY);
        reticleColor.a = reticleAlpha;
        sb.setColor(reticleColor);
        sb.draw(ImageMaster.RETICLE_CORNER, hb.cX + x - 18.0F, hb.cY + y - 18.0F, 18.0F, 18.0F, 36.0F, 36.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 36, 36, flipX, flipY);
    }
}
