package theGartic.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.stances.AbstractStance;
import javassist.CtBehavior;
import theGartic.cards.PolarityDisorder;

public class PolarityDisorderPatch {
    @SpirePatch2(
            clz = ChangeStanceAction.class,
            method = "update"
    )
    public static class AddStancePatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void insert(ChangeStanceAction __instance) {
            PolarityDisorder.onChangeStance(ReflectionHacks.getPrivate(__instance, ChangeStanceAction.class, "newStance"));
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractStance.class, "onExitStance");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch2(
            clz = AbstractPlayer.class,
            method = "applyStartOfTurnRelics"
    )
    public static class ResetCounter {
        @SpirePrefixPatch
        public static void prefix() {
            PolarityDisorder.reset();
        }
    }
}
