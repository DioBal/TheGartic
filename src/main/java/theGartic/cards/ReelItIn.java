package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.GarticMod;
import theGartic.cards.fish.AbstractFishCard;
import theGartic.util.Wiz;

public class ReelItIn extends AbstractEasyCard {
    public final static String ID = GarticMod.makeID(ReelItIn.class.getSimpleName());

    private static final int DAMAGE = 6;
    private static final int UP_DMG = 3;

    public ReelItIn() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 1;
    }
    
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        for (int i = 0; i < magicNumber; i++) {
            Wiz.makeInHand(AbstractFishCard.returnRandomFish());
        }
    }
    
    public void upp() {
        upgradeDamage(UP_DMG);
    }
}