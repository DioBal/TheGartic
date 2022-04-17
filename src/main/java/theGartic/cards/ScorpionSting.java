package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theGartic.TheGartic;
import theGartic.util.Wiz;

import static theGartic.GarticMod.makeCardPath;
import static theGartic.GarticMod.makeID;

public class ScorpionSting extends AbstractEasyCard {
    public static final String ID = makeID(ScorpionSting.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("Skill.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheGartic.Enums.GARTIC_COLOR;

    private static final int COST = 2;
    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 2;

    public ScorpionSting () {
        super(ID,COST,TYPE,RARITY,TARGET,COLOR);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 6;
        secondMagic = baseSecondMagic = 2;
        isMultiDamage = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        Wiz.applyToEnemy(m,new PoisonPower(m,p,magicNumber));
        Wiz.applyToEnemy(m,new WeakPower(m,secondMagic,false));
    }

    // Upgraded stats.
    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
        initializeDescription();
    }
}
