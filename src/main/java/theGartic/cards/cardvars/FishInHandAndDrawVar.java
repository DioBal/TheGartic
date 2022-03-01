package theGartic.cards.cardvars;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGartic.GarticMod;
import theGartic.cards.fish.AbstractFishCard;

public class FishInHandAndDrawVar extends DynamicVariable {
    @Override
    public String key() {
        return GarticMod.makeID("fshhd");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return value(card) != 0;
    }

    @Override
    public int value(AbstractCard card) {
        int count = 0;
        for (AbstractCard c: AbstractDungeon.player.drawPile.group)
            if (c instanceof AbstractFishCard)
                count++;
        for (AbstractCard c: AbstractDungeon.player.hand.group)
            if (c instanceof AbstractFishCard)
                count++;
        return count;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return false;
    }
}
