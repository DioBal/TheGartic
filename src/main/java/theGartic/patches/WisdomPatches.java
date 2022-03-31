package theGartic.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DiscardAtEndOfTurnAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theGartic.stances.WisdomStance;

import java.util.ArrayList;

@SpirePatch2(
        clz = CardGroup.class,
        method=SpirePatch.CLASS
)
public class WisdomPatches {

    public static SpireField<Boolean> wisdomActive = new SpireField<>(() -> false);

    @SpirePatch2(
            clz = CardGroup.class,
            method = "addToTop"
    )
    public static class AddToTopPatch{
        @SpirePostfixPatch
        public static void plsWork(CardGroup __instance, AbstractCard c) {
            if(!AbstractDungeon.player.stance.ID.equals(WisdomStance.STANCE_ID) || wisdomActive.get(__instance).equals(true)){
                return;
            }

            if(__instance.type==CardGroup.CardGroupType.DISCARD_PILE){
                wisdomActive.set(__instance, true);
                onAddToDiscardPile(__instance, c);
            } else if (__instance.type==CardGroup.CardGroupType.DRAW_PILE){
                wisdomActive.set(__instance, true);
                onAddToDrawPile(__instance, c, false , false);
            }
        }
    }

    @SpirePatch2(
            clz = CardGroup.class,
            method = "addToBottom"
    )
    public static class AddToBottomPatch{
        @SpirePostfixPatch
        public static void plsWork(CardGroup __instance, AbstractCard c) {
            if(!AbstractDungeon.player.stance.ID.equals(WisdomStance.STANCE_ID) || wisdomActive.get(__instance).equals(true)){
                return;
            }

            if(__instance.type==CardGroup.CardGroupType.DISCARD_PILE){
                wisdomActive.set(__instance, true);
                onAddToDiscardPile(__instance, c);
            } else if (__instance.type==CardGroup.CardGroupType.DRAW_PILE){
                wisdomActive.set(__instance, true);
                onAddToDrawPile(__instance, c, false , true);
            }
        }
    }

    @SpirePatch2(
            clz = CardGroup.class,
            method = "addToRandomSpot"
    )
    public static class AddToRandomSpotPatch{
        @SpirePostfixPatch
        public static void plsWork(CardGroup __instance, AbstractCard c) {
            if(!AbstractDungeon.player.stance.ID.equals(WisdomStance.STANCE_ID) || wisdomActive.get(__instance).equals(true)){
                return;
            }

            if(__instance.type==CardGroup.CardGroupType.DISCARD_PILE){
                wisdomActive.set(__instance, true);
                onAddToDiscardPile(__instance, c);
            } else if (__instance.type==CardGroup.CardGroupType.DRAW_PILE){
                wisdomActive.set(__instance, true);
                onAddToDrawPile(__instance, c, true , false);
            }
        }
    }

    public static void onAddToDiscardPile(CardGroup cg, AbstractCard card){
        //check for end of turn discard
        if(AbstractDungeon.actionManager.currentAction instanceof DiscardAtEndOfTurnAction){
            wisdomActive.set(cg, false);
            return;
        }
        ArrayList<AbstractGameAction> actions = new ArrayList<AbstractGameAction>(AbstractDungeon.actionManager.actions);
        for(AbstractGameAction a : actions){
            if(a instanceof DiscardAtEndOfTurnAction){
                wisdomActive.set(cg, false);
                return;
            }
        }

        AbstractCard temp = card.makeStatEquivalentCopy();
        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(temp));
        wisdomActive.set(cg, false);
    }

    public static void onAddToDrawPile(CardGroup cg, AbstractCard card, boolean randomSpot, boolean toBottom){
        //check for deck shuffle
        if(AbstractDungeon.actionManager.currentAction instanceof EmptyDeckShuffleAction){
            wisdomActive.set(cg, false);
            return;
        }
        ArrayList<AbstractGameAction> actions = new ArrayList<AbstractGameAction>(AbstractDungeon.actionManager.actions);
        for(AbstractGameAction a : actions){
            if(a instanceof EmptyDeckShuffleAction){
                wisdomActive.set(cg, false);
                return;
            }
        }

        AbstractCard temp = card.makeStatEquivalentCopy();
        AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(temp, randomSpot, toBottom));
        wisdomActive.set(cg, false);
    }

    @SpirePatch2(
            clz = DiscardAction.class,
            method = SpirePatch.CLASS
    )
    public static class DiscardActionSpireField {
        public static SpireField<Boolean> atEndOfTurnDiscard = new SpireField<>(() -> false);
    }

    @SpirePatch2(
            clz = DiscardAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {
            AbstractCreature.class, AbstractCreature.class, int.class, boolean.class, boolean.class
            }
    )
    public static class EndOfTurnDiscardActionPatch{


        @SpirePrefixPatch
        public static void whatAmIEvenDoing(DiscardAction __instance) {
            if(AbstractDungeon.actionManager.currentAction instanceof DiscardAtEndOfTurnAction){
                DiscardActionSpireField.atEndOfTurnDiscard.set(__instance, true);
            }
        }
    }

    @SpirePatch2(
            clz = DiscardAction.class,
            method = "update"
    )
    public static class EndOfTurnPreMoveToDiscardPatch{
        @SpireInsertPatch(
                locator=PreMoveToDiscardLocator.class
        )
        public static void prayge(DiscardAction __instance){
            if( DiscardActionSpireField.atEndOfTurnDiscard.get(__instance).equals(true)){
                wisdomActive.set(AbstractDungeon.player.discardPile, true);
            }
        }
    }

    @SpirePatch2(
            clz = DiscardAction.class,
            method = "update"
    )
    public static class EndOfTurnPostMoveToDiscardPatch{
        @SpireInsertPatch(
                locator=PostMoveToDiscardLocator.class
        )
        public static void prayge(DiscardAction __instance){
            if( DiscardActionSpireField.atEndOfTurnDiscard.get(__instance).equals(true)){
                wisdomActive.set(AbstractDungeon.player.discardPile, false);
            }
        }
    }

    @SpirePatch2(
            clz = UseCardAction.class,
            method = "update"
    )
    public static class PlayCardPreMoveToDiscardPatch{
        @SpireInsertPatch(
                locator=PreMoveToDiscardLocator.class
        )
        public static void prayge(UseCardAction __instance){
                wisdomActive.set(AbstractDungeon.player.discardPile, true);
        }
    }

    @SpirePatch2(
            clz = UseCardAction.class,
            method = "update"
    )
    public static class PlayCardPostMoveToDiscardPatch{
        @SpireInsertPatch(
                locator=PostMoveToDiscardLocator.class
        )
        public static void prayge(UseCardAction __instance){
                wisdomActive.set(AbstractDungeon.player.discardPile, false);
        }
    }

    private static class PreMoveToDiscardLocator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(CardGroup.class, "moveToDiscardPile");
            return LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
        }

    }

    private static class PostMoveToDiscardLocator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(CardGroup.class, "moveToDiscardPile");
            int[] result = LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
            for (int i = 0; i < result.length; i++) {
                result[i] = result[i] + 1;
            }
            return result;
        }
    }
}
