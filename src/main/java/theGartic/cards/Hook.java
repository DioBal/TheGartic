package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.cards.fish.FishHelper;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.*;

public class Hook extends AbstractEasyCard {
    public final static String ID = makeID("Hook");
    // intellij stuff attack, enemy, common, 8, 1, , , 1, 

    public Hook() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        for (int i = 0; i < magicNumber; i++) {
            shuffleIn(FishHelper.returnRandomFish());
        }
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}