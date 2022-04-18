package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import theGartic.cards.DistractingFox;
import theGartic.summons.AbstractSummonOrb;
import theGartic.util.OrbTargetArrow;
import theGartic.util.OrbTargetScreen;

import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.applyToSelfTop;

public class UncannyHungerUnsummonAction extends AbstractGameAction implements OrbTargetArrow.OrbTargetArrowSubscriber {
    private int magicNumber;
    private int secondMagic;
    private String tipString;

    public UncannyHungerUnsummonAction(String tipString, int magicNumber, int secondMagic) {
        this.tipString = tipString;
        this.magicNumber = magicNumber;
        this.secondMagic = secondMagic;
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
        }
        else
            OrbTargetScreen.Inst.open(this, tipString);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public void receiveTargetOrb(AbstractCreature source, AbstractSummonOrb orb) {
        orb.unSummon();
        addToBot(new GainEnergyAction(magicNumber));
        addToBot(new DrawCardAction(AbstractDungeon.player,secondMagic));
        isDone = true;
    }

    @Override
    public void end() {
        isDone = true;
    }

    @Override
    public boolean isAcceptableTarget(AbstractSummonOrb orb) {
        return orb.getClass().getSimpleName().contains(DistractingFox.FOX_STRING);
    }

    public static boolean isOrbTarget(AbstractSummonOrb orb) {
        return orb instanceof AbstractSummonOrb;
    }
}
