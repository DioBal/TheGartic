package theGartic.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.powers.AdaptationPower;

import static theGartic.GarticMod.makeID;


public class Adaptation extends AbstractEasyCard {
    public final static String ID = makeID("Adaptation");

    public Adaptation() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new AdaptationPower(p, 1), 1));
    }

    public void upp() {
        upgradeBaseCost(2);
        uDesc();
    }
}
