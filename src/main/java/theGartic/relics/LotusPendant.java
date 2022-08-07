package theGartic.relics;

import com.megacrit.cardcrawl.powers.watcher.MantraPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import theGartic.TheGartic;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.applyToSelf;

public class LotusPendant extends AbstractEasyRelic {
    public static final String ID = makeID(LotusPendant.class.getSimpleName());

    public LotusPendant() {
        super(ID, RelicTier.RARE, LandingSound.CLINK, TheGartic.Enums.GARTIC_COLOR);
    }

    @Override
    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
        if (!newStance.ID.equals(NeutralStance.STANCE_ID))
            applyToSelf(new MantraPower(adp(), 1));
    }
}
