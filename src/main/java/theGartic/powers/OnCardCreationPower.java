package theGartic.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;

public interface OnCardCreationPower {
    boolean onCardCreation(AbstractCard card);
}
