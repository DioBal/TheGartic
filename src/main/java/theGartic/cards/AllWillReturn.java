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
        this.baseDamage = this.damage = this.baseBlock = this.block = 0;
    }

    @Override
    public void applyPowers() {
        this.baseBlock = lastTurnBlock;
        this.baseDamage = lastTurnDamage;
        super.applyPowers();

        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        this.baseBlock = lastTurnBlock;
        this.baseDamage = lastTurnDamage;
        super.calculateCardDamage(m);

        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.block > 0)
            blck();

        if (this.damage >= 30) {
            if (m != null) {
                this.addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
                this.addToBot(new WaitAction(0.8F));
            }
            dmg(m, AbstractGameAction.AttackEffect.NONE);
        }
        else if (this.damage >= 16) {
            dmg(m, AbstractGameAction.AttackEffect.SMASH);
        }
        else if (this.damage >= 8) {
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