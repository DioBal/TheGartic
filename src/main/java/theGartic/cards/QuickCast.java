package theGartic.cards;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.TheGartic;
import theGartic.cards.Cantrips.BoomingBlade;
import theGartic.cards.Cantrips.ShieldofLight;

import java.util.ArrayList;

import static theGartic.GarticMod.makeCardPath;
import static theGartic.GarticMod.makeID;

public class QuickCast extends AbstractEasyCard {
    public static final String ID = makeID(QuickCast.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("Skill.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheGartic.Enums.GARTIC_COLOR;
    private static final int COST = 1;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 2;
    private ArrayList<AbstractCard> Choices = new ArrayList<>();

    public QuickCast () {
        super(ID,COST,TYPE,RARITY,TARGET,COLOR);
        baseDamage = DAMAGE;
        block = baseBlock = 7;
        isMultiDamage = true;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Choices.add(new BoomingBlade(m));
        Choices.add(new ShieldofLight(m));
        for (AbstractCard c : Choices){
            if (upgraded){
                c.upgrade();
            }
            c.applyPowers();
            c.calculateCardDamage(m);
        }
        addToBot(new ChooseOneAction(Choices));
    }

    // Upgraded stats.
    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeBlock(3);
        initializeDescription();
    }
}