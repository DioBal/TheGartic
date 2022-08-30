package theGartic.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.SetDontTriggerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theGartic.powers.LambdaPower;
import theGartic.relics.TranquilVoid;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.*;

public class CleanestMind extends AbstractEasyCard {
    public final static String ID = makeID(CleanestMind.class.getSimpleName());
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = -2;

    public CleanestMind() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        SoulboundField.soulbound.set(this, true);
        baseMagicNumber = magicNumber = MAGIC;
        if (adp() != null && adp().hasRelic(TranquilVoid.ID))
            equipTranquil();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractDungeon.player.decreaseMaxHealth(magicNumber);
            }
        });
        applyToSelf(new LambdaPower(cardStrings.EXTENDED_DESCRIPTION[0], AbstractPower.PowerType.BUFF, false, p, 1) {
            @Override
            public void atStartOfTurnPostDraw() {
                flash();
                if (!adp().hasRelic(TranquilVoid.ID))
                    atb(new LoseHPAction(owner, owner, amount, AbstractGameAction.AttackEffect.FIRE));
                atb(new ApplyPowerAction(owner, owner, new DexterityPower(owner, amount), amount));
            }

            @Override
            public void updateDescription() {
                if (!adp().hasRelic(TranquilVoid.ID))
                    description = cardStrings.EXTENDED_DESCRIPTION[1] + amount + cardStrings.EXTENDED_DESCRIPTION[2]
                            + amount + cardStrings.EXTENDED_DESCRIPTION[3];
                else
                    description = cardStrings.EXTENDED_DESCRIPTION[4] + amount + cardStrings.EXTENDED_DESCRIPTION[5];
            }
        });
    }

    public void equipTranquil() {
        upgradeBaseCost(TranquilVoid.BASE_COST);
        upgradeMagicNumber(TranquilVoid.HEALTH_COST_INCREASE);
    }

    @Override
    public void triggerWhenDrawn() {
        atb(new SetDontTriggerAction(this, false));
    }

    public void triggerOnEndOfTurnForPlayingCard() {
        shuffleIn(this.makeStatEquivalentCopy());
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}