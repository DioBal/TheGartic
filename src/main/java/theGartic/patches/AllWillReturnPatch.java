package theGartic.patches;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CtBehavior;

import java.util.ArrayList;
import java.util.List;

import static theGartic.util.Wiz.adp;

public class AllWillReturnPatch {
    public static int lastTurnBlock = 0;
    public static int lastTurnDamage = 0;

    public static int thisTurnBlock = 0;
    public static int thisTurnDamage = 0;

    @SpirePatch(
            clz = GameActionManager.class,
            method = "getNextAction"
    )
    public static class ResetEndOfRound {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void reset(GameActionManager __instance) {
            lastTurnBlock = thisTurnBlock;
            lastTurnDamage = thisTurnDamage;

            thisTurnBlock = 0;
            thisTurnDamage = 0;
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

    @SpirePatch(
            clz = AbstractCreature.class,
            method = "addBlock"
    )
    public static class BlockGain {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void tracking(AbstractCreature __instance, int blockAmount, float ___tmp) {
            if (__instance.equals(AbstractDungeon.player)) {
                thisTurnBlock += MathUtils.floor(___tmp);
            }
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCreature.class, "powers");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    //I hope nobody overrides damage in a monster without calling super. I'm sure nobody would do that. Right?
    @SpirePatch(
            clz = AbstractMonster.class,
            method = "damage",
            paramtypez = { DamageInfo.class }
    )
    public static class MonsterDamage {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void tracking(AbstractCreature __instance, DamageInfo info) {
            if (info.owner != null && info.owner == adp()) {
                thisTurnDamage += __instance.lastDamageTaken;
            }
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractMonster.class, "currentHealth");
                List<Matcher> preMatches = new ArrayList<>();
                preMatches.add(finalMatcher);
                return LineFinder.findInOrder(ctMethodToPatch, preMatches, finalMatcher);
            }
        }
    }
    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "damage",
            paramtypez = { DamageInfo.class }
    )
    public static class WhyAreYouHittingYourself {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void noReallyWhy_AreYouUsingTacklesOrSomething(AbstractCreature __instance, DamageInfo info, int ___damageAmount) {
            if (__instance.equals(info.owner)) {
                thisTurnDamage += Math.min(___damageAmount, __instance.currentHealth);
            }
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(GameActionManager.class, "damageReceivedThisTurn");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
