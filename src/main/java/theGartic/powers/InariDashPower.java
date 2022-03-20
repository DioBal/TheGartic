package theGartic.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGartic.GarticMod;
import theGartic.util.TexLoader;

import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.atb;

public class InariDashPower extends AbstractPower {

    public static final String POWER_ID = GarticMod.makeID(InariDashPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static int amount_of_turns_left = 2;
    private static int magicAmount;

    public InariDashPower(int magicAmount)
    {
        this.name = NAME;
        ID = POWER_ID;

        this.owner = adp();
        this.amount = -1;
        this.magicAmount = magicAmount;

        type = AbstractPower.PowerType.BUFF;
        isTurnBased = true;

        Texture normalTexture = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/Hover32.png");
        Texture hiDefImage = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/Hover84.png");
        region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
        region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());

        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action){
        atb(new DrawCardAction(magicAmount));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer){
            amount_of_turns_left -= 1;
        }
        if (amount_of_turns_left <= 0){
            atb(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }

    @Override
    public void updateDescription() {
        if (magicAmount == 1){
            description = DESCRIPTIONS[0] + magicAmount + DESCRIPTIONS[1];
            description += DESCRIPTIONS[3];
        }
        else{
            description = DESCRIPTIONS[0] + magicAmount + DESCRIPTIONS[2];
            description += DESCRIPTIONS[3];
        }

    }

}
