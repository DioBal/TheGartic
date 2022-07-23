package theGartic.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.actions.defect.ShuffleAllAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGartic.powers.OnShufflePower;

import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.atb;

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
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {CardGroup.class, boolean.class}
    )
    public static class ShuffleHookForPowers {
        @SpirePostfixPatch
        public static void postfix() {
            for (AbstractPower pow : adp().powers) {
                if (pow instanceof OnShufflePower)
                    atb(new AbstractGameAction() {
                        @Override
                        public void update() {
                            ((OnShufflePower) pow).onShuffle();
                            isDone = true;
                        }
                    });
            }
        }
    }
}
