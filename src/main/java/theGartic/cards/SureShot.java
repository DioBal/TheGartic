package theGartic.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.GarticMod;
import theGartic.TheGartic;

import static theGartic.util.Wiz.atb;

public class SureShot extends AbstractEasyCard {
    public static final String ID = GarticMod.makeID(SureShot.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = GarticMod.makeCardPath("Attack.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheGartic.Enums.GARTIC_COLOR;

    public SureShot() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if (m.currentBlock > 0){
            atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, baseDamage, DamageInfo.DamageType.HP_LOSS),
                    AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
        atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, baseDamage, DamageInfo.DamageType.HP_LOSS),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

    }

    // Upgraded stats.
    @Override
    public void upp() {
        upgradeDamage(3);
        initializeDescription();
    }


}