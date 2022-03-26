package theGartic.cards;

import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.GarticMod;
import theGartic.cards.fish.AbstractFishCard;
import theGartic.util.Wiz;

public class BigHaul extends AbstractEasyCard {
    public final static String ID = GarticMod.makeID(BigHaul.class.getSimpleName());

    private static final int FISH = 2;
    private static final int UP_FISH = 1;

    public BigHaul() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        magicNumber = baseMagicNumber = FISH;
    }
    
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            AbstractCard fish = AbstractFishCard.returnRandomFish();
            CardModifierManager.addModifier(fish, new RetainMod());
            Wiz.makeInHand(fish);
        }
    }
    
    public void upp() {
        upgradeMagicNumber(UP_FISH);
    }
}