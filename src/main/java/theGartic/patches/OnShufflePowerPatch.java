package theGartic.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.actions.defect.ShuffleAllAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;
import theGartic.powers.OnShufflePower;

import static theGartic.util.Wiz.adp;

public class OnShufflePowerPatch {
    @SpirePatch(
            clz = EmptyDeckShuffleAction.class,
            method = SpirePatch.CONSTRUCTOR
    )
    @SpirePatch(
            clz = ShuffleAllAction.class,
            method = SpirePatch.CONSTRUCTOR
    )
    @SpirePatch(
            clz = ShuffleAction.class,
            method = SpirePatch.CONSTRUCTOR
    )
    public static class ShuffleHookForPowers {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static SpireReturn insert(AbstractPlayer __instance) {
            for (AbstractPower pow : adp().powers) {
                if (pow instanceof OnShufflePower)
                    ((OnShufflePower) pow).onShuffle();
            }
            return SpireReturn.Continue();
        }
        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "relics");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
