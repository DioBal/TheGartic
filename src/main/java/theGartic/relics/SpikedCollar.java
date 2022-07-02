package theGartic.relics;

import theGartic.TheGartic;
import theGartic.actions.PickNewSummonToAddToPartyAction;
import theGartic.cards.fish.AbstractFishCard;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;
import static theGartic.util.Wiz.shuffleIn;

public class SpikedCollar extends AbstractEasyRelic {
    public static final String ID = makeID(SpikedCollar.class.getSimpleName());

    public SpikedCollar() {
        super(ID, RelicTier.UNCOMMON, LandingSound.CLINK, TheGartic.Enums.GARTIC_COLOR);
        counter = 1;
    }

    @Override
    public void atBattleStart() {
        if(counter == 1)
        {
            flash();
            atb(new PickNewSummonToAddToPartyAction(3));
            counter = -2;
        }
    }
}
