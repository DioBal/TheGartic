package theGartic.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGartic.TheGartic;
import theGartic.cards.fish.AbstractFishCard;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.shuffleIn;

public class FiberglassRod extends AbstractEasyRelic {
    public static final String ID = makeID(FiberglassRod.class.getSimpleName());

    public FiberglassRod() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheGartic.Enums.GARTIC_COLOR);
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(OldRod.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(OldRod.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public void atTurnStartPostDraw() {
        flash();
        for (int i = 0; i < 2; i++) {
            shuffleIn(AbstractFishCard.returnRandomFish());
        }
    }
}
