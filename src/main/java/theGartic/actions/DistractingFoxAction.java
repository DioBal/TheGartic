package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import theGartic.cards.DistractingFox;
import theGartic.summons.AbstractSummonOrb;
import theGartic.util.OrbTargetArrow;
import theGartic.util.OrbTargetScreen;

import static theGartic.util.Wiz.*;

public class DistractingFoxAction extends AbstractGameAction implements OrbTargetArrow.OrbTargetArrowSubscriber {
	private String tipString;

	public DistractingFoxAction(int amount, String tipString) {
		this.amount = amount;
		this.tipString = tipString;
		actionType = ActionType.POWER;
		startDuration = duration = Settings.ACTION_DUR_XFAST;
	}

	@Override
	public void update() {
		if (OrbTargetScreen.Inst.isActive)
			return;
		int foxCount = 0;
		AbstractSummonOrb lastOrb = null;
		for (AbstractOrb orb : adp().orbs) {
			if (orb instanceof AbstractSummonOrb) {
				AbstractSummonOrb sorb = (AbstractSummonOrb) orb;
				if (isAcceptableTarget(sorb)) {
					foxCount++;
					lastOrb = sorb;
				}
			}
		}
		if (foxCount == 0) {
			isDone = true;
		}
		else if (foxCount == 1) {
			lastOrb.unSummon();
			applyToSelfTop(new MetallicizePower(adp(), amount));
			isDone = true;
		} else
			OrbTargetScreen.Inst.open(this, tipString);
	}

	@Override
	public void receiveTargetOrb(AbstractCreature source, AbstractSummonOrb orb) {
		orb.unSummon();
		applyToSelfTop(new MetallicizePower(adp(), amount));
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
		return orb.getClass().getSimpleName().contains(DistractingFox.FOX_STRING);
	}
}
