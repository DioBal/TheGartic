package theGartic.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import theGartic.GarticMod;
import theGartic.actions.UncannyHungerUnsummonAction;
import theGartic.util.TexLoader;

import java.util.Collections;

import static theGartic.GarticMod.makeID;

public class UncannyHungerPower extends AbstractPower implements NonStackablePower {

    public static final String POWER_ID = makeID("UncannyHungerPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int amount2;
    private int energyGain;
    private int cardDraw;
    public static Color green = Color.GREEN.cpy();
    public String tipString;

    public UncannyHungerPower(AbstractCreature owner, int energyGain, int cardDraw) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = PowerType.BUFF;
        this.owner = owner;
        this.isTurnBased = false;
        this.energyGain = energyGain;
        this.cardDraw = cardDraw;
        this.amount = energyGain;
        this.amount2 = cardDraw;
        this.tipString = "Your hunger must be sated.";


        Texture normalTexture = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/UncannyHunger32.png");
        Texture hiDefImage = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/UncannyHunger84.png");
        region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
        region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());

        updateDescription();
    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        this.addToBot(new UncannyHungerUnsummonAction(tipString, energyGain, cardDraw));
    }

    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(powerStrings.DESCRIPTIONS[0]);
        this.description = powerStrings.DESCRIPTIONS[0] + String.join("", Collections.nCopies(energyGain, "[E] ")) + powerStrings.DESCRIPTIONS[1] + this.cardDraw + powerStrings.DESCRIPTIONS[2];
    }

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        super.renderAmount(sb, x, y, c);
        green.a = c.a;
        c=green;
        FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
    }
}