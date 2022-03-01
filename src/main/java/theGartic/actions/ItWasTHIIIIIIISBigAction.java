package theGartic.actions;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGartic.cards.AbstractEasyCard;
import theGartic.cards.fish.AbstractFishCard;

import java.util.ArrayList;

public class ItWasTHIIIIIIISBigAction extends AbstractGameAction {

    public ItWasTHIIIIIIISBigAction(int amount) {
        this.amount = amount;
        duration = startDuration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        for (AbstractCard c: AbstractDungeon.player.hand.group) {
            tripleCard(c);
            if (c instanceof AbstractFishCard) {
                c.superFlash();
            }
        }
        for (AbstractCard c: AbstractDungeon.player.drawPile.group)
            tripleCard(c);
        for (AbstractCard c: AbstractDungeon.player.discardPile.group)
            tripleCard(c);
        isDone = true;
    }

    private void tripleCard(AbstractCard c) {
        if (c instanceof AbstractFishCard) {
            if (c.baseDamage > 0)
                c.damage = c.baseDamage = c.baseDamage * amount;
            if (c.baseBlock > 0)
                c.block = c.baseBlock = c.baseBlock * amount;
            if (c.baseMagicNumber > 0)
                c.magicNumber = c.baseMagicNumber = c.baseMagicNumber * amount;
            if (((AbstractEasyCard)c).baseSecondMagic > 0)
                ((AbstractEasyCard)c).secondMagic = ((AbstractEasyCard)c).baseSecondMagic = ((AbstractEasyCard)c).baseSecondMagic * amount;
            c.applyPowers();
        }
    }
}
