package theGartic.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.GarticMod;

public class Protector extends AbstractEasyCard {
    public final static String ID = GarticMod.makeID("Protector");

    public Protector() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 6;
    }

    @Override
    public void upp() {
        upgradeBlock(4);
    }

    public void triggerOnManualDiscard() {
        addToTop(new GainEnergyAction(1));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
    }
}
