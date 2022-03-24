package theGartic.summons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.GarticMod;
import theGartic.actions.DireWolfAction;
import theGartic.cards.DireWolfHelper;
import theGartic.util.OnModifyPowersOrb;

import static theGartic.GarticMod.makeOrbPath;

public class DireWolfSummon extends AbstractSummonOrb implements OnModifyPowersOrb
{
    public static final String ORB_ID = GarticMod.makeID(DireWolfSummon.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static int BASE_PASSIVE_AMOUNT = 3, BASE_STACK = 1;
    public static final DireWolfHelper helperCard = new DireWolfHelper(1);

    private int displayAmount;

    public DireWolfSummon(int amount, int stack)
    {
        super(ORB_ID, orbString.NAME, amount, stack, makeOrbPath("DireWolf.png"));
        OnPowersModified();
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
        description = DESCRIPTIONS[0] + evokeAmount + DESCRIPTIONS[1] + passiveAmount + DESCRIPTIONS[2];
    }

    @Override
    public void OnPowersModified() {
        helperCard.baseDamage = evokeAmount;
        helperCard.applyPowers();
        displayAmount = helperCard.damage;
    }

    @Override
    protected void renderText(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L,
                displayAmount + "x" + passiveAmount,
                cX + NUM_X_OFFSET + 20* Settings.scale, cY + NUM_Y_OFFSET - 20* Settings.yScale,
                new Color(1.0f, 0.5f, 0.5f, 1.0f), fontScale);
    }

    @Override
    public AbstractOrb makeCopy()
    {
        return new DireWolfSummon(passiveAmount, evokeAmount);
    }
}