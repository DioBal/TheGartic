package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static theGartic.GarticMod.makeID;
import static theGartic.patches.AllWillReturnPatch.lastTurnBlock;
import static theGartic.patches.AllWillReturnPatch.lastTurnDamage;

public class AllWillReturn extends AbstractEasyCard {
    public final static String ID = makeID("AllWillReturn");

    public AllWillReturn() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = baseBlock = block = 0;
    }

    @Override
    public void applyPowers() {
        baseBlock = lastTurnBlock;
        baseDamage = lastTurnDamage;
        super.applyPowers();

        rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        baseBlock = lastTurnBlock;
        baseDamage = lastTurnDamage;
        super.calculateCardDamage(m);

        rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void onMoveToDiscard() {
        rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (block > 0)
            blck();

        if (damage >= 30) {
            if (m != null) {
                addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
                addToBot(new WaitAction(0.8F));
            }
            dmg(m, AbstractGameAction.AttackEffect.NONE);
        }
        else if (damage >= 16) {
            dmg(m, AbstractGameAction.AttackEffect.SMASH);
        }
        else if (damage >= 8) {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        }
        else {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
    }

    public void upp() {
        upgradeBaseCost(2);
    }

    @Override
    public AbstractCard makeCopy() {
        return new AllWillReturn();
    }
}