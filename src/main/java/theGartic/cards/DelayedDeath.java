package theGartic.cards;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.actions.DelayedDeathAction;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.*;

public class DelayedDeath extends AbstractEasyCard {
    public final static String ID = makeID(DelayedDeath.class.getSimpleName());
    private final static int DAMAGE = 1;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int COST = 1;

    public DelayedDeath() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        isMultiDamage = true;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(mon -> {
            calculateCardDamage(mon);
            DamageInfo info = new DamageInfo(adp(), damage, DamageInfo.DamageType.NORMAL);
            atb(new DelayedDeathAction(mon, info));
        });
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}