package theGartic.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theGartic.TheGartic;

import static theGartic.GarticMod.makeID;

public class DarkPact extends AbstractEasyRelic {
    public static final String ID = makeID("DarkPact");

    public DarkPact() {
        super(ID, AbstractRelic.RelicTier.SHOP, AbstractRelic.LandingSound.FLAT, TheGartic.Enums.GARTIC_COLOR);
    }

    public void atTurnStartPostDraw() {
        if (AbstractDungeon.player.isBloodied) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
        }
    }
}
