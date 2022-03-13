package theGartic.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.CardCrawlGame;
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

    public static int counter = 0;

    public PandamoniumPower(int amount) {
        ID = POWER_ID + counter;
        counter++;
        this.name = NAME;
        type = PowerType.BUFF;

        owner = adp();
        this.amount = amount;
        isTurnBased = false;

        Texture normalTexture = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/Pandamonium32.png");
        Texture hiDefImage = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/Pandamonium84.png");
        region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
        region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        atb(new SummonOrbAction(new CrazyPanda(amount)));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}