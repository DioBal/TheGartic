package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theGartic.powers.PandamoniumPower;
import theGartic.summons.*;

import static theGartic.summons.AbstractSummonOrb.unSummon;
import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.atb;

public class ReplaceBaitAction extends AbstractGameAction {

    public ReplaceBaitAction() {
        actionType = ActionType.SPECIAL;
    }

    @Override
    public void update() {
        RemoveBait();

        int lengthOfSummonsEnum = SummonsAvailableForBaitSummon.values().length;
        int randomSummonChosen;

        //Yes, this do-while is needed, as the random function returning 8
        //happened once while testing.
        do{
            randomSummonChosen = AbstractDungeon.miscRng.random(
                    lengthOfSummonsEnum);
        } while (randomSummonChosen >= lengthOfSummonsEnum);

        SummonsAvailableForBaitSummon enumValue = SummonsAvailableForBaitSummon.
                values()[randomSummonChosen];
        SummonFromBait(enumValue);

        this.isDone = true;
    }

    //magical numbers below for summons
    //were taken from the cards that summon the summons.
    //for TheGartic's polished version, the ideal is that the numbers
    //should be instead variables from those cards.
    private void SummonFromBait(SummonsAvailableForBaitSummon enumValue){
        switch(enumValue){
            case CRAZY_PANDA:
                atb(new SummonOrbAction(new CrazyPanda(10)));
                break;
            case DIRE_WOLF:
                atb(new SummonOrbAction(new DireWolfSummon(2,8)));
                break;
            case FETCHING_FOX:
                atb(new SummonOrbAction(new FetchingFoxSummon(1,1)));
                break;
            case FIRE_IMP:
                atb(new SummonOrbAction(new FireImpSummon(3,1)));
                break;
            case HUNGRY_FOX:
                atb(new SummonOrbAction(new HungryFox(3)));
                break;
            case INARI_WHITE_FOX:
                atb(new SummonOrbAction(new InariWhiteFoxSummon(1)));
                break;
            case MIRRORED_FOX:
                atb(new SummonOrbAction(new MirroredFoxSummon()));
                break;
            case MISCHIEVOUS_FOX:
                atb(new SummonOrbAction(new MischievousFoxSummon(1,2)));
                break;
            default:
                Logger logger = LogManager.getLogger("ReplaceBaitAction");
                logger.info("This line shouldn't being printed. Notify the modders about this!");
                break;
        }
    }

    private void RemoveBait(){
        AbstractPlayer player = adp();
        for (int i = 0; i < player.orbs.size(); i++){
            if (player.orbs.get(i) instanceof BaitSummon){
                unSummon(player.orbs.get(i));
                break;
            }
        }
    }

    private enum SummonsAvailableForBaitSummon{
        CRAZY_PANDA,
        DIRE_WOLF,
        FETCHING_FOX,
        FIRE_IMP,
        HUNGRY_FOX,
        INARI_WHITE_FOX,
        MIRRORED_FOX,
        MISCHIEVOUS_FOX
    }

}
