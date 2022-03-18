package theGartic.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGartic.GarticMod;
import theGartic.actions.SummonOrbAction;
import theGartic.summons.CrazyPanda;
import theGartic.util.TexLoader;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.*;

public class PandamoniumPower extends AbstractPower {
    public static final String POWER_ID = makeID("PandamoniumPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public int amount2;
    public static Color redColor2 = Color.RED.cpy();
    public static Color greenColor2 = Color.GREEN.cpy();
    public boolean canGoNegative2 = false;

    public PandamoniumPower(int amount, int amount2) {
        this.amount = amount;
        this.amount2 = amount2;
        ID = POWER_ID + amount2;
        this.name = NAME;
        type = PowerType.BUFF;
        owner = adp();
        isTurnBased = false;

        Texture normalTexture = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/Pandamonium32.png");
        Texture hiDefImage = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/Pandamonium84.png");
        region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
        region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        for (int i = 0; i < amount; i++)
            atb(new SummonOrbAction(new CrazyPanda(amount2)));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        super.renderAmount(sb, x, y, c);
        if (amount2 > 0) {
            if (!isTurnBased) {
                greenColor2.a = c.a;
                c = greenColor2;
            }

            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
        } else if (amount2 < 0 && canGoNegative2) {
            redColor2.a = c.a;
            c = redColor2;
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
        }
    }
}