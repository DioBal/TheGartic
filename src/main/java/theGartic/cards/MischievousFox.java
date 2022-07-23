package theGartic.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.GarticMod;
import theGartic.TheGartic;
import theGartic.actions.SummonOrbAction;
import theGartic.summons.FetchingFoxSummon;
import theGartic.summons.MischievousFoxSummon;

import static theGartic.GarticMod.makeCardPath;

public class MischievousFox extends AbstractEasyCard
{

    public static final String ID = GarticMod.makeID(MischievousFox.class.getSimpleName());
    public static final String IMG = makeCardPath("MischievousFox.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = TheGartic.Enums.GARTIC_COLOR;

    private static final int COST = 1;
    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 1;


    public MischievousFox()
    {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR);
        magicNumber = baseMagicNumber = MAGIC;
        tags.add(GarticMod.Enums.SUMMON);
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        addToBot(new SummonOrbAction(new MischievousFoxSummon(magicNumber, magicNumber), magicNumber));
    }

    @Override
    public void upp()
    {
        upgradeMagicNumber(UPGRADE_MAGIC);
        initializeDescription();
    }
}