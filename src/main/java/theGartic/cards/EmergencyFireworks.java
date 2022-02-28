package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Iterator;

import static theGartic.GarticMod.makeID;

public class EmergencyFireworks extends AbstractEasyCard {
    //Deal 11(14) Damage. Apply 2(3) Vulnerable to ALL enemies. When discarded, apply 1 Weak to ALL enemies.
    public final static String ID = makeID("EmergencyFireworks");
    private final static int DAMAGE = 11;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 2; //vulnerable
    private final static int SECOND_MAGIC = 1; //weak
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 2;

    // , UNCOMMON, ENEMY
    public EmergencyFireworks() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //damage
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        //vulnerable
        Iterator monsters = AbstractDungeon.getMonsters().monsters.iterator(); //yeah, I don't remember what this looks like with a for

        while(monsters.hasNext()) {
            AbstractMonster monster = (AbstractMonster)monsters.next();
            if (!monster.isDead && !monster.isDying) {
                this.addToBot(new ApplyPowerAction(monster, p, new VulnerablePower(monster, magicNumber, false), magicNumber));
            }
        }

    }
    public void triggerOnManualDiscard() {

        Iterator monsters = AbstractDungeon.getMonsters().monsters.iterator();

        while(monsters.hasNext()) {
            AbstractMonster monster = (AbstractMonster)monsters.next();
            if (!monster.isDead && !monster.isDying) {
                this.addToBot(new ApplyPowerAction(monster, AbstractDungeon.player, new WeakPower(monster, secondMagic, false), secondMagic));
            }
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upMagic(UPGRADE_MAGIC);
    }
}