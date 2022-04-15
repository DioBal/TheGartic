package theGartic.cards.InariModal;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import theGartic.actions.InariGiftAction;
import theGartic.cards.EasyModalChoiceCard;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class InariGift extends InariCard {

    /*
    Step 1: Use Colorless Potion effect.
    Step 2: Go after my old code for Rainbow Brush that creates cards and see how
        to use it together with the Colorless Potion effect to make exhaustible Colorless cards.
     */

    public static final String ID = makeID(InariGift.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public InariGift(){
        super(NAME, DESCRIPTION, () -> {
            atb(new InariGiftAction(1));
        });

        baseMagicNumber = magicNumber;

        initializeDescription();
    }

    public InariGift(int magicNumber){
        super(NAME, DESCRIPTION, () -> {
            atb(new InariGiftAction(magicNumber));
        });

        baseMagicNumber = magicNumber;

        initializeDescription();
    }

    @Override
    public void updateMagicNumber(int amount){
        magicNumber += amount;
        baseMagicNumber = magicNumber;
    }
}
