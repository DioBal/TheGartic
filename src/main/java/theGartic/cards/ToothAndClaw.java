package theGartic.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.GarticMod;
import theGartic.TheGartic;
import theGartic.actions.DistractingFoxAction;
import theGartic.actions.SummonOrbAction;
import theGartic.actions.DireWolfSummonAction;
import theGartic.summons.AbstractSummonOrb;
import theGartic.summons.DireWolfSummon;

import static theGartic.GarticMod.makeCardPath;
import static theGartic.util.Wiz.adp;

public class ToothAndClaw extends AbstractEasyCard
{

    public static final String ID = GarticMod.makeID(ToothAndClaw.class.getSimpleName());
    public static final String IMG = makeCardPath("ToothAndClaw.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = TheGartic.Enums.GARTIC_COLOR;

    private static final int COST = 2;
    private static final int MAGIC = 2;
    private static final int SECOND_MAGIC = 8;
    private static final int UPGRADE_MAGIC = 1;


    public ToothAndClaw()
    {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR);
        magicNumber = baseMagicNumber = MAGIC;
        secondMagic = baseSecondMagic = SECOND_MAGIC;
        tags.add(GarticMod.Enums.SUMMON);
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster) {
        addToBot(new DireWolfSummonAction(cardStrings.EXTENDED_DESCRIPTION[1], magicNumber, secondMagic));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m))
            return false;
        for (AbstractOrb orb : adp().orbs) {
            if (orb instanceof AbstractSummonOrb)
                if (DireWolfSummonAction.isOrbTarget((AbstractSummonOrb) orb))
                    return true;
        }
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void upp()
    {
        upgradeMagicNumber(UPGRADE_MAGIC);
        initializeDescription();
    }
}