package theGartic.summons;

import basemod.devcommands.draw.Draw;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
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
import sun.security.krb5.internal.crypto.Des;
import theGartic.GarticMod;
import theGartic.actions.StealCardAction;
import theGartic.cards.MischievousFox;

import static theGartic.GarticMod.makeOrbPath;

public class MischievousFoxSummon extends AbstractSummonOrb
{
    public static final String ORB_ID = GarticMod.makeID(MischievousFoxSummon.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static int BASE_PASSIVE_AMOUNT = 3, BASE_STACK = 1;

    public MischievousFoxSummon(int amount, int stack)
    {
        super(ORB_ID, orbString.NAME, amount, stack, makeOrbPath("MischievousFox.png"));

    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action){
        int rand = AbstractDungeon.cardRng.random(0, 2);
        System.out.println(rand);
        if (rand == 1){
            AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.evokeAmount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, this.evokeAmount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
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
        return new MischievousFoxSummon(passiveAmount, evokeAmount);
    }
}