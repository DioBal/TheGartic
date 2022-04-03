package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import theGartic.powers.InvisibleSummonPower;
import theGartic.summons.CrazyPanda;
import theGartic.summons.DireWolfSummon;
import theGartic.summons.HungryFox;
import theGartic.summons.InariWhiteFoxSummon;

public class SummonOrbAction extends AbstractGameAction
{
    private AbstractOrb summon;
    int stack = 1;

    public SummonOrbAction(AbstractOrb summonOrb, int stackAmount)
    {
        actionType = ActionType.SPECIAL;
        duration = Settings.ACTION_DUR_FAST;
        summon = summonOrb;
        this.stack = stackAmount;
    }

    public SummonOrbAction(AbstractOrb summonOrb) {
        this(summonOrb, 0);
    }

    public void update()
    {
        if(summon == null)
        {
            isDone = true;
            return;
        }

        if(!AbstractDungeon.player.hasPower(InvisibleSummonPower.POWER_ID))
            AbstractDungeon.player.powers.add(new InvisibleSummonPower(AbstractDungeon.player, AbstractDungeon.player, 1));

        if(!CheckForStack())
            AbstractDungeon.player.increaseMaxOrbSlots(1, false);
//        Random rand = new Random();
//        if(rand.randomBoolean())
//            addToTop(new SFXAction("theVacant:gemSpawn"));
//        else
//            addToTop(new SFXAction("theVacant:gemSpawn2"));

        isDone = true;
    }

    private boolean CheckForStack()
    {
        for (AbstractOrb orb : AbstractDungeon.player.orbs)
        {
            if(orb.name == summon.name && !orb.ID.equals(CrazyPanda.ORB_ID) && !orb.ID.equals(HungryFox.ORB_ID) && !orb.ID.equals(DireWolfSummon.ORB_ID))
            {
                orb.evokeAmount += stack;
                AbstractDungeon.actionManager.addToBottom(
                        new VFXAction(new OrbFlareEffect(orb, OrbFlareEffect.OrbFlareColor.DARK), 0.1f));
                return true;
            }
            if (orb.name == summon.name && orb.ID.equals(InariWhiteFoxSummon.ORB_ID)){
                ((InariWhiteFoxSummon)orb).passiveAmount += orb.passiveAmount;
                AbstractDungeon.actionManager.addToBottom(
                        new VFXAction(new OrbFlareEffect(orb, OrbFlareEffect.OrbFlareColor.PLASMA), 0.2f));
                return true;
            }
            if(orb.name == summon.name && orb.ID.equals(DireWolfSummon.ORB_ID))
            {
                orb.passiveAmount += stack;
                AbstractDungeon.actionManager.addToBottom(
                        new VFXAction(new OrbFlareEffect(orb, OrbFlareEffect.OrbFlareColor.DARK), 0.1f));
                return true;
            }
        }
        addToTop(new ChannelAction(summon, false));
        return false;
    }
}
