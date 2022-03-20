package theGartic.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.SetDontTriggerAction;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theGartic.powers.LambdaPower;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.*;

public class CleanestMind extends AbstractEasyCard {
    public final static String ID = makeID("CleanestMind");
    // intellij stuff power, self, basic, , , , , , 

    public CleanestMind() {
        super(ID, 1, CardType.POWER, CardRarity.BASIC, CardTarget.SELF);
        SoulboundField.soulbound.set(this, true);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (dontTriggerOnUseCard) {
            shuffleIn(this.makeStatEquivalentCopy());
        } else {
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
                    atb(new LoseHPAction(owner, owner, amount, AbstractGameAction.AttackEffect.FIRE));
                    atb(new ApplyPowerAction(owner, owner, new DexterityPower(owner, amount), amount));
                }

                @Override
                public void updateDescription() {
                    description = cardStrings.EXTENDED_DESCRIPTION[1] + amount + cardStrings.EXTENDED_DESCRIPTION[2] + amount + cardStrings.EXTENDED_DESCRIPTION[3];
                }
            });
        }
    }

    @Override
    public void triggerWhenDrawn() {
        atb(new SetDontTriggerAction(this, false));
    }

    public void triggerOnEndOfTurnForPlayingCard() {
        this.dontTriggerOnUseCard = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }

    public void upp() {
        upgradeMagicNumber(-2);
    }
}