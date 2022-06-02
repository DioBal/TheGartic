package theGartic.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class GameActionManagerPatch
{
    @SpirePatch(
            clz=GameActionManager.class,
            method=SpirePatch.CLASS
    )
    public static class addCardsAddedToDrawOrDiscardField {
        public static SpireField<Integer> CardsAddedToDrawOrDiscard = new SpireField<>(() -> 0);
    }


    @SpirePatch(
            clz=GameActionManager.class,
            method="clearPostCombatActions"
    )
    public static class resetCardsAddedToDrawOrDiscard {
        @SpirePrefixPatch
        public static void Prefix()
        {
            GameActionManager GMA = AbstractDungeon.actionManager;
            // Set value back to zero after each combat
            addCardsAddedToDrawOrDiscardField.CardsAddedToDrawOrDiscard.set(GMA, 0);
        }
    }
}