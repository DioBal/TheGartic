package theGartic.summons;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DarkOrbEvokeAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.GarticMod;
import theGartic.actions.DireWolfAction;

import static theGartic.GarticMod.makeOrbPath;

public class DireWolfSummon extends AbstractSummonOrb
{
    public static final String ORB_ID = GarticMod.makeID(DireWolfSummon.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static int BASE_PASSIVE_AMOUNT = 3, BASE_STACK = 1;

    public DireWolfSummon(int amount, int stack)
    {
        super(ORB_ID, orbString.NAME, amount, stack, makeOrbPath("DireWolf.png"));

    }

    @Override
    public void onEndOfTurn() {
        for (int i = 0; i < passiveAmount; i++) {
            AbstractDungeon.actionManager.addToTop(new DireWolfAction(passiveAmount, evokeAmount));
        }
    }

    @Override
    public void updateDescription()
    {
        description = DESCRIPTIONS[0]+evokeAmount+ DESCRIPTIONS[1]+evokeAmount+DESCRIPTIONS[2];
    }

    @Override
    public AbstractOrb makeCopy()
    {
        return new DireWolfSummon(passiveAmount, evokeAmount);
    }
}