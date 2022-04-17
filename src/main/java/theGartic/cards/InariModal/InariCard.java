package theGartic.cards.InariModal;

import theGartic.cards.EasyModalChoiceCard;

public abstract class InariCard extends EasyModalChoiceCard {

    public InariCard(String name, String description, Runnable onUseOrChosen) {
        super(name, description, onUseOrChosen);
    }

    public void updateMagicNumber(int amount){

    }
}
