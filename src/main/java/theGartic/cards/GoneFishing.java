package theGartic.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import theGartic.cards.fish.AbstractFishCard;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.*;

public class GoneFishing extends AbstractEasyCard {
    public final static String ID = makeID("GoneFishing");
    // intellij stuff skill, self, rare, , , , , 3, 2

    public GoneFishing() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new IntangiblePlayerPower(p, 1));
        for (int i = 0; i < magicNumber; i++) {
            shuffleIn(AbstractFishCard.returnRandomFish());
        }
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}