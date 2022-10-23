package theGartic.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.TheGartic;
import theGartic.powers.PlayerFlightPower;
import theGartic.util.Wiz;

import static theGartic.GarticMod.makeCardPath;
import static theGartic.GarticMod.makeID;

public class FeatherGuard extends AbstractEasyCard {
    public static final String ID = makeID(FeatherGuard.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("Skill.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheGartic.Enums.GARTIC_COLOR;
    private static final int COST = 2;

    public FeatherGuard() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR);
        block = baseBlock = 10;
        magicNumber = baseMagicNumber = 2;
        isMultiDamage = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new PlayerFlightPower(p, magicNumber));
    }

    // Upgraded stats.
    @Override
    public void upp() {
        upgradeMagicNumber(1);
        initializeDescription();
    }
}