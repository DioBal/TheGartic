package theGartic.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import theGartic.GarticMod;
import static theGartic.GarticMod.makeID;
import theGartic.util.TexLoader;
import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.atb;

public class LoseWrathStancePower extends AbstractPower {
    public static final String POWER_ID = makeID("LoseWrathPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public LoseWrathStancePower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = PowerType.BUFF;

        this.owner = owner;
        this.amount = amount;
        this.isTurnBased = true;

        Texture normalTexture = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/LoseWrathPower32.png");
        Texture hiDefImage = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/LoseWrathPower84.png");
        region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
        region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());

        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean IsPlayer) {
        if (amount == 1 && owner instanceof AbstractPlayer) {
        	flash();
            atb(new ChangeStanceAction("Neutral"));
        }
        atb(new ReducePowerAction(adp(), adp(), this, 1));
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0];
        } else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }
}
