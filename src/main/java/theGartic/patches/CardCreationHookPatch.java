package theGartic.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import theGartic.powers.OnCardCreationPower;

import static theGartic.util.Wiz.adp;

public class CardCreationHookPatch {
    @SpirePatch(clz = ShowCardAndAddToDiscardEffect.class, method = "update")
    @SpirePatch(clz = ShowCardAndAddToHandEffect.class, method = "update")
    @SpirePatch(clz = ShowCardAndAddToDrawPileEffect.class, method = "update")
    public static class CallHook
    {
        public static SpireReturn Prefix(AbstractGameEffect __instance, float ___EFFECT_DUR, AbstractCard ___card)
        {
            if (__instance.duration == ___EFFECT_DUR) {
                if (adp() == null)
                    return SpireReturn.Continue();
                boolean create = true;
                for (AbstractPower pow : adp().powers) {
                    if (pow instanceof OnCardCreationPower) {
                        boolean foo = ((OnCardCreationPower) pow).onCardCreation(___card);
                        if (!foo)
                            create = false;
                    }
                }
                if (!create)
                    return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }


    @SpirePatch2(
            clz = AbstractCard.class,
            method = "<class>"
    )
    public static class CelestialCraftingField {
        public static SpireField<Boolean> makeCopy = new SpireField(() -> true);
    }
}