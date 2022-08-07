package theGartic.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;
import theGartic.powers.DivineInterventionPower;

import static theGartic.util.Wiz.adp;

public class DivineInterventionPatch {
    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "damage"
    )
    public static class OnTheMagesSoTheyCanAOE {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static SpireReturn insert(AbstractPlayer __instance) {
            for (AbstractPower pow : adp().powers) {
                if (pow instanceof DivineInterventionPower) {
                    ((DivineInterventionPower) pow).onTrigger();
                    return SpireReturn.Return();
                }
            }
            return SpireReturn.Continue();
        }
        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "hasPotion");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
