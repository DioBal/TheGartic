package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.summons.AbstractSummonOrb;
import theGartic.summons.DireWolfSummon;
import theGartic.util.OrbTargetArrow;
import theGartic.util.OrbTargetScreen;

import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.att;

public class DireWolfSummonAction extends AbstractGameAction implements OrbTargetArrow.OrbTargetArrowSubscriber {
    private final int magicNumber;
    private final int secondMagic;
    private String tipString;

    public DireWolfSummonAction(String tipString, int magicNumber, int secondMagic) {
        this.tipString = tipString;
        this.magicNumber = magicNumber;
        this.secondMagic = secondMagic;
        actionType = ActionType.POWER;
        startDuration = duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        int summonCount = 0;
        AbstractSummonOrb lastOrb = null;
        for (AbstractOrb orb : adp().orbs) {
            if (orb instanceof AbstractSummonOrb) {
                AbstractSummonOrb sorb = (AbstractSummonOrb) orb;
                summonCount++;
                lastOrb = sorb;
            }
        }
        if (summonCount == 0) {
            isDone = true;
            return;
        }
        else if (summonCount == 1) {
            lastOrb.unSummon();
            addToBot(new SummonOrbAction(new DireWolfSummon(magicNumber, secondMagic), magicNumber));
        } else
            OrbTargetScreen.Inst.open(this, tipString);

        if(OrbTargetScreen.Inst.isActive && !OrbTargetArrow.isActive)
            isDone = true;
    }

    @Override
    public void receiveTargetOrb(AbstractCreature source, AbstractSummonOrb orb) {
        orb.unSummon();
        att(new SummonOrbAction(new DireWolfSummon(magicNumber, secondMagic), magicNumber));
        isDone = true;
    }

    @Override
    public void end() {
        isDone = true;
    }

    public static boolean isOrbTarget(AbstractSummonOrb orb) {
        return true;
    }

    @Override
    public boolean isAcceptableTarget(AbstractSummonOrb orb) {
        return isOrbTarget(orb);
    }
}
