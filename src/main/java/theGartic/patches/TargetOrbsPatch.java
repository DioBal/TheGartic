package theGartic.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;
import theGartic.GarticMod;
import theGartic.util.OrbTargetScreen;

import java.util.ArrayList;

public class TargetOrbsPatch {
    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "openPreviousScreen"
    )
    public static class OpenPreviousScreen {
        public static void Postfix(AbstractDungeon.CurrentScreen s) {
            if (s == GarticMod.Enums.ORB_TARGET_SCREEN)
                OrbTargetScreen.Inst.reopen();
        }
    }

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "render"
    )
    public static class Render {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AbstractDungeon __instance, SpriteBatch sb) {
            if (AbstractDungeon.screen == GarticMod.Enums.ORB_TARGET_SCREEN)
                OrbTargetScreen.Inst.render(sb);
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "screen");
                return LineFinder.findInOrder(ctBehavior, new ArrayList<>(), finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "update"
    )
    public static class Update {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AbstractDungeon __instance) {
            if (AbstractDungeon.screen == GarticMod.Enums.ORB_TARGET_SCREEN)
                OrbTargetScreen.Inst.update();
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "screen");
                return LineFinder.findInOrder(ctBehavior, new ArrayList<>(), finalMatcher);
            }
        }
    }
}
