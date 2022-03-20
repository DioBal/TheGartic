package theGartic.powers;

import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import theGartic.GarticMod;

public class PowerOfCreationPower extends AbstractEasyPower {
    public static final String POWER_ID = GarticMod.makeID("PowerofCreation");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String NAME = powerStrings.NAME;
    
    public PowerOfCreationPower(boolean isTurnBased, AbstractCreature owner, int amount) {
        super(NAME, PowerType.BUFF, isTurnBased, owner, amount);
        isTwoAmount = true;
        amount2 = 0;
        amount2 += amount;
    }
    
    @Override
    public void atStartOfTurn() {
        amount2 = amount;
    }
    
    public void onCardCreation(AbstractCard card) {
        if(amount2 > 0)
        {
            amount2--;
            //flash();
            addToBot(new ReduceCostAction(card.uuid, 1));
        }
    }
    
    @Override
    public void stackPower(int stackAmount) {
        fontScale = 8.0F;
        amount += stackAmount;
        amount2 += stackAmount;
    }
    
    @Override
    public void updateDescription() {
        if(amount > 1)
        {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
        else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }
    }
}
