package theGartic.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class GlassWaveEffect extends AbstractGameEffect {
    private float waveTimer = 0.0F;
    private float x;
    private float y;
    private float cX;

    public GlassWaveEffect(float x, float y, float cX) {
        this.x = x + 120.0F * Settings.scale;
        this.y = y - 20.0F * Settings.scale;
        this.cX = cX;
    }

    public void update() {
        waveTimer -= Gdx.graphics.getDeltaTime();
        if (waveTimer < 0.0F) {
            waveTimer = 0.03F;
            x += 160.0F * Settings.scale;
            y -= 15.0F * Settings.scale;
            AbstractDungeon.effectsQueue.add(new GlassWaveParticle(x, y));
            if (x > cX) {
                isDone = true;
                CardCrawlGame.sound.playA("ATTACK_DAGGER_6", -0.3F);
            }
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
