package theGartic.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGartic.GarticMod;
import theGartic.util.TexLoader;

import static theGartic.GarticMod.makeID;

public class DoubleDamagePower extends AbstractPower {
    public static final String POWER_ID = makeID("DoubleDamagePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DoubleDamagePower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = PowerType.BUFF;

        this.owner = owner;
        this.amount = amount;
        this.isTurnBased = true;

        this.priority = 6; //pen nib is also 6

        Texture normalTexture = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/DoubleDamage32.png");
        Texture hiDefImage = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/DoubleDamage84.png");
        region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
        region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());

        updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        }
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage * 2.0F : damage;
    }

    public void updateDescription() {
        if (amount == 1) {
            this.description = DESCRIPTIONS[0];
        }
        else {
            this.description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }
}
