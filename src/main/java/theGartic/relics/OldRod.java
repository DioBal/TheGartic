package theGartic.relics;

import theGartic.TheGartic;
import theGartic.fish.FishHelper;
import theGartic.util.Wiz;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.shuffleIn;

public class OldRod extends AbstractEasyRelic {
    public static final String ID = makeID("OldRod");

    public OldRod() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheGartic.Enums.GARTIC_COLOR);
    }

    @Override
    public void atBattleStart() {
        flash();
        for (int i = 0; i < 2; i++) {
            shuffleIn(FishHelper.returnRandomFish());
        }
    }
}
