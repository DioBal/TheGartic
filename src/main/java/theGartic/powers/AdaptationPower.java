package theGartic.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import theGartic.actions.BetterDiscardPileToTopOfDeckAction;
import theGartic.util.TexLoader;
import theGartic.GarticMod;

import static theGartic.GarticMod.makeID;

public class AdaptationPower extends AbstractPower {

    public static final String POWER_ID = makeID("AdaptationPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AdaptationPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = PowerType.BUFF;

        this.owner = owner;
        this.amount = amount;
        this.isTurnBased = false;

        Texture normalTexture = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/Adaptation32.png");
        Texture hiDefImage = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/Adaptation84.png");
        region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
        region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());

        updateDescription();
    }

    public void updateDescription() {
        if (amount == 1) {
            this.description = powerStrings.DESCRIPTIONS[0];
        } else {
            this.description = powerStrings.DESCRIPTIONS[1] + this.amount + powerStrings.DESCRIPTIONS[2];
        }
    }

    public void onChangeStance(AbstractStance oldStance, AbstractStance newStance) {
        if (!oldStance.ID.equals(newStance.ID)) {
            flash();
            addToBot(new BetterDiscardPileToTopOfDeckAction(AbstractDungeon.player, this.amount));
        }
    }
}
