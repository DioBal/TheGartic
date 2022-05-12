package theGartic.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.WeMeetAgain;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static theGartic.GarticMod.makeID;

public class DarklingMilk extends CustomPotion {
    public static final String ID = makeID(DarklingMilk.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public DarklingMilk() {
        super(NAME, ID, PotionRarity.RARE, PotionSize.BOTTLE, PotionColor.WHITE);
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
        // Hardcoding max starting hp because it's hardcoded elsewhere...
        int hp_diff = 80 - AbstractDungeon.player.maxHealth;
        AbstractDungeon.player.increaseMaxHp((hp_diff * this.potency)/100, true);
    }

    @Override
    public boolean canUse() {
        int hp_diff = 80 - AbstractDungeon.player.maxHealth;
        if( hp_diff > 0 ) { return true; }
        return false;
    }

    @Override
    public int getPotency(int i) {
        return 50;
    }

    public void upgradePotion()
    {
        potency = 100;
        tips.clear();
        tips.add(new PowerTip(name, description));
    }


    @Override
    public AbstractPotion makeCopy() {
        return new DarklingMilk();
    }
}
