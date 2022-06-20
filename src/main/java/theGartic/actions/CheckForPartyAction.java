package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.relics.PartyRelic;
import theGartic.summons.AbstractSummonOrb;
import theGartic.util.OrbTargetArrow;
import theGartic.util.OrbTargetScreen;

import static theGartic.util.Wiz.adp;

public class CheckForPartyAction extends AbstractGameAction
{

    public CheckForPartyAction()
    {
        actionType = ActionType.SPECIAL;
        startDuration = duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update()
    {
        if(!adp().hasRelic(PartyRelic.ID))
        {
            adp().relics.add(new PartyRelic());
            adp().reorganizeRelics();
        }
        isDone = true;
    }
}
