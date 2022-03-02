package theGartic.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class BoxOfCrayons extends AbstractEasyRelic {
    public static final String ID = makeID(BoxOfCrayons.class.getSimpleName());

    private static final int THRESHOLD = 3;

    public BoxOfCrayons() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT);
        counter = 0;
    }

    public void onManualDiscard() {
        counter++;
        if (counter == THRESHOLD) {
            counter = 0;
            flash();
            pulse = false;
            atb(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            atb(new DrawCardAction(1));
        } else if (counter == THRESHOLD - 1) {
            beginPulse();
            pulse = true;
        }
    }

    public void atBattleStart() {
        if (counter == THRESHOLD - 1) {
            beginPulse();
            pulse = true;
        }
    }
}
