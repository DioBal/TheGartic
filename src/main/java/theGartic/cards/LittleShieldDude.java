package theGartic.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGartic.powers.LambdaPower;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.applyToSelf;

public class LittleShieldDude extends AbstractEasyCard {
    public final static String ID = makeID("LittleShieldDude");
    private final static int MAGIC = 1; //buffs amount
    private final static int UPGRADE_MAGIC = 1;
    public LittleShieldDude() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Little Shield Dude", AbstractPower.PowerType.BUFF, false, p, magicNumber) {
            @Override
            public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
                if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
                    this.flash();
                    this.addToTop(new GainBlockAction(p,damageAmount));
                    this.amount -= 1;
                    if (amount<=0){
                        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
                    }
                }

            }

            @Override
            public void updateDescription() {
                description = "Your next " + amount + " Attack(s) grant you Block equal to their unblocked Damage. ";
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        uDesc();
    }
}