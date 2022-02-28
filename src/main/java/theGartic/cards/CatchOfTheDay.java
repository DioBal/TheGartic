package theGartic.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGartic.cards.AbstractEasyCard;
import theGartic.fish.FishHelper;
import theGartic.powers.LambdaPower;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.*;

public class CatchOfTheDay extends AbstractEasyCard {
    public final static String ID = makeID("CatchOfTheDay");
    // intellij stuff power, self, uncommon, , , , , , 

    public CatchOfTheDay() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Catch of the Day", AbstractPower.PowerType.BUFF, false, p, 1) {
            @Override
            public void atStartOfTurnPostDraw() {
                flash();
                for (int i = 0; i < amount; i++) {
                    atb(new MakeTempCardInDrawPileAction(FishHelper.returnRandomFish(), 1, true, true));
                }
            }

            @Override
            public void updateDescription() {
                description = "At the start of your turn, shuffle #b" + amount + " #yFish into your draw pile.";
            }
        });
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}