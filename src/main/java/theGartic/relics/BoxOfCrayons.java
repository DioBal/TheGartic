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
    }

    public void onManualDiscard() {
        counter++;
        if (this.counter == THRESHOLD) {
            this.counter = 0;
            flash();
            this.pulse = false;
            atb(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            atb(new DrawCardAction(1));
        } else if (this.counter == THRESHOLD - 1) {
            beginPulse();
            this.pulse = true;
        }
        flash();
        atb(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    public void atBattleStart() {
        if (this.counter == THRESHOLD - 1) {
            beginPulse();
            this.pulse = true;
        }
    }
}
