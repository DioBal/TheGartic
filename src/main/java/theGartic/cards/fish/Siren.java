package theGartic.cards.fish;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theGartic.cards.AbstractEasyCard;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.*;

public class Siren extends AbstractFishCard {
    public final static String ID = makeID("Siren");
    // intellij stuff skill, enemy, special, , , , , 2, 1

    public Siren() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 6;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new StrengthPower(m, -magicNumber));
        atb(new LoseHPAction(m, p, secondMagic));
        atb(new DrawCardAction(1));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(3);
    }
}