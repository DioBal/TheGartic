package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class SwapDrawAndDiscardPilesAction extends AbstractGameAction {

    private boolean drawVFXDone;
    private boolean started;

    private ArrayList<UUID> discardIds;

    public SwapDrawAndDiscardPilesAction() { }

    @Override
    public void update() {
        if (!started) {
            started = true;
            discardIds = new ArrayList<>();
            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                discardIds.add(c.uuid);
            }
        }
        if(!drawVFXDone) {
            Iterator<AbstractCard> draw = AbstractDungeon.player.drawPile.group.iterator();
            if(draw.hasNext()) {
                AbstractCard c = draw.next();
                draw.remove();
                AbstractDungeon.getCurrRoom().souls.discard(c);
                return;
            }
            drawVFXDone = true;
        } else {
            Iterator<AbstractCard> discard = AbstractDungeon.player.discardPile.group.iterator();
            while (!discardIds.isEmpty()) {
                AbstractCard c = discard.next();
                if (discardIds.contains(c.uuid)) {
                    discard.remove();
                    discardIds.remove(c.uuid);
                    AbstractDungeon.getCurrRoom().souls.shuffle(c, false);
                    return;
                }
            }
            isDone = true;
        }
    }
}
