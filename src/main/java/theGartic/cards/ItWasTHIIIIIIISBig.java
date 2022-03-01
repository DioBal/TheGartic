package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.actions.ItWasTHIIIIIIISBigAction;
import theGartic.cards.fish.AbstractFishCard;

import java.util.ArrayList;

import static theGartic.GarticMod.makeID;

public class ItWasTHIIIIIIISBig extends AbstractEasyCard {
    public final static String ID = makeID(ItWasTHIIIIIIISBig.class.getSimpleName());

    public ItWasTHIIIIIIISBig() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        magicNumber = baseMagicNumber = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ItWasTHIIIIIIISBigAction(magicNumber));
    }

    public void upp() {
        upMagic(1);
        uDesc();
    }
}