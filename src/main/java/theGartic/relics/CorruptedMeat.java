package theGartic.relics;

import theGartic.GarticMod;
import theGartic.cards.SupplicateToInari;

import static theGartic.GarticMod.makeID;

public class CorruptedMeat extends AbstractEasyRelic {

    public static final String ID = GarticMod.makeID(CorruptedMeat.class.getSimpleName());
    public static final RelicTier RELIC_TIER = RelicTier.BOSS;
    public static final LandingSound LANDING_SOUND = LandingSound.MAGICAL;

    public static boolean summon_small_spire_growth;

    public CorruptedMeat() {
        super(ID, RELIC_TIER, LANDING_SOUND);
        summon_small_spire_growth = false;
    }

    @Override
    public void atBattleStart() {
        summon_small_spire_growth = true;
    }

    @Override
    public void onPlayerEndTurn() {
        if (summon_small_spire_growth){
            
        }
    }

}
