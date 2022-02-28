package theGartic.cards.fish;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.cards.AbstractEasyCard;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class Piranha extends AbstractEasyCard {
    public final static String ID = makeID("Piranha");
    // intellij stuff atack, enemy, special, 5, 4, , , , 

    public Piranha() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = 5;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new DrawCardAction(1));
    }

    public void upp() {
        upgradeDamage(4);
    }
}