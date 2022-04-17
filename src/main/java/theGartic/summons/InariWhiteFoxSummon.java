package theGartic.summons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.GarticMod;
import theGartic.actions.EasyModalChoiceAction;
import theGartic.cards.EasyModalChoiceCard;
import theGartic.cards.InariModal.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.lwjgl.util.mapped.MappedObject.foreach;
import static theGartic.GarticMod.makeOrbPath;
import static theGartic.util.Wiz.atb;

public class InariWhiteFoxSummon extends AbstractSummonOrb
{
    public static final String ORB_ID = GarticMod.makeID(InariWhiteFoxSummon.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static int BASE_PASSIVE_AMOUNT = 1;
    private static ArrayList<EasyModalChoiceCard> inariChoicesDeck;
    private static int optionsShown = 0;

    public InariWhiteFoxSummon(int amount)
    {
        super(ORB_ID, orbString.NAME, amount, 0, makeOrbPath("MischievousFox.png"));
        inariChoicesDeck = new ArrayList<EasyModalChoiceCard>();
        initInariChoicesDeck();
        shuffleInariChoicesDeck();
    }

    private void initInariChoicesDeck(){
        inariChoicesDeck.add(new InariDash(BASE_PASSIVE_AMOUNT));
        inariChoicesDeck.add(new InariEnergy(BASE_PASSIVE_AMOUNT));
        inariChoicesDeck.add(new InariEndurance(BASE_PASSIVE_AMOUNT));
        inariChoicesDeck.add(new InariPloy(BASE_PASSIVE_AMOUNT));
        inariChoicesDeck.add(new InariProtection(BASE_PASSIVE_AMOUNT));
        inariChoicesDeck.add(new InariFlames(BASE_PASSIVE_AMOUNT));
        inariChoicesDeck.add(new InariSabotage(BASE_PASSIVE_AMOUNT));
        inariChoicesDeck.add(new InariBarrage(BASE_PASSIVE_AMOUNT));
        inariChoicesDeck.add(new InariGift(BASE_PASSIVE_AMOUNT));
    }

    private void shuffleInariChoicesDeck(){
        Collections.shuffle(inariChoicesDeck,AbstractDungeon.cardRandomRng.random);
    }

    @Override
    protected void renderText(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(passiveAmount),
                cX + NUM_X_OFFSET, cY + NUM_Y_OFFSET, c, fontScale);
    }


    public void onEndOfTurn() {
        if (optionsShown >= inariChoicesDeck.size()/3){
            optionsShown = 0;
            shuffleInariChoicesDeck();
        }

        ArrayList<AbstractCard> choiceCardList = new ArrayList<>();
        for (int i = optionsShown * 3; i < optionsShown * 3 + 3; i++){
            choiceCardList.add(inariChoicesDeck.get(i));
        }
        atb(new EasyModalChoiceAction(choiceCardList));

        optionsShown += 1;
    }

    @Override
    public void updateDescription()
    {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new InariWhiteFoxSummon(passiveAmount);
    }
}
