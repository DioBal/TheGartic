package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.TheGartic;
import theGartic.powers.PlayerFlightPower;
import theGartic.util.Wiz;

import static theGartic.GarticMod.makeCardPath;
import static theGartic.GarticMod.makeID;

public class GuzzlerHelmet extends AbstractEasyCard {
    public static final String ID = makeID(GuzzlerHelmet.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("GuzzlerHelmet.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheGartic.Enums.GARTIC_COLOR;
    private static final int COST = 1;
    private static final int MAGIC = 3;

    public GuzzlerHelmet() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR);
        magicNumber = baseMagicNumber = MAGIC;
    }

    public void triggerOnGlowCheck() {
        if (Wiz.adp().discardPile.size() > Wiz.adp().discardPile.size()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if(Wiz.adp().discardPile.size() > Wiz.adp().discardPile.size()) {
                    addToTop(new GainEnergyAction(magicNumber));
                }
            }
        });
    }

    // Upgraded stats.
    @Override
    public void upp() {
        upgradeBaseCost(0);
        initializeDescription();
    }
}