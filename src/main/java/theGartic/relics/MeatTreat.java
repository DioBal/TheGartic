package theGartic.relics;

import theGartic.actions.SummonOrbAction;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class MeatTreat extends AbstractEasyRelic {

    public static final String ID = makeID(MeatTreat.class.getSimpleName());
    public static final RelicTier RELIC_TIER = RelicTier.RARE;
    public static final LandingSound LANDING_SOUND = LandingSound.MAGICAL;

    public MeatTreat() {
        super(ID, RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public void onPlayerEndTurn() {

        atb(new SummonOrbAction(new BaitSummon()));

    }


}
