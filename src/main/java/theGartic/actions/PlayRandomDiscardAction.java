package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.att;

public class PlayRandomDiscardAction extends AbstractGameAction {

    public PlayRandomDiscardAction(AbstractMonster target) {
        duration = Settings.ACTION_DUR_FAST;
        actionType = ActionType.WAIT;
        this.target = target;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            if (adp().discardPile.size() == 0) {
                isDone = true;
                return;
            }
            else {
                AbstractCard card = adp().discardPile.getRandomCard(AbstractDungeon.cardRandomRng);
                adp().discardPile.group.remove(card);
                AbstractDungeon.getCurrRoom().souls.remove(card);
                card.exhaustOnUseOnce = false;
                adp().limbo.group.add(card);
                card.current_y = -200.0F * Settings.scale;
                card.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
                card.target_y = (float)Settings.HEIGHT / 2.0F;
                card.targetAngle = 0.0F;
                card.lighten(false);
                card.drawScale = 0.12F;
                card.targetDrawScale = 0.75F;
                card.applyPowers();
                att(new NewQueueCardAction(card, target, false, true));
                att(new UnlimboAction(card));
                if (!Settings.FAST_MODE)
                    att(new WaitAction(Settings.ACTION_DUR_MED));
                else
                    att(new WaitAction(Settings.ACTION_DUR_FASTER));
            }

            isDone = true;
        }
    }
}
