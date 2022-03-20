package theGartic.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import theGartic.TheGartic;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class HiddenCompartment extends AbstractEasyRelic {
    public static final String ID = makeID("HiddenCompartment");

    public HiddenCompartment() {
        super(ID, RelicTier.RARE, LandingSound.HEAVY, TheGartic.Enums.GARTIC_COLOR);
    }

    @Override
    public void atBattleStart() {
        atb(new MakeTempCardInHandAction(new Smite()));
        atb(new MakeTempCardInHandAction(new Safety()));
    }
}
