package theGartic.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DiscardAtEndOfTurnAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
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
}
