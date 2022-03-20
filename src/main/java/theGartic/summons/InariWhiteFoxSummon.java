package theGartic.summons;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.GarticMod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static theGartic.GarticMod.makeOrbPath;

public class InariWhiteFoxSummon extends AbstractSummonOrb
{
    public static final String ORB_ID = GarticMod.makeID(InariWhiteFoxSummon.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static int BASE_PASSIVE_AMOUNT = 1, BASE_STACK = 1;
    private enum InariChoices{
        IF_YOU_PLAY_A_CARD_DRAW_A_CARD,
        GAIN_E,
        ADD_COLORLESS_WITH_EXHAUST_TO_TOP_OF_DRAW_PILE,
        APPLY_WEAK_TO_ALL_ENEMIES,
        APPLY_VULNERABLE_TO_ALL_ENEMIES,
        GAIN_BLOCK,
        DEAL_DAMAGE_TO_THE_LOWEST_HEALTH_ENEMY,
        DEAL_DAMAGE_TO_THE_HIGHEST_HEALTH_ENEMY,
        GAIN_TEMP_HP
    }
    private static ArrayList<InariChoices> inariChoicesDeck;
    private static int optionsShown = 0;

    public InariWhiteFoxSummon(int amount, int stack)
    {
        super(ORB_ID, orbString.NAME, amount, stack, makeOrbPath("MischievousFox.png"));
        inariChoicesDeck = new ArrayList<InariChoices>();
        initInariChoicesDeck();
        shuffleInariChoicesDeck();
    }

    private void initInariChoicesDeck(){
        inariChoicesDeck.add(InariChoices.IF_YOU_PLAY_A_CARD_DRAW_A_CARD);
        inariChoicesDeck.add(InariChoices.GAIN_E);
        inariChoicesDeck.add(InariChoices.ADD_COLORLESS_WITH_EXHAUST_TO_TOP_OF_DRAW_PILE);
        inariChoicesDeck.add(InariChoices.APPLY_WEAK_TO_ALL_ENEMIES);
        inariChoicesDeck.add(InariChoices.APPLY_VULNERABLE_TO_ALL_ENEMIES);
        inariChoicesDeck.add(InariChoices.GAIN_BLOCK);
        inariChoicesDeck.add(InariChoices.DEAL_DAMAGE_TO_THE_LOWEST_HEALTH_ENEMY);
        inariChoicesDeck.add(InariChoices.DEAL_DAMAGE_TO_THE_HIGHEST_HEALTH_ENEMY);
        inariChoicesDeck.add(InariChoices.GAIN_TEMP_HP);
    }

    private void shuffleInariChoicesDeck(){
        Collections.shuffle(inariChoicesDeck,AbstractDungeon.cardRandomRng.random);
    }

    public void onEndOfTurn() {
        if (optionsShown >= inariChoicesDeck.size()){
            optionsShown = 0;
            shuffleInariChoicesDeck();
        }

        //Call for the three choices on the inariChoicesDeck
        //the player picks an option
        //option does thing

    }



    @Override
    public void updateDescription()
    {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new InariWhiteFoxSummon(passiveAmount, evokeAmount);
    }
}
