package theGartic.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theGartic.TheGartic;
import theGartic.powers.GarbageGolemPower;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.applyToSelf;

public class GarbageGolem extends AbstractEasyCard {
    public static final String ID = makeID(GarbageGolem.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheGartic.Enums.GARTIC_COLOR;

    private static final int COST = 2;
    private static final int BLOCK = 18;
    private static final int MAGIC = 3;

    private static final Color FLAVOR_BOX_COLOR = new Color(230f/256f, 126f/256f, 0f, 1f).cpy();
    private static final Color FLAVOR_TEXT_COLOR = Color.WHITE.cpy();

    public GarbageGolem() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR);
        baseBlock = BLOCK;

        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (upgraded)
            applyToSelf(new StrengthPower(adp(), magicNumber));
        applyToSelf(new GarbageGolemPower(adp()));
    }

    @Override
    public void upp() {
        baseMagicNumber = magicNumber = MAGIC;
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
