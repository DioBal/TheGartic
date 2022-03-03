package theGartic.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.powers.ArcaneToolboxPower;
import static theGartic.GarticMod.makeID;

public class ArcaneToolbox extends AbstractEasyCard {
    public final static String ID = makeID("ArcaneToolbox");

    public ArcaneToolbox() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ArcaneToolboxPower(p, 1), 1));
    }

    public void upp() {
        this.isInnate = true;
        uDesc();
    }
}