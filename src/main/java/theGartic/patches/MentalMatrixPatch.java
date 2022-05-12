package theGartic.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;

@SpirePatch(clz = ShowCardAndAddToDiscardEffect.class, method = "update")
//@SpirePatch(clz = ShowCardAndAddToHandEffect.class, method = "update")
@SpirePatch(clz = ShowCardAndAddToDrawPileEffect.class, method = "update")
public class MentalMatrixPatch {
    @SpirePrefixPatch
    public static void Prefix(AbstractGameEffect __instance, float ___EFFECT_DUR, AbstractCard ___card)
    {
        if (__instance.duration == ___EFFECT_DUR)
        {
            // Track the number of cards added to draw or discard
            GameActionManager GMA = new GameActionManager();
            int prevValue = GameActionManagerPatch.addCardsAddedToDrawOrDiscardField.CardsAddedToDrawOrDiscard.get(GMA);
            // Increment value each time
            GameActionManagerPatch.addCardsAddedToDrawOrDiscardField.CardsAddedToDrawOrDiscard.set(GMA,(prevValue+1));
        }
    }
}