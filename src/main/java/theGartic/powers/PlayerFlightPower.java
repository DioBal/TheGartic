package theGartic.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theGartic.GarticMod;
import theGartic.actions.ReduceFlightMaybeAction;

import static theGartic.util.Wiz.*;

public class PlayerFlightPower extends AbstractPower {
    public static final String POWER_ID = GarticMod.makeID(PlayerFlightPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final int DMG_REDUCTION_PERCENT = 50;
    public static final float DMG_REDUCTION_MULTIPLIER = DMG_REDUCTION_PERCENT/100.0f;

    public PlayerFlightPower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        loadRegion("flight");
        type = PowerType.BUFF;
        isTurnBased = false;

        updateDescription();
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage*DMG_REDUCTION_MULTIPLIER : damage;
    }

    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT &&
                !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            atb(new ReduceFlightMaybeAction());
        }

    }
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type == DamageInfo.DamageType.NORMAL && damageAmount > 0 && !adp().hasPower(HoverPower.POWER_ID)) {
            flash();
            att(new ReducePowerAction(owner, owner, this, 1));
        }

        return damageAmount;
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0] + DMG_REDUCTION_PERCENT + DESCRIPTIONS[1];
    }
}
