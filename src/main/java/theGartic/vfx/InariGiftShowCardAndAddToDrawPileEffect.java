package theGartic.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import com.megacrit.cardcrawl.vfx.combat.CardPoofEffect;

public class InariGiftShowCardAndAddToDrawPileEffect extends AbstractGameEffect {
    private static final float EFFECT_DUR = 1.5F;
    private AbstractCard card;
    private static final float PADDING;
    private boolean randomSpot;
    private boolean cardOffset;


    public InariGiftShowCardAndAddToDrawPileEffect(AbstractCard srcCard, boolean randomSpot, boolean toBottom) {
        this.randomSpot = false;
        this.cardOffset = false;
        this.card = srcCard;
        this.duration = 1.5F;
        this.randomSpot = randomSpot;
        this.card.target_x = MathUtils.random((float) Settings.WIDTH * 0.1F, (float)Settings.WIDTH * 0.9F);
        this.card.target_y = MathUtils.random((float)Settings.HEIGHT * 0.8F, (float)Settings.HEIGHT * 0.2F);
        AbstractDungeon.effectsQueue.add(new CardPoofEffect(this.card.target_x, this.card.target_y));
        this.card.drawScale = 0.01F;
        this.card.targetDrawScale = 1.0F;
        if (toBottom) {
            AbstractDungeon.player.drawPile.addToBottom(srcCard);
        } else if (randomSpot) {
            AbstractDungeon.player.drawPile.addToRandomSpot(srcCard);
        } else {
            AbstractDungeon.player.drawPile.addToTop(srcCard);
        }
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.card.update();
        if (this.duration < 0.0F) {
            this.isDone = true;
            this.card.shrink();
            AbstractDungeon.getCurrRoom().souls.onToDeck(this.card, this.randomSpot, true);
        }

    }

    public void render(SpriteBatch sb) {
        if (!this.isDone) {
            this.card.render(sb);
        }

    }

    public void dispose() {
    }

    static {
        PADDING = 30.0F * Settings.scale;
    }

}
