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
import theGartic.summons.FireImpSummon;

import static theGartic.GarticMod.makeCardPath;

public class Hellblaze extends AbstractEasyCard
{

    public static final String ID = GarticMod.makeID(Hellblaze.class.getSimpleName());
    public static final String IMG = makeCardPath("Hellblaze.png");

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    public static final AbstractCard.CardColor COLOR = TheGartic.Enums.GARTIC_COLOR;

    private static final int COST = 1;
    private static final int DAMAGE = 4;


    public Hellblaze()
    {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR);
        damage = baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 3;
        tags.add(GarticMod.Enums.SUMMON);
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        for (int i = 0; i < 2; i++)
            addToBot( new DamageAction(monster, new DamageInfo(player, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new SummonOrbAction(new FireImpSummon(magicNumber, 1), 1));
    }

    @Override
    public void upp()
    {
        upgradeDamage(2);
        upgradedDamage = true;
        initializeDescription();
    }
}