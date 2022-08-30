package theGartic.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGartic.GarticMod;
import theGartic.actions.FireImpAttackAction;
import theGartic.summons.AbstractSummonOrb;
import theGartic.summons.FireImpSummon;
import theGartic.util.TexLoader;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.atb;

public class DynamicEntryPower extends AbstractPower implements OnSummonPower {
    public static final String POWER_ID = makeID(DynamicEntryPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DynamicEntryPower(int amount) {
        ID = POWER_ID;
        this.name = NAME;
        type = PowerType.BUFF;

        owner = adp();
        this.amount = amount;
        isTurnBased = false;

        Texture normalTexture = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/DynamicEntry32.png");
        Texture hiDefImage = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/DynamicEntry84.png");
        region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
        region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());

        updateDescription();
    }

    @Override
    public void onSummon(AbstractSummonOrb orb) {
        for (int i = 0; i < amount; i++) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    orb.onEndOfTurn();
                    if (orb instanceof FireImpSummon)
                        atb(new FireImpAttackAction((FireImpSummon) orb));
                    isDone = true;
                }
            });
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}