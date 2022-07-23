package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import theGartic.powers.InvisibleSummonPower;
import theGartic.powers.OnSummonPower;
import theGartic.summons.*;

import static theGartic.util.Wiz.*;

public class SummonOrbAction extends AbstractGameAction
{
    private AbstractSummonOrb summon;
    int stack = 1;

    public SummonOrbAction(AbstractSummonOrb summonOrb, int stackAmount)
    {
        actionType = ActionType.SPECIAL;
        duration = Settings.ACTION_DUR_FAST;
        summon = summonOrb;
        this.stack = stackAmount;
    }

    public SummonOrbAction(AbstractSummonOrb summonOrb) {
        this(summonOrb, 0);
    }

    public void update()
    {
        if(summon == null)
        {
            isDone = true;
            return;
        }

        if(!adp().hasPower(InvisibleSummonPower.POWER_ID))
            adp().powers.add(new InvisibleSummonPower(adp(), adp(), 1));

        addToTop(new ChannelAction(summon, false));
        AbstractDungeon.player.increaseMaxOrbSlots(1, false);

        if (adp() != null)
            for (AbstractPower pow : adp().powers)
                if (pow instanceof OnSummonPower)
                    ((OnSummonPower) pow).onSummon(summon);

        isDone = true;
    }
/*
    private boolean CheckForStack()
    {
        for (AbstractOrb orb : AbstractDungeon.player.orbs)
        {
            if(orb.name.equals(summon.name) && !orb.ID.equals(InariWhiteFoxSummon.ORB_ID)
                    && !orb.ID.equals(CrazyPanda.ORB_ID) && !orb.ID.equals(HungryFox.ORB_ID) && !orb.ID.equals(DireWolfSummon.ORB_ID))
            {
                orb.evokeAmount += stack;
                vfx(new OrbFlareEffect(orb, OrbFlareEffect.OrbFlareColor.DARK), 0.1f);
                return true;
            }

            if(orb.name.equals(summon.name) && orb.ID.equals(DireWolfSummon.ORB_ID))
            {
                orb.passiveAmount += stack;
                vfx(new OrbFlareEffect(orb, OrbFlareEffect.OrbFlareColor.DARK), 0.1f);
                return true;
            }
        }
        att(new ChannelAction(summon, false));
        return false;
    }*/
}
