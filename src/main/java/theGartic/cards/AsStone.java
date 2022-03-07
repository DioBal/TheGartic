package theGartic.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.powers.DoubleDamagePower;

import static theGartic.GarticMod.makeID;

public class AsStone extends AbstractEasyCard {
    public final static String ID = makeID("AsStone");

    public AsStone() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DoubleDamagePower(p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public AbstractCard makeCopy() {
        return new AsStone();
    }
}