package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGartic.cards.ClearIntention;

import java.util.ArrayList;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.att;

public class ClearIntentionAction extends AbstractGameAction {
    public static final String UI_KEY = makeID(ClearIntention.class.getSimpleName());
    public static final String[] TEXT;
    private int numCopies;

    public ClearIntentionAction(int copies) {
        actionType = ActionType.CARD_MANIPULATION;
        duration = startDuration = Settings.ACTION_DUR_FAST;
        numCopies = copies;
    }

    public void update() {
        if (adp().drawPile.isEmpty()) {
            isDone = true;
            return;
        }
        if (duration == startDuration) {
            if (adp().drawPile.size() == 1) {
                AbstractCard cardToCopy = adp().drawPile.group.get(0);

                discardHand();
                makeCopies(cardToCopy);
                isDone = true;
            } else {
                CardGroup temp = new CardGroup(CardGroupType.UNSPECIFIED);
                temp.group.addAll(adp().drawPile.group);

                temp.sortAlphabetically(true);
                temp.sortByRarityPlusStatusCardType(false);

                AbstractDungeon.gridSelectScreen.open(temp, 1, TEXT[0], false);

                tickDuration();
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                AbstractCard card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);

                discardHand();
                makeCopies(card);

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                adp().hand.refreshHandLayout();
            }

            tickDuration();
        }
    }

    private void discardHand() {
        ArrayList<AbstractCard> temp = new ArrayList<>(adp().drawPile.group);
        for (AbstractCard card : temp)
            adp().drawPile.moveToDiscardPile(card);
    }

    private void makeCopies(AbstractCard card) {
        att(new MakeTempCardInDrawPileAction(card, numCopies, false, false));
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString(UI_KEY).TEXT;
    }
}