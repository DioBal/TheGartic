package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGartic.powers.PlayerFlightPower;
import theGartic.powers.StolenCardPower;

import java.util.ArrayList;

public class StealCardAction extends AbstractGameAction {

    int numToSteal;
    public StealCardAction() {
        actionType = ActionType.CARD_MANIPULATION;
        startDuration = duration = Settings.ACTION_DUR_XFAST;
        numToSteal = 1;


    }

    public StealCardAction(int i) {
        actionType = ActionType.CARD_MANIPULATION;
        startDuration = duration = Settings.ACTION_DUR_XFAST;
        numToSteal = i;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> handList = new ArrayList<AbstractCard>(AbstractDungeon.player.hand.group);
        ArrayList<AbstractCard> toStealList = new ArrayList<AbstractCard>();
        if (numToSteal > handList.size()){ numToSteal = handList.size();}
        for (int i = 0; i<numToSteal; i++) {
            int toRemove = AbstractDungeon.cardRng.random(0, handList.size() - 1);
            AbstractCard cardToSteal = handList.remove(toRemove);
            toStealList.add(cardToSteal);
            AbstractDungeon.player.hand.removeCard(cardToSteal);
            AbstractDungeon.player.limbo.addToBottom(cardToSteal);
            cardToSteal.setAngle(0.0F);
            cardToSteal.targetDrawScale = 0.75F;
            cardToSteal.target_x = (float)Settings.WIDTH / 2.0F;
            cardToSteal.target_y = (float)Settings.HEIGHT / 2.0F;
            cardToSteal.lighten(false);
            cardToSteal.unfadeOut();
            cardToSteal.unhover();
            cardToSteal.untip();
            cardToSteal.stopGlowing();
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StolenCardPower(AbstractDungeon.player, toStealList, 1)));
        }
        for (AbstractCard ca : toStealList){
            addToTop(new ShowCardAction(ca));
        }
        this.isDone = true;
    }
}
