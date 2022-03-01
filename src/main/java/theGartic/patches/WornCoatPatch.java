package theGartic.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGartic.cards.fish.AbstractFishCard;
import theGartic.relics.WornCoat;

@SpirePatch(
        clz = CardGroup.class,
        method = "moveToDiscardPile"
)
public class WornCoatPatch {
    public static void Prefix(CardGroup __instance, AbstractCard c) {
        if (c instanceof AbstractFishCard && AbstractDungeon.player.hasRelic(WornCoat.ID))
            ((WornCoat) AbstractDungeon.player.getRelic(WornCoat.ID)).onEnterDiscard(c);
    }
}
