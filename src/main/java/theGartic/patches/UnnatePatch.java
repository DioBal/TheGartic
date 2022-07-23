package theGartic.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CtBehavior;

import java.util.ArrayList;

public class UnnatePatch {
    @SpirePatch2(clz = AbstractCard.class, method = SpirePatch.CONSTRUCTOR)
    public static class UnnateFieldPatch {
        public UnnateFieldPatch() {}

        public static SpireField<Boolean> unnate = new SpireField<Boolean>(() -> { return false; });
    }

    @SpirePatch(clz = CardGroup.class, method = "initializeDeck")
    public static class UnnateInsertPatch {
        @SpireInsertPatch(locator = Locator.class, localvars = {"copy"})
        public static void Insert(CardGroup copy) {
            ArrayList<AbstractCard> placeOnBot = new ArrayList<AbstractCard>();

            for (AbstractCard newCard : copy.group) {
                if (UnnateFieldPatch.unnate.get(newCard))
                    placeOnBot.add(newCard);
            }

            if (placeOnBot.size() > 0) {
                for (AbstractCard abstractCard : placeOnBot) {
                    copy.removeCard(abstractCard);
                    copy.addToBottom(abstractCard);
                }
            }
        }
        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "applyStartOfTurnRelics");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}