package theGartic.summons;

import static theGartic.GarticMod.makeID;
import static theGartic.GarticMod.makeOrbPath;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import com.megacrit.cardcrawl.core.Settings;
import theGartic.cards.summonOptions.FireImpOption;
import theGartic.cards.summonOptions.MirroredFoxOption;

public class MirroredFoxSummon extends AbstractSummonOrb {
    
    public static final String ORB_ID = makeID("MirroredFoxSummon");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    public static int BASE_PASSIVE_AMOUNT = 6, BASE_STACK = 1;

    private int tempStr, baseStr, baseDex, tempFr, tempWeak, countFr, countWeak, rand;
    private boolean Shackle = false;
    
    public MirroredFoxSummon() {
        this(BASE_PASSIVE_AMOUNT, BASE_STACK);
    }

    public MirroredFoxSummon(int amount, int stack) {
        super(ORB_ID, orbString.NAME, amount, stack, makeOrbPath("MirroredFox.png"));
        summonOption = new MirroredFoxOption(false, true);
    }

    @Override
    public void onStartOfTurn() {
        rand = AbstractDungeon.cardRng.random(0, 2);
        updateDescription();
        updateAmt();
    }

    @Override
    public void onEndOfTurn() {
        if (rand == 1) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, passiveAmount));
        } else {
            AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy())));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(AbstractDungeon.player, passiveAmount, DamageInfo.DamageType.THORNS ), AbstractGameAction.AttackEffect.NONE));
        }
        if (Shackle) {
            baseStr -= tempStr;
            tempStr = 0;
        }
        if (countWeak == 0) {
            tempWeak = 0;
        } else {
            tempWeak = (int) Math.floor(baseStr * 0.5F);
            countWeak -= 1;
        }
        if (countFr == 0) {
            tempFr = 0;
        } else {
            tempFr = (int) Math.floor(baseDex * 0.5F);
            countFr -= 1;
        }
    }
    
    public boolean onReceivePower(AbstractPower p, AbstractCreature target, AbstractCreature source) {
        if (p.amount < 0) {
            switch (p.ID) {
                case StrengthPower.POWER_ID:
                    baseStr += -p.amount;
                    break;
                case DexterityPower.POWER_ID:
                    baseDex += -p.amount;
                    break;
                default:
                    break;
            }
        } else {
            switch (p.ID) {
                case FrailPower.POWER_ID:
                    tempFr = (int) Math.floor(baseDex * 0.5F);
                    countFr += p.amount;
                    break;
                case WeakPower.POWER_ID:
                    tempWeak = (int) Math.floor(baseStr * 0.5F);
                    countWeak += p.amount;
                    break;
                case "Shackled":
                    Shackle = true;
                    tempStr += p.amount;
                    break;
                default:
                    break;
            }
        }
        updateAmt();
        return true;
    }
    
    @Override
    public void updateDescription()
    {
        if (rand == 1) {
            description = DESCRIPTIONS[0] + evokeAmount + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[2] + passiveAmount + DESCRIPTIONS[3];
        }
    }
    
    public void updateAmt() {
        passiveAmount = BASE_PASSIVE_AMOUNT;
        if (rand == 1) {
            passiveAmount += baseDex + tempFr;
        } else {
            passiveAmount += baseStr + tempWeak;
        }
    }
    
    @Override
    public AbstractOrb makeCopy()
    {
        return new MirroredFoxSummon(passiveAmount, evokeAmount);
    }
    
}