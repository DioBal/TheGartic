package theGartic.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.unique.RandomCardFromDiscardPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class Frontflip extends AbstractEasyCard {
    public final static String ID = makeID(Frontflip.class.getSimpleName());

    public Frontflip() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        baseBlock = block = 5;
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        int cardsToDraw = magicNumber;
        if(magicNumber + p.hand.size() - 1 > BaseMod.MAX_HAND_SIZE) { // -1 to account for card being played
            int handSizeAndDraw = BaseMod.MAX_HAND_SIZE - (magicNumber + p.hand.size() - 1);
            cardsToDraw += handSizeAndDraw;
            if(cardsToDraw < p.discardPile.size()) { //Don't say hand is full if you're only going to get one card from the discard
                p.createHandIsFullDialog();
            }
        }
        for(int i = 0; i < cardsToDraw; i++) {
            atb(new RandomCardFromDiscardPileToHandAction());
        }
    }
}
