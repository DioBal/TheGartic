package theGartic.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGartic.GarticMod;
import theGartic.actions.CelestialCraftingAction;
import theGartic.patches.CardCreationHookPatch;
import theGartic.util.TexLoader;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.att;

public class CelestialCraftingPower extends AbstractPower implements OnCardCreationPower {
    public static final String POWER_ID = makeID(CelestialCraftingPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CelestialCraftingPower(int amount) {
        this.amount = amount;
        this.name = NAME;
        ID = POWER_ID;
        type = AbstractPower.PowerType.BUFF;
        owner = adp();
        isTurnBased = false;

        Texture normalTexture = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/CelestialCrafting32.png");
        Texture hiDefImage = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/CelestialCrafting84.png");
        region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
        region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());

        updateDescription();
    }

    @Override
    public boolean onCardCreation(AbstractCard card) {
        if (adp().hasPower(PlayerFlightPower.POWER_ID))
            for (int i = 0; i < amount; i++) {
                if (CardCreationHookPatch.CelestialCraftingField.makeCopy.get(card))
                    att(new CelestialCraftingAction(card.makeStatEquivalentCopy()));
            }
        return true;
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}