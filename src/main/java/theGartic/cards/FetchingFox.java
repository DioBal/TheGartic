package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.GarticMod;
import theGartic.TheGartic;
import theGartic.actions.SummonOrbAction;
import theGartic.summons.FetchingFoxSummon;
import theGartic.summons.FireImpSummon;

import static theGartic.GarticMod.makeCardPath;

public class FetchingFox extends AbstractEasyCard
{

    public static final String ID = GarticMod.makeID(FetchingFox.class.getSimpleName());
    public static final String IMG = makeCardPath("FetchingFox.png");

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = TheGartic.Enums.GARTIC_COLOR;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;


    public FetchingFox()
    {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR);
        magicNumber = baseMagicNumber = 1;
        tags.add(GarticMod.Enums.SUMMON);
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        addToBot(new SummonOrbAction(new FetchingFoxSummon(magicNumber, 1), 1));
    }

    @Override
    public void upp()
    {
        upgradeBaseCost(UPGRADED_COST);
        initializeDescription();
    }
}