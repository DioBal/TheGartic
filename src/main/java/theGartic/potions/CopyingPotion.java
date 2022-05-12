package theGartic.potions;

import basemod.abstracts.CustomPotion;
//import basemod.Basemod;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import static theGartic.util.Wiz.getRandomItem;
import static theGartic.GarticMod.makeID;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CopyingPotion extends CustomPotion {
    
    public static final String POTION_ID = makeID("CopyingPotion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    
    public CopyingPotion() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.BOTTLE , PotionColor.SMOKE);
        this.isThrown = false;
    }
    
    @Override
    public void initializeData() {
        potency = getPotency(AbstractDungeon.ascensionLevel);
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        tips.clear();
        tips.add(new PowerTip(name, description));
    }
    
    @Override
    public void use(AbstractCreature target) {
        ArrayList<AbstractCard> tempcards = (ArrayList<AbstractCard>)(CardLibrary.getAllCards().stream().filter(c -> (c.rarity == AbstractCard.CardRarity.SPECIAL) && (c.cost != -2) && (c.type != AbstractCard.CardType.CURSE)).collect(Collectors.toList()));
        for (int i = 0; i < potency; i++) {
            this.addToBot(new MakeTempCardInHandAction(getRandomItem(tempcards, AbstractDungeon.cardRandomRng)));
        }
    }
    
    @Override
    public int getPotency(int ascensionLevel) {
        return 3;
    }
    
    @Override
    public AbstractPotion makeCopy() {
        return new CopyingPotion();
    }
}