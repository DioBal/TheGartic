package theGartic.relics;

import theGartic.TheGartic;

import static theGartic.GarticMod.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheGartic.Enums.GARTIC_COLOR);
    }
}
