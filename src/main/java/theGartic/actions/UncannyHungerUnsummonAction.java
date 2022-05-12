package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.cards.UncannyHunger;
import theGartic.summons.AbstractSummonOrb;
import theGartic.util.OrbTargetArrow;
import theGartic.util.OrbTargetScreen;

import static theGartic.util.Wiz.adp;

public class UncannyHungerUnsummonAction extends AbstractGameAction implements OrbTargetArrow.OrbTargetArrowSubscriber {
    private int energyGain;
    private int cardDraw;
    private static String tipString;

    public UncannyHungerUnsummonAction(int magicNumber, int secondMagic) {
        if (tipString == null)
            tipString = UncannyHunger.getTipString();
        energyGain = magicNumber;
        cardDraw = secondMagic;
        actionType = ActionType.POWER;
        startDuration = duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (OrbTargetScreen.Inst.isActive)
            return;
        int summonCount = 0;
        AbstractSummonOrb lastOrb = null;
        for (AbstractOrb orb : adp().orbs) {
            if (orb instanceof AbstractSummonOrb) {
                AbstractSummonOrb sorb = (AbstractSummonOrb) orb;
                if (isAcceptableTarget(sorb)) {
                    summonCount++;
                    lastOrb = sorb;
                }
            }
        }
        if (summonCount == 0) {
            isDone = true;
        } else if (summonCount == 1)
            receiveTargetOrb(adp(), lastOrb);
        else
            OrbTargetScreen.Inst.open(this, tipString);
    }

    @Override
    public void receiveTargetOrb(AbstractCreature source, AbstractSummonOrb orb) {
        orb.unSummon();
        addToBot(new GainEnergyAction(energyGain));
        addToBot(new DrawCardAction(cardDraw));
        isDone = true;
    }

    @Override
    public void end() {
        isDone = true;
    }

    @Override
    public boolean isAcceptableTarget(AbstractSummonOrb orb) {
        return isOrbTarget(orb);
    }

    public static boolean isOrbTarget(AbstractSummonOrb orb) {
        return true;
    }
}
