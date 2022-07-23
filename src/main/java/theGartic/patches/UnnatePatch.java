package theGartic.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import javassist.CtBehavior;
import theGartic.GarticMod;

import java.util.ArrayList;
import java.util.Iterator;

public class UnnatePatch {
    @SpirePatch2(clz = AbstractCard.class, method = SpirePatch.CLASS,
            paramtypez = {String.class, String.class, String.class, int.class, String.class, AbstractCard.CardType.class,
                    AbstractCard.CardColor.class, AbstractCard.CardRarity.class, AbstractCard.CardTarget.class,
                    DamageInfo.DamageType.class})
    public static class UnnateFieldPatch {
        public UnnateFieldPatch() {}

        public static SpireField<Boolean> unnate = new SpireField<Boolean>(() -> { return false; });
    }

    @SpirePatch(clz = CardGroup.class, method = "initializeDeck")
    public static class UnnateInsertPatch {
        @SpireInsertPatch(locator = Locator.class, localvars = {"copy"})
        public static void Insert(CardGroup copy) {
            ArrayList<AbstractCard> placeOnBot = new ArrayList<AbstractCard>();

            for (AbstractCard card : copy.group) {
                if (UnnateFieldPatch.unnate.get(card)) {
                    GarticMod.logger.info("Unnate" + card.name);
                    placeOnBot.add(card);
                }
            }

            if (placeOnBot.size() > 0) {
                for (AbstractCard card : placeOnBot) {
                    copy.removeCard(card);
                    copy.addToBottom(card);
                }
            }
        }
        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(Iterator.class, "hasNext");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}