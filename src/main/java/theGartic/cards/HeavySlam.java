package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theGartic.GarticMod.makeID;

public class HeavySlam extends AbstractEasyCard {
    public final static String ID = makeID("HeavySlam");

    public HeavySlam() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 0;
    }

    @Override
    public void upp() {
    }

    @Override
    public void applyPowers() {
        baseDamage = AbstractDungeon.player.discardPile.size();
        if (upgraded) baseDamage += 3;
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AttackEffect effect = damage > 12 ? AttackEffect.BLUNT_HEAVY : AttackEffect.BLUNT_LIGHT;
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), effect));
    }
}
