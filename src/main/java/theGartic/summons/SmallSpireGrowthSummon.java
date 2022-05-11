package theGartic.summons;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.GarticMod;

import static theGartic.GarticMod.makeOrbPath;

public class SmallSpireGrowthSummon extends AbstractSummonOrb {

    public static final String ORB_ID = GarticMod.makeID(SmallSpireGrowthSummon.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    //public static final Logger logger = LogManager.getLogger("a");
    public int amountOfSummonsToUnsummonIt = 0;
    private static final int limitWhereSmallSpireGrowthIsUnsummoned = 2;
    private static final int amountOfDamageItDoes = 5;

    public SmallSpireGrowthSummon()
    {
        super(ORB_ID, orbString.NAME, 1, 0, makeOrbPath("MischievousFox.png"));
    }

    @Override
    public void onEndOfTurn() {
        //logger.info("So, we are here, and still");

        DamageAction smallSpireDamageAction =
                new DamageAction(AbstractDungeon.player,
                        new DamageInfo(AbstractDungeon.player, amountOfDamageItDoes,
                                DamageInfo.DamageType.THORNS),
                        AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        AbstractDungeon.actionManager.addToTop(smallSpireDamageAction);
    }

    @Override
    public void onSummon(){
        amountOfSummonsToUnsummonIt++;
        if (amountOfSummonsToUnsummonIt >= limitWhereSmallSpireGrowthIsUnsummoned){
            unSummon();
        }
    }

    @Override
    public void updateDescription()
    {
        description = DESCRIPTIONS[0] + amountOfDamageItDoes + DESCRIPTIONS[1];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new SmallSpireGrowthSummon();
    }

}