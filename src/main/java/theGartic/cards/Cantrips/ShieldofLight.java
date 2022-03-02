package theGartic.cards.Cantrips;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.cards.AbstractEasyCard;

import static theGartic.GarticMod.makeCardPath;
import static theGartic.GarticMod.makeID;
@AutoAdd.Ignore
public class ShieldofLight extends AbstractEasyCard {
    public static final String ID = makeID(ShieldofLight.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("Skill.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = CardColor.COLORLESS;
    private static final int COST = -2;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 2;
    private AbstractMonster Target;

    public ShieldofLight() {
        super(ID,COST,TYPE,RARITY,TARGET,COLOR);
        baseDamage = DAMAGE;
        block = baseBlock = 7;
        isMultiDamage = true;
    }
    public ShieldofLight(AbstractMonster target) {
        super(ID,COST,TYPE,RARITY,TARGET,COLOR);
        Target = target;
        baseDamage = DAMAGE;
        block = baseBlock = 7;
        isMultiDamage = true;
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new MakeTempCardInHandAction(new Safety()));
    }
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        use(p,Target);
    }
    // Upgraded stats.
    @Override
    public void upp() {
        upgradeBlock(3);
        initializeDescription();
    }
}
