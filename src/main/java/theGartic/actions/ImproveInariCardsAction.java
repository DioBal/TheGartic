package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import theGartic.summons.InariWhiteFoxSummon;

public class ImproveInariCardsAction extends AbstractGameAction {

    int amount_to_add = 1;
    InariWhiteFoxSummon summon;

    public ImproveInariCardsAction(InariWhiteFoxSummon summon){
        this.summon = summon;
    }

    @Override
    public void update() {
        if (!this.isDone){
            summon.improveInariCards(1);
            this.isDone = true;
        }
    }
}
