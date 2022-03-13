package theGartic.summons;

import basemod.devcommands.draw.Draw;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import theGartic.GarticMod;
import theGartic.actions.StealCardAction;

import static theGartic.GarticMod.makeOrbPath;

public class FetchingFoxSummon extends AbstractSummonOrb
{
    public static final String ORB_ID = GarticMod.makeID(FetchingFoxSummon.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static int BASE_PASSIVE_AMOUNT = 3, BASE_STACK = 1;

    public FetchingFoxSummon(int amount, int stack)
    {
        super(ORB_ID, orbString.NAME, amount, stack, makeOrbPath("FetchingFox.png"));

    }

    @Override
    public void atStartOfTurnPostDraw(){
        int random = (int)AbstractDungeon.cardRng.random(0, 1);
        System.out.println(random);
        if (random == 1){
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(evokeAmount));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new StealCardAction(evokeAmount));
            updateDescription();
        }

    }

    @Override
    public void updateDescription()
    {
        if (this.evokeAmount > 1) {
            description = DESCRIPTIONS[1] + evokeAmount + DESCRIPTIONS[2] + evokeAmount + DESCRIPTIONS[3];
        }
        else {
            description = DESCRIPTIONS[0];
        }
    }

    @Override
    public AbstractOrb makeCopy()
    {
        return new FetchingFoxSummon(passiveAmount, evokeAmount);
    }
}