package theGartic.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.actions.CreationOfPowerAction;

import static theGartic.GarticMod.makeID;

public class CreationOfPower extends AbstractEasyCard {
    public final static String ID = makeID(CreationOfPower.class.getSimpleName());
    private final static int COST = 2;
    
    public CreationOfPower() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        exhaust = true;
    }
    
    //upp small lul
    @Override
    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeTitle();
        initializeDescription();
    }
    
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new CreationOfPowerAction(upgraded));
    }
}
