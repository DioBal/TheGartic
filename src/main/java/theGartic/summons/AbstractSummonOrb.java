package theGartic.summons;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbPassiveEffect;

public abstract class AbstractSummonOrb extends CustomOrb
{
    // Animation Rendering Numbers - You can leave these at default, or play around with them and see what they change.
    private float vfxTimer = 1.0f;
    private float vfxIntervalMin = 0.1f;
    private float vfxIntervalMax = 0.4f;
    private static final float ORB_WAVY_DIST = 0.04f;
    private static final float PI_4 = 12.566371f;

    public AbstractSummonOrb(String ID, String name, int amount, int stack, String path)
    {
        super(ID, name, amount, stack, "", "", path);

        showEvokeValue = true;
        updateDescription();

        angle = MathUtils.random(360.0f);
        channelAnimTimer = 0.5f;
    }

    public void onUseCard(AbstractCard card, UseCardAction action)
    {

    }

    protected void renderText(SpriteBatch sb)
    {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L,

                Integer.toString(this.evokeAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET - 4.0F * Settings.scale, new Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale);
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L,

                Integer.toString(this.passiveAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET + 20.0F * Settings.scale, this.c, this.fontScale);
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
    public void onEvoke()
    {

    }

    public static void unSummon(AbstractOrb orb)
    {
        AbstractPlayer player = AbstractDungeon.player;
        if(player.orbs.contains(orb))
        {
            AbstractDungeon.player.orbs.remove(orb);
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
        angle += Gdx.graphics.getDeltaTime() * 45.0f;
        vfxTimer -= Gdx.graphics.getDeltaTime();
        if (vfxTimer < 0.0f)
        {
            AbstractDungeon.effectList.add(new DarkOrbPassiveEffect(cX, cY)); // This is the purple-sparkles in the orb. You can change this to whatever fits your orb.
            vfxTimer = MathUtils.random(vfxIntervalMin, vfxIntervalMax);
        }

        AbstractPlayer player = AbstractDungeon.player;
    }

    // Render the orb.
    @Override
    public void render(SpriteBatch sb)
    {
        sb.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        sb.draw(img, cX - 48.0f, cY - 48.0f + bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, scale, 0, 0, 0, 96, 96, false, false);
        //sb.setColor(new Color(1.0f, 1.0f, 1.0f, this.c.a / 2.0f));
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
}
