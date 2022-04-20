package theGartic.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect;
import theGartic.vfx.ResoluteParticleEffect;
import theGartic.vfx.ResoluteStanceAuraEffect;

import static theGartic.GarticMod.makeID;

public class ResoluteStance extends AbstractStance {
    public static final String STANCE_ID = makeID("Resolute");
    private static final StanceStrings stanceString;
    private static long sfxId;
    private static final int BLOCK_AMOUNT = 2;
    
    public ResoluteStance() {
        this.ID = STANCE_ID;
        this.name = stanceString.NAME;
        this.updateDescription();
    }
    
    @Override
    public void onPlayCard(AbstractCard card) {
        super.onPlayCard(card);
        if(card.type == AbstractCard.CardType.ATTACK)
        {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, BLOCK_AMOUNT, true));
        }
    }
    
    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.05F;
                AbstractDungeon.effectsQueue.add(new ResoluteParticleEffect());
            }
        }
        
        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.3F, 0.4F);
            AbstractDungeon.effectsQueue.add(new ResoluteStanceAuraEffect());
        }
        
    }
    
    public void updateDescription() {
        this.description = stanceString.DESCRIPTION[0] + BLOCK_AMOUNT + stanceString.DESCRIPTION[1];
    }
    
    public void onEnterStance() {
        if (sfxId != -1L) {
            this.stopIdleSfx();
        }
        
        CardCrawlGame.sound.play("STANCE_ENTER_WRATH");
        sfxId = CardCrawlGame.sound.playAndLoop("STANCE_LOOP_WRATH");
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.GREEN, true));
        AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, "Wrath"));
    }
    
    public void onExitStance() {
        this.stopIdleSfx();
    }
    
    public void stopIdleSfx() {
        if (sfxId != -1L) {
            CardCrawlGame.sound.stop("STANCE_LOOP_WRATH", sfxId);
            sfxId = -1L;
        }
        
    }
    
    static {
        stanceString = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
        sfxId = -1L;
    }
}