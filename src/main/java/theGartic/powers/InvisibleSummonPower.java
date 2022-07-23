package theGartic.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGartic.GarticMod;
import theGartic.summons.AbstractSummonOrb;

public class InvisibleSummonPower extends AbstractPower implements InvisiblePower
{
    public AbstractCreature source;

    public static final String POWER_ID = GarticMod.makeID(InvisibleSummonPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public InvisibleSummonPower(final AbstractCreature owner, final AbstractCreature source, final int amount)
    {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.valueOf("NEUTRAL");
        isTurnBased = false;

        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action)
    {
        for (AbstractOrb orb : AbstractDungeon.player.orbs)
        {
            if(orb instanceof AbstractSummonOrb)
                ((AbstractSummonOrb)orb).onUseCard(card, action);
        }
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source)
    {
        for (AbstractOrb orb : AbstractDungeon.player.orbs)
        {
            if(orb instanceof AbstractSummonOrb)
                ((AbstractSummonOrb)orb).onApplyPower(power, target, source);
        }
    }
}
