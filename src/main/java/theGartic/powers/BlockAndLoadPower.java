package theGartic.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theGartic.GarticMod.makeID;
import static theGartic.GarticMod.modID;
import static theGartic.util.Wiz.atb;

public class BlockAndLoadPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID(BlockAndLoadPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public boolean isUpgraded;
    
    public BlockAndLoadPower(AbstractCreature owner, int amount, boolean isUpgraded) {
        super(NAME, PowerType.BUFF, false, owner, amount);
        if(isUpgraded && AbstractDungeon.player.hasPower(this.ID))
        {
            ((BlockAndLoadPower)AbstractDungeon.player.getPower(this.ID)).isUpgraded = true;
        }
    }
    
    public void onGainedBlock(float blockAmount) {
        if (blockAmount > 0.0F) {
            this.flash();
            AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK).makeCopy();
            if(isUpgraded)
            {
                c.upgrade();
            }
            this.addToBot(new MakeTempCardInHandAction(c, true));
        }
    }
    
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }
    
    public void updateDescription() {
        if (!isUpgraded) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
        if (amount <= 1)
        {
            this.description += DESCRIPTIONS[3];
        }
        else
        {
            this.description += DESCRIPTIONS[4];
        }
    }
}
