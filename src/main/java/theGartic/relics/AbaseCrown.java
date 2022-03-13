package theGartic.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;

import static theGartic.GarticMod.makeID;

public class AbaseCrown extends AbstractEasyRelic {
    public static final String ID = makeID(AbaseCrown.class.getSimpleName());

    private boolean active = false;

    public AbaseCrown() {
        super(ID, RelicTier.RARE, LandingSound.CLINK);
    }

    @Override
    public void atPreBattle() {
        active = false;
        stopPulse();
    }

    @Override
    public void onVictory() {
        active = false;
        stopPulse();
    }

    @Override
    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
        if (prevStance.ID != null && !prevStance.ID.equals(newStance.ID)) {
            active = true;
            this.beginLongPulse();
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (active && (card.costForTurn == 0 || card.freeToPlay())) {
            this.active = false;
            this.stopPulse();

            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));

            AbstractMonster m = null;
            if (action.target instanceof AbstractMonster) {
                m = (AbstractMonster)action.target;
            }

            AbstractCard tmp = card.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = (float)Settings.HEIGHT / 2.0F;
            if (m != null) {
                tmp.calculateCardDamage(m);
            }
            else {
                tmp.applyPowers();
            }

            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
        }
    }
}