package theGartic.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theGartic.GarticMod;
import theGartic.cardmods.ConsumeCallbackInterface;
import theGartic.cardmods.ConsumeMod;
import theGartic.util.Wiz;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class BloodInTheWater extends AbstractEasyCard implements ConsumeCallbackInterface {
    public final static String ID = GarticMod.makeID(BloodInTheWater.class.getSimpleName());

    private static final int STR = 2;
    private static final int STR_PER_FISH = 1;

    public BloodInTheWater() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.NONE);
        magicNumber = baseMagicNumber = STR;
        secondMagic = baseSecondMagic = STR_PER_FISH;
        CardModifierManager.addModifier(this, new ConsumeMod());
    }
    
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new StrengthPower(p, magicNumber));
    }
    
    public void upp() {
        selfRetain = true;
        rawDescription = languagePack.getCardStrings(cardID).UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void numCardsUsedToConsume(int num) {
        Wiz.applyToSelf(new StrengthPower(AbstractDungeon.player, secondMagic * num));
    }
}