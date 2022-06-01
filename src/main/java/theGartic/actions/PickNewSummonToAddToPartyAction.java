package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import theGartic.cards.summonOptions.AbstractSummonOption;

import java.util.ArrayList;

public class PickNewSummonToAddToPartyAction extends AbstractGameAction
{
    public PickNewSummonToAddToPartyAction(int num)
    {
        actionType = AbstractGameAction.ActionType.SPECIAL;
        startDuration = duration = Settings.ACTION_DUR_XFAST;
        amount = num;
    }

    public void update()
    {
        ArrayList<AbstractCard> summonChoices = new ArrayList<>();
        for (int i = 0; i < amount; ++i)
            summonChoices.add(AbstractSummonOption.returnRandomSummon(false, true));
        addToBot(new ChooseOneAction(summonChoices));
        isDone = true;
    }
}
