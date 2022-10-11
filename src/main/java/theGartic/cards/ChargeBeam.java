package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGartic.GarticMod;
import theGartic.util.Wiz;

public class ChargeBeam extends AbstractEasyCard {
    public final static String ID = GarticMod.makeID("ChargeBeam");
    private boolean discarded = false;

    public ChargeBeam() {
        super(ID, -2, CardType.ATTACK, CardRarity.RARE, CardTarget.NONE);
        damage = baseDamage = 7;
        magicNumber = baseMagicNumber = 3;
        selfRetain = true;
        isMultiDamage = true;
    }

    public void onRetained() {
        superFlash();
        upgradeDamage(this.magicNumber);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(discarded || (purgeOnUse && isInAutoplay)) {
            discarded = false;
            addToBot(new VFXAction(new MindblastEffect(Wiz.adp().dialogX, Wiz.adp().dialogY, Wiz.adp().flipHorizontal)));
            addToBot(new DamageAllEnemiesAction(Wiz.adp(), multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return discarded || (purgeOnUse && isInAutoplay);
    }

    @Override
    public void triggerOnManualDiscard() {
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, false));
        discarded=true;
        Wiz.adp().discardPile.removeCard(this);
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}