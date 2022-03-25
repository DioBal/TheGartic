package theGartic.summons;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.GarticMod;
import theGartic.actions.EasyModalChoiceAction;
import theGartic.cards.EasyModalChoiceCard;
import theGartic.cards.InariModal.InariDash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static theGartic.GarticMod.makeOrbPath;
import static theGartic.util.Wiz.atb;

public class InariWhiteFoxSummon extends AbstractSummonOrb
{
    public static final String ORB_ID = GarticMod.makeID(InariWhiteFoxSummon.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static int BASE_PASSIVE_AMOUNT = 1, BASE_STACK = 1;
    private static ArrayList<EasyModalChoiceCard> inariChoicesDeck;
    private static int optionsShown = 0;

    public InariWhiteFoxSummon(int amount, int stack)
    {
        super(ORB_ID, orbString.NAME, amount, stack, makeOrbPath("MischievousFox.png"));
        inariChoicesDeck = new ArrayList<EasyModalChoiceCard>();
        initInariChoicesDeck();
        shuffleInariChoicesDeck();
    }

    private void initInariChoicesDeck(){
        inariChoicesDeck.add(new InariDash(BASE_PASSIVE_AMOUNT));
        inariChoicesDeck.add(new InariDash(BASE_PASSIVE_AMOUNT));
        inariChoicesDeck.add(new InariDash(BASE_PASSIVE_AMOUNT));
        inariChoicesDeck.add(new InariDash(BASE_PASSIVE_AMOUNT));
        inariChoicesDeck.add(new InariDash(BASE_PASSIVE_AMOUNT));
        inariChoicesDeck.add(new InariDash(BASE_PASSIVE_AMOUNT));
        inariChoicesDeck.add(new InariDash(BASE_PASSIVE_AMOUNT));
        inariChoicesDeck.add(new InariDash(BASE_PASSIVE_AMOUNT));
        inariChoicesDeck.add(new InariDash(BASE_PASSIVE_AMOUNT));
        /*inariChoicesDeck.add(InariChoices.GAIN_E);
        inariChoicesDeck.add(InariChoices.ADD_COLORLESS_WITH_EXHAUST_TO_TOP_OF_DRAW_PILE);
        inariChoicesDeck.add(InariChoices.APPLY_WEAK_TO_ALL_ENEMIES);
        inariChoicesDeck.add(InariChoices.APPLY_VULNERABLE_TO_ALL_ENEMIES);
        inariChoicesDeck.add(InariChoices.GAIN_BLOCK);
        inariChoicesDeck.add(InariChoices.DEAL_DAMAGE_TO_THE_LOWEST_HEALTH_ENEMY);
        inariChoicesDeck.add(InariChoices.DEAL_DAMAGE_TO_THE_HIGHEST_HEALTH_ENEMY);
        inariChoicesDeck.add(InariChoices.GAIN_TEMP_HP);*/
    }

    private void shuffleInariChoicesDeck(){
        Collections.shuffle(inariChoicesDeck,AbstractDungeon.cardRandomRng.random);
    }

    public void onEndOfTurn() {
        if (optionsShown >= inariChoicesDeck.size()){
            optionsShown = 0;
            shuffleInariChoicesDeck();
        }

        ArrayList<AbstractCard> choiceCardList = new ArrayList<>();
        for (int i = optionsShown * 3; i < optionsShown * 3 + 3; i++){
            choiceCardList.add(inariChoicesDeck.get(i));
        }
        atb(new EasyModalChoiceAction(choiceCardList));


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
