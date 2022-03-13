package theGartic.cards;

import com.badlogic.gdx.graphics.Color;
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

    private static final Color FLAVOR_BOX_COLOR = Color.BROWN.cpy();
    private static final Color FLAVOR_TEXT_COLOR = Color.WHITE.cpy();

    public SupplicateToInari() {
        super(ID, 2, TYPE, RARITY, TARGET);
    }

    @Override
    public void upp() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
}
