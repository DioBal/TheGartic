package theGartic.summons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.GarticMod;
import theGartic.actions.FireImpAttackAction;
import theGartic.cards.SummonDamageHelper;
import theGartic.cards.summonOptions.FireImpOption;
import theGartic.util.OnModifyPowersOrb;

import static theGartic.GarticMod.makeOrbPath;

public class FireImpSummon extends AbstractSummonOrb implements OnModifyPowersOrb
{
    public static final String ORB_ID = GarticMod.makeID(FireImpSummon.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    public static int BASE_PASSIVE_AMOUNT = 3, BASE_STACK = 1;
    public static final SummonDamageHelper helperCard = new SummonDamageHelper(1);

    public int attackAmount;

    public FireImpSummon()
    {
        this(BASE_PASSIVE_AMOUNT, BASE_STACK);
    }

    public FireImpSummon(int amount, int stack)
    {
        super(ORB_ID, orbString.NAME, amount, stack, makeOrbPath("FireImp.png"));
        summonOption = new FireImpOption(false, true);
        attackAmount = amount;
        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action)
    {
        if(card.type != AbstractCard.CardType.ATTACK)
            return;

        AbstractDungeon.actionManager.addToBottom(new FireImpAttackAction(this));
        updateDescription();
    }


    @Override
    public void OnPowersModified() {
        helperCard.baseDamage = passiveAmount;
        helperCard.applyPowers();
        attackAmount = helperCard.damage;
        updateDescription();
    }

    @Override
    public void updateDescription()
    {
        description = DESCRIPTIONS[0] + attackAmount + DESCRIPTIONS[1];
    }

    @Override
    protected void renderText(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L,
                String.valueOf(attackAmount),
                cX + NUM_X_OFFSET + 20* Settings.scale, cY + NUM_Y_OFFSET - 20* Settings.yScale,
                new Color(1.0f, 1f, 1f, 1.0f), fontScale);
    }

    @Override
    public AbstractOrb makeCopy()
    {
        return new FireImpSummon(passiveAmount, evokeAmount);
    }
}