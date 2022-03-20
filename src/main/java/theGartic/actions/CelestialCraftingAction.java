package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import theGartic.patches.CardCreationHookPatch;

public class CelestialCraftingAction extends AbstractGameAction {
    AbstractCard card;

    public CelestialCraftingAction(AbstractCard card) {
        this.card = card;
        duration = startDuration = 0.2f;
    }

    @Override
    public void update() {
        if (duration == startDuration) {
            CardCreationHookPatch.CelestialCraftingField.makeCopy.set(card, false);
            AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(card));
        }
        isDone = true;
        tickDuration();
    }
}
