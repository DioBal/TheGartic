package theGartic.relics;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import theGartic.actions.SummonOrbAction;
import theGartic.summons.AbstractSummonOrb;
import theGartic.summons.BaitSummon;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.adp;
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
        summonBait();
    }

    private void summonBait(){
        AbstractPlayer player = adp();
        if (player.orbs.size() == 0){
            atb(new SummonOrbAction(new BaitSummon()));
        }
        else
        {
            boolean noSummons = true;
            for(int i = 0; i < player.orbs.size(); i++){
                if (player.orbs.get(i) instanceof AbstractSummonOrb){
                    noSummons = false;
                    break;
                }
            }
            if (noSummons){
                atb(new SummonOrbAction(new BaitSummon()));
            }
        }
    }


}
