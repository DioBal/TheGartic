package theGartic.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGartic.GarticMod;
import theGartic.actions.SummonOrbAction;
import theGartic.cards.SupplicateToInari;
import theGartic.summons.InariWhiteFoxSummon;
import theGartic.summons.SmallSpireGrowthSummon;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class CorruptedMeat extends AbstractEasyRelic {

    public static final String ID = GarticMod.makeID(CorruptedMeat.class.getSimpleName());
    public static final RelicTier RELIC_TIER = RelicTier.BOSS;
    public static final LandingSound LANDING_SOUND = LandingSound.MAGICAL;

    public static boolean summon_small_spire_growth;

    public CorruptedMeat() {
        super(ID, RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void atBattleStart() {
        atb (new SummonOrbAction(new SmallSpireGrowthSummon()));
    }

}
