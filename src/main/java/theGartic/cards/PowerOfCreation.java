package theGartic.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.actions.CreationOfPowerAction;
import theGartic.powers.PowerOfCreationPower;

import static theGartic.GarticMod.makeID;

public class PowerOfCreation extends AbstractEasyCard {
    public final static String ID = makeID(PowerOfCreation.class.getSimpleName());
    private final static int COST = 2;
    private final static int UPGRADED_COST = 1;
    
    public PowerOfCreation() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.NONE);
    }
    
    //upp small lul
    @Override
    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
    
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractPlayer, abstractPlayer, new PowerOfCreationPower(false, abstractPlayer, 1)));
    }
}