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

public class SupplicateToInari extends AbstractEasyCard  {

    public static final String ID = GarticMod.makeID(SupplicateToInari.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = GarticMod.makeCardPath("Skill.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheGartic.Enums.GARTIC_COLOR;

    private final static int MAGIC = 0;
    private final static int MAGIC_UPGRADE_INCREMENT = 1;

    private static final Color FLAVOR_BOX_COLOR = Color.WHITE.cpy();
    private static final Color FLAVOR_TEXT_COLOR = Color.BROWN.cpy();

    public SupplicateToInari()
    {
        super(ID, 3, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(MAGIC_UPGRADE_INCREMENT);
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new GainEnergyAction(magicNumber));
    }
}
