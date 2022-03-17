package theGartic.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
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

import javax.swing.*;
import java.util.ArrayList;

@SpirePatch2(
        clz = CardGroup.class,
        method = "addToTop"
)
public class WisdomPatches {

    public static SpireField<Boolean> wisdomActive = new SpireField<>(() -> false);

    @SpirePostfixPatch
    public void PlsWork(CardGroup __instance, AbstractCard c) {
        if(!AbstractDungeon.player.stance.ID.equals(WisdomStance.STANCE_ID) || wisdomActive.get(__instance).equals(false)){
            return;
        }

        if(__instance.type==CardGroup.CardGroupType.DISCARD_PILE){
            wisdomActive.set(__instance, true);
            onAddToDiscardPile(__instance, c);
        } else if (__instance.type==CardGroup.CardGroupType.DRAW_PILE){
            wisdomActive.set(__instance, true);
            onAddToDrawPile(__instance, c);
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

    public static void onAddToDrawPile(CardGroup cg, AbstractCard card){
        //check for deck shuffle
        ArrayList<AbstractGameAction> actions = new ArrayList<AbstractGameAction>(AbstractDungeon.actionManager.actions);
        for(AbstractGameAction a : actions){
            if(a instanceof EmptyDeckShuffleAction){
                wisdomActive.set(cg, false);
                return;
            }
        }

        AbstractCard temp = card.makeStatEquivalentCopy();
        AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(temp, false, false));
        wisdomActive.set(cg, false);
    }
}
