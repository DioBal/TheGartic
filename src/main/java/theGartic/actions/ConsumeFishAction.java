package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theGartic.cards.fish.AbstractFishCard;

import java.util.ArrayList;
import java.util.Iterator;

public class ConsumeFishAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private static final float DURATION_PER_CARD = 0.25F;
    private AbstractPlayer p;
    private int consumeAmount;
    private ArrayList<AbstractCard> notFish = new ArrayList();

    public ConsumeFishAction(int amount) {
        this.setValues(AbstractDungeon.player, source, amount);// 26
        this.actionType = ActionType.DRAW;// 27
        this.duration = 0.25F;// 28
        this.p = AbstractDungeon.player;// 29
        this.consumeAmount = amount;// 30
    }// 31

    public void update() {
        Iterator var1;
        AbstractCard c;
        if (this.duration == Settings.ACTION_DUR_FAST) {// 36
            var1 = this.p.hand.group.iterator();// 38

            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                if (!this.isFish(c)) {// 39
                    this.notFish.add(c);// 40
                }
            }

            if (this.notFish.size() == this.p.hand.group.size()) {// 45
                this.isDone = true;// 46
                return;// 47
            }

            if (this.p.hand.group.size() - this.notFish.size() == 1) {// 50
                var1 = this.p.hand.group.iterator();// 51

                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    if (this.isFish(c)) {// 52
                        for(int i = 0; i < this.consumeAmount; ++i) {// 53
                            this.p.hand.moveToExhaustPile(c);
                        }

                        this.isDone = true;// 56
                        return;// 57
                    }
                }
            }

            this.p.hand.group.removeAll(this.notFish);// 64
            if (this.p.hand.group.size() > 1) {// 66
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], consumeAmount, false, false, false, false);// 67
                this.tickDuration();// 68
                return;// 69
            }

            if (this.p.hand.group.size() == 1) {// 70
                for(int i = 0; i < this.consumeAmount; ++i) {// 71
                    this.p.hand.moveToExhaustPile(p.hand.getTopCard());
                }

                this.returnCards();// 74
                this.isDone = true;// 75
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {// 80
            var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();// 81

            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                this.p.hand.moveToExhaustPile(c);// 82
            }

            this.returnCards();// 88
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;// 90
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();// 91
            this.isDone = true;// 92
        }

        this.tickDuration();// 95
    }// 96

    private void returnCards() {
        Iterator var1 = this.notFish.iterator();// 99

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            this.p.hand.addToTop(c);// 100
        }

        this.p.hand.refreshHandLayout();// 102
    }// 103

    private boolean isFish(AbstractCard card) {
        return card instanceof AbstractFishCard;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");// 16
        TEXT = uiStrings.TEXT;// 17
    }
}
