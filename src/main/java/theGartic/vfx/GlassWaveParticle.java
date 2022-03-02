package theGartic.vfx;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.vfx.combat.IronWaveParticle;

public class GlassWaveParticle extends IronWaveParticle {

    public GlassWaveParticle(float x, float y) {
        super(x, y);
        this.color = Color.CYAN.cpy();
    }
}
