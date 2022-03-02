package theGartic.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import theGartic.actions.SwapDrawAndDiscardPilesAction;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class PurpleStuff extends CustomPotion {
    public static final String ID = makeID(PurpleStuff.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public PurpleStuff() {
        super(NAME, ID, PotionRarity.UNCOMMON, PotionSize.SPHERE, PotionColor.EXPLOSIVE);
        isThrown = false;
    }

    public void initializeData() {
        this.potency = getPotency();
        this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        atb(new SwapDrawAndDiscardPilesAction());
        atb(new DrawCardAction(AbstractDungeon.player, potency));
    }

    @Override
    public int getPotency(int i) {
        return 4;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new PurpleStuff();
    }
}
