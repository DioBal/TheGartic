package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import theGartic.summons.AbstractSummonOrb;

import static theGartic.util.Wiz.*;

public class DistractingFoxAction extends AbstractGameAction {
	private static final String FOX_NAME = "Fox";

	public DistractingFoxAction(int amount) {
		this.amount = amount;
		actionType = ActionType.POWER;
		startDuration = duration = Settings.ACTION_DUR_XFAST;
	}

	@Override
	public void update() {
		if (duration == startDuration) {
			for (AbstractOrb orb : adp().orbs) {
				if (orb instanceof AbstractSummonOrb) {
					AbstractSummonOrb sorb = (AbstractSummonOrb) orb;
					if (sorb.getClass().getSimpleName().contains(FOX_NAME)) {
						AbstractSummonOrb.unSummon(sorb);
						applyToSelfTop(new MetallicizePower(adp(), amount));
						break;
					}
				}
			}
			isDone = true;
		}
		else {
			isDone = true;
		}
	}
}
