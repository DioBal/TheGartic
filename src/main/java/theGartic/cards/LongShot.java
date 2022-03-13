package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.TheGartic;
import theGartic.powers.GambleNextTurnPower;
import theGartic.powers.PlayerFlightPower;
import theGartic.util.Wiz;

import static theGartic.GarticMod.makeCardPath;
import static theGartic.GarticMod.makeID;

public class LongShot extends AbstractEasyCard {
    public static final String ID = makeID(LongShot.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("LongShot.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheGartic.Enums.GARTIC_COLOR;
    private static final int COST = 2;
    private static final int DAMAGE = 11, DMG_UPG = 5;

    public LongShot() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR);
        damage = baseDamage = DAMAGE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        Wiz.applyToSelf(new GambleNextTurnPower());
    }

    // Upgraded stats.
    @Override
    public void upp() {
        upgradeDamage(DMG_UPG);
        initializeDescription();
    }
}