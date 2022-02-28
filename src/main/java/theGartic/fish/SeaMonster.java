package theGartic.fish;

import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.cards.AbstractEasyCard;

import java.util.ArrayList;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.*;

public class SeaMonster extends AbstractEasyCard {
    public final static String ID = makeID("SeaMonster");
    // intellij stuff skill, none, special, , , , , , 

    public SeaMonster() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> foundCards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            foundCards.add(AbstractDungeon.returnTrulyRandomCardInCombat(CardType.ATTACK));
        }
        if (upgraded) {
            for (AbstractCard c : foundCards) {
                c.upgrade();
            }
        }
        for (AbstractCard c : foundCards) {
            CardModifierManager.addModifier(c, new ExhaustMod());
            c.setCostForTurn(0);
            atb(new MakeTempCardInHandAction(c, true));
        }
    }

    public void upp() {
        uDesc();
    }
}