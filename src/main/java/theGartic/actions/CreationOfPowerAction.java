package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.*;
import java.util.stream.Collectors;

public class CreationOfPowerAction extends AbstractGameAction {
    private boolean isUpgraded;
    public CreationOfPowerAction(boolean isUpgraded) {
        this.isUpgraded = isUpgraded;
    }
    
    @Override
    public void update() {
        List<AbstractCard> abstractCardList =
                AbstractDungeon.actionManager.cardsPlayedThisCombat.stream()
                        .filter(abstractCard -> abstractCard.type == AbstractCard.CardType.POWER).collect(Collectors.toList());
        for (AbstractCard card : abstractCardList) {
            AbstractCard addToHand = card.makeCopy();
            if(isUpgraded)
            {
                addToHand.upgrade();
            }
            AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(addToHand));
        }
        this.isDone = true;
    }
}