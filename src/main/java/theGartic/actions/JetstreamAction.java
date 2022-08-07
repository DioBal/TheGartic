package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.core.Settings;

import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.att;

public class JetstreamAction extends AbstractGameAction {

    public JetstreamAction() {
        duration = startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        att(new ShuffleAction(adp().drawPile));
        for (AbstractCard card : adp().hand.group) {
            if (card instanceof Safety || card instanceof Smite)
                continue;
            att(new AbstractGameAction() {
                    @Override
                    public void update() {
                        adp().hand.moveToDeck(card, true);
                        isDone = true;
                    }
                });
        }
        isDone = true;
    }
}
