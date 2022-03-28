package theGartic.cards.InariModal;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import theGartic.cards.EasyModalChoiceCard;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class InariFlames extends EasyModalChoiceCard {

    public static final String ID = makeID(InariFlames.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public InariFlames() {
        super(NAME, DESCRIPTION, () -> {
            AbstractPlayer p = AbstractDungeon.player;
            AbstractGameAction.AttackEffect effect = AbstractGameAction.AttackEffect.FIRE;
            DamageInfo.DamageType damageType = DamageInfo.DamageType.THORNS;
            for (int i = 0; i < 10; i++){
                atb(new DamageAction(m, new DamageInfo(p, 2, damageType), effect));
            }

        });

        baseMagicNumber = magicNumber;
        initializeDescription();
    }

    public InariFlames(int magicNumber) {
        super(NAME, DESCRIPTION, () -> {
            AbstractPlayer p = AbstractDungeon.player;
            AbstractGameAction.AttackEffect effect = AbstractGameAction.AttackEffect.FIRE;
            DamageInfo.DamageType damageType = DamageInfo.DamageType.THORNS;
            for (int i = 0; i < 10; i++){
                atb(new DamageAction(m, new DamageInfo(p, 1+magicNumber, damageType), effect));
            }

        });

        baseMagicNumber = 1+magicNumber;
        initializeDescription();
    }
}
