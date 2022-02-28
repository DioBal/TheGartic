package theGartic.cards.fish;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.cards.AbstractEasyCard;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.*;

public class Boot extends AbstractEasyCard {
    public final static String ID = makeID("Boot");
    // intellij stuff skill, none, special, , , , , 1, 1

    public Boot() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}