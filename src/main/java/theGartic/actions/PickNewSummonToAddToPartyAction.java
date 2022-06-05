package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import theGartic.cards.summonOptions.AbstractSummonOption;
import theGartic.relics.PartyRelic;

import java.util.ArrayList;

import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.att;

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
        ArrayList<AbstractCard> summonChoices =
                AbstractSummonOption.returnRandomSummons(false, true, amount);
        att(new AbstractGameAction() {
            @Override
            public void update() {
                PartyRelic relic = (PartyRelic) adp().getRelic(PartyRelic.ID);
                if (relic != null)
                    relic.resetDescription();
                isDone = true;
            }
        });
        att(new ChooseOneAction(summonChoices));
        isDone = true;
    }
}
