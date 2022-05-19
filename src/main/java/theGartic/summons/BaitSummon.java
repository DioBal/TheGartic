package theGartic.summons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.GarticMod;
import theGartic.actions.ReplaceBaitAction;
import theGartic.actions.SummonOrbAction;

import static theGartic.GarticMod.makeOrbPath;
import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.atb;

public class BaitSummon extends AbstractSummonOrb{

    public static final String ORB_ID = GarticMod.makeID(BaitSummon.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private int TURNS_BEFORE_SUMMONING_SOMETHING = 1;

    public BaitSummon(){
        super(ORB_ID, orbString.NAME, 1, 1, makeOrbPath("MischievousFox.png"));
    }

    @Override
    protected void renderText(SpriteBatch sb) {

    }

    @Override
    public void onEndOfTurn() {
        if (TURNS_BEFORE_SUMMONING_SOMETHING == 0){
            replaceThisBaitWithAnotherSummon();
        }
        else {
            TURNS_BEFORE_SUMMONING_SOMETHING -= 1;
        }
    }

    private void replaceThisBaitWithAnotherSummon(){
        atb(new ReplaceBaitAction());
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
