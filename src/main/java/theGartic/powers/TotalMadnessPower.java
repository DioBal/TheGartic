package theGartic.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.stances.WrathStance;
import theGartic.GarticMod;
import theGartic.actions.PlayRandomDiscardAction;
import theGartic.util.TexLoader;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.atb;

public class TotalMadnessPower extends AbstractPower {
    public static final String POWER_ID = makeID(TotalMadnessPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TotalMadnessPower(int amount) {
        ID = POWER_ID;
        this.name = NAME;
        type = PowerType.BUFF;

        owner = adp();
        this.amount = amount;
        isTurnBased = false;

        Texture normalTexture = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/TotalMadness32.png");
        Texture hiDefImage = TexLoader.getTexture(GarticMod.modID + "Resources/images/powers/TotalMadness84.png");
        region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
        region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        if (adp().stance.ID.equals(WrathStance.STANCE_ID)) {
            flash();

            for(int i = 0; i < amount; ++i) {
                atb(new AbstractGameAction() {
                    public void update() {
                        atb(new PlayRandomDiscardAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(
                                (AbstractMonster)null, true, AbstractDungeon.cardRandomRng)));
                        isDone = true;
                    }
                });
            }
        }
        else
            atb(new ChangeStanceAction(WrathStance.STANCE_ID));
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}