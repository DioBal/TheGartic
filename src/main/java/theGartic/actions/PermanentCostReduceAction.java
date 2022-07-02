package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.UUID;

public class PermanentCostReduceAction extends AbstractGameAction {
    private int reduceAmount;
    private UUID uuid;

    public PermanentCostReduceAction(UUID targetUUID, int reduceAmount) {
        this.reduceAmount = reduceAmount;
        this.uuid = targetUUID;
    }

    public void update() {
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (!c.uuid.equals(this.uuid))
                continue;
            int diff = c.costForTurn - c.cost;// 872
            c.cost -= reduceAmount;// 873
            if (c.costForTurn > 0) {// 875
                c.costForTurn = c.cost + diff;// 876
            }

            if (c.costForTurn < 0) {// 878
                c.costForTurn = 0;// 879
            }

            c.upgradedCost = true;// 881
            c.applyPowers();
            //c.isCostModified = false;
        }
        for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
            int diff = c.costForTurn - c.cost;// 872
            c.cost -= reduceAmount;// 873
            if (c.costForTurn > 0) {// 875
                c.costForTurn = c.cost + diff;// 876
            }

            if (c.costForTurn < 0) {// 878
                c.costForTurn = 0;// 879
            }

            c.upgradedCost = true;// 881
            c.applyPowers();
            c.upgradedCost = true;
            //c.isCostModified = false;
        }
        this.isDone = true;
    }
}
