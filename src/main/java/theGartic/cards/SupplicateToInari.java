package theGartic.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.GarticMod;
import theGartic.TheGartic;
import theGartic.actions.SummonOrbAction;
import theGartic.summons.InariWhiteFoxSummon;

import static theGartic.util.Wiz.atb;

public class SupplicateToInari extends AbstractEasyCard  {

    public static final String ID = GarticMod.makeID(SupplicateToInari.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = GarticMod.makeCardPath("SupplicateToInari.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheGartic.Enums.GARTIC_COLOR;

    private final static int MAGIC = 0;
    private final static int MAGIC_UPGRADE_INCREMENT = 1;
    private final static int WHITE_FOX_MAGIC = 1;

    private static final Color FLAVOR_BOX_COLOR = Color.WHITE.cpy();
    private static final Color FLAVOR_TEXT_COLOR = Color.BROWN.cpy();

    public SupplicateToInari()
    {
        super(ID, 3, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(GarticMod.Enums.SUMMON);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(MAGIC_UPGRADE_INCREMENT);
        if (magicNumber == 0)
        {
            this.rawDescription = cardStrings.DESCRIPTION;
        }
        else if (magicNumber == 1)
        {
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        else
        {
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        atb (new SummonOrbAction(new InariWhiteFoxSummon(WHITE_FOX_MAGIC)));
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new GainEnergyAction(magicNumber));
    }
}
