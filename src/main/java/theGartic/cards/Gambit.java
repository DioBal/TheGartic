package theGartic.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.GarticMod;
import theGartic.util.Wiz;

public class Gambit extends AbstractEasyCard {
    public final static String ID = GarticMod.makeID("Gambit");

    public Gambit() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseBlock = block = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {}

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void triggerWhenDrawn() {
        blck();
        addToBot(new DiscardAction(Wiz.adp(), Wiz.adp(), 1, true));
    }

    public void upp() {
        upgradeBlock(3);
    }
}