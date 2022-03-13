package theGartic.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGartic.GarticMod;
import theGartic.util.TexLoader;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.*;

public class SummonCostRefundPower extends AbstractPower {
    public static final String POWER_ID = makeID("SummonCostRefundPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SummonCostRefundPower(int amount) {
        ID = POWER_ID;
        this.name = NAME;
        type = PowerType.BUFF;

        owner = adp();
        this.amount = amount;
        isTurnBased = false;

        Texture normalTexture = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/SummonCostRefund32.png");
        Texture hiDefImage = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/SummonCostRefund84.png");
        region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
        region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());

        updateDescription();
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        super.onAfterUseCard(card, action);
        if (card.hasTag(GarticMod.Enums.SUMMON)) {
            att(new RemoveSpecificPowerAction(adp(), adp(), this));
            att(new GainEnergyAction(amount));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}