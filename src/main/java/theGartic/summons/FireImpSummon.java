package theGartic.summons;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theGartic.GarticMod;
import theGartic.actions.FireImpAttackAction;
import theGartic.cards.summonOptions.FireImpOption;

import static theGartic.GarticMod.makeOrbPath;

public class FireImpSummon extends AbstractSummonOrb
{
    public static final String ORB_ID = GarticMod.makeID(FireImpSummon.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    public static int BASE_PASSIVE_AMOUNT = 3, BASE_STACK = 1;

    public FireImpSummon()
    {
        this(BASE_PASSIVE_AMOUNT, BASE_STACK);
    }

    public FireImpSummon(int amount, int stack)
    {
        super(ORB_ID, orbString.NAME, amount, stack, makeOrbPath("FireImp.png"));
        summonOption = new FireImpOption(false, true);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action)
    {
        if(card.type != AbstractCard.CardType.ATTACK)
            return;

        AbstractDungeon.actionManager.addToBottom(new FireImpAttackAction(this));
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source)
    {
        passiveAmount = basePassiveAmount + (AbstractDungeon.player.hasPower(StrengthPower.POWER_ID) ?
                AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount:0);
        updateDescription();
    }

    @Override //if you want to ignore Focus
    public void applyFocus()
    {
        passiveAmount = basePassiveAmount + (AbstractDungeon.player.hasPower(StrengthPower.POWER_ID) ?
                AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount:0);
        evokeAmount = baseEvokeAmount;
    }

    @Override
    public void updateDescription()
    {
        applyFocus();
        description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractOrb makeCopy()
    {
        return new FireImpSummon(passiveAmount, evokeAmount);
    }
}