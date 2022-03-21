package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.WhiteNoise;
import com.megacrit.cardcrawl.cards.colorless.JackOfAllTrades;
import com.megacrit.cardcrawl.cards.green.Distraction;
import com.megacrit.cardcrawl.cards.red.InfernalBlade;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreationOfCreationAction extends AbstractGameAction {
    private boolean isUpgraded;
    public CreationOfCreationAction(boolean isUpgraded) {
        this.isUpgraded = isUpgraded;
    }
    
    @Override
    public void update() {
        AbstractCard card;
        List<AbstractCard> choices = Arrays.asList(new InfernalBlade(), new Distraction(), new WhiteNoise(), new JackOfAllTrades());
        card = choices.get(AbstractDungeon.cardRandomRng.random(3)).makeCopy();
        if(isUpgraded)
        {
            card.upgrade();
        }
        this.addToBot(new MakeTempCardInHandAction(card));
        isDone = true;
    }
}
