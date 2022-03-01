package theGartic.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGartic.TheGartic;

import static theGartic.GarticMod.makeID;

public class WornCoat extends AbstractEasyRelic {
    public static final String ID = makeID(WornCoat.class.getSimpleName());

    public WornCoat() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT, TheGartic.Enums.GARTIC_COLOR);
    }

    public void onEnterDiscard(AbstractCard c) {
        flash();
        addToTop(new GainBlockAction(AbstractDungeon.player, 6));
    }
}
