package theGartic.cards.fish;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.cards.AbstractEasyCard;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class Guppy extends AbstractEasyCard {
    public final static String ID = makeID("Guppy");
    // intellij stuff skill, self, special, , , 3, 2, , 

    public Guppy() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        baseBlock = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new DrawCardAction(1));
    }

    public void upp() {
        upgradeBlock(2);
    }
}