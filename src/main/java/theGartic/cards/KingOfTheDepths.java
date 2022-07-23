package theGartic.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.GarticMod;
import theGartic.cardmods.ConsumeCallbackInterface;
import theGartic.cardmods.ConsumeMod;
import theGartic.cards.fish.AbstractFishCard;
import theGartic.util.Wiz;

import java.util.Iterator;

public class KingOfTheDepths extends AbstractEasyCard implements ConsumeCallbackInterface {
    public final static String ID = GarticMod.makeID(KingOfTheDepths.class.getSimpleName());

    private static final int FISH = 1;
    private static final int BONUS_FISH = 1;
    private static final int UP_COST = 2;

    public KingOfTheDepths() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        misc = FISH;
        magicNumber = baseMagicNumber = misc;
        secondMagic = baseSecondMagic = BONUS_FISH;
        CardModifierManager.addModifier(this, new ConsumeMod());
        exhaust = true;
    }
    
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            Wiz.shuffleIn(AbstractFishCard.returnRandomFish());
        }
    }

    @Override
    public void update() {
        super.update();
        magicNumber = baseMagicNumber = misc;
    }
    
    public void upp() {
        upgradeBaseCost(UP_COST);
    }

    @Override
    public void numCardsUsedToConsume(int num) {
        Iterator var1 = AbstractDungeon.player.masterDeck.group.iterator();

        AbstractCard c;
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c.uuid.equals(this.uuid)) {
                c.misc += secondMagic * num;
                c.applyPowers();
                c.magicNumber = c.baseMagicNumber = c.misc;
                c.isMagicNumberModified = false;
            }
        }

        for(var1 = GetAllInBattleInstances.get(this.uuid).iterator(); var1.hasNext(); c.magicNumber = c.baseMagicNumber = c.misc) {
            c = (AbstractCard)var1.next();
            c.misc += secondMagic * num;
            c.applyPowers();
        }
    }
}