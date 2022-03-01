package theGartic.cards.fish;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theGartic.cards.AbstractEasyCard;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.*;

public class Qwilfish extends AbstractFishCard {
    public final static String ID = makeID("Qwilfish");
    // intellij stuff skill, enemy, special, , , , , 5, 2

    public Qwilfish() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 5;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new PoisonPower(m, p, magicNumber));
        atb(new DrawCardAction(1));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}