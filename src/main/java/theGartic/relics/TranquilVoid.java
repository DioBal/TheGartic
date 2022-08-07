package theGartic.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import theGartic.TheGartic;
import theGartic.cards.CleanestMind;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.adp;

public class TranquilVoid extends AbstractEasyRelic {
    public static final String ID = makeID(TranquilVoid.class.getSimpleName());
    public static final int BASE_COST = 0;
    public static final int HEALTH_COST_INCREASE = 1;

    public TranquilVoid() {
        super(ID, RelicTier.SHOP, LandingSound.MAGICAL, TheGartic.Enums.GARTIC_COLOR);
    }

    @Override
    public void onEquip() {
        for (AbstractCard card : adp().masterDeck.group)
            if (card instanceof CleanestMind)
                ((CleanestMind)card).equipTranquil();
    }
}
