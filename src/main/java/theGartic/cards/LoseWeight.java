package theGartic.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.GarticMod;
import theGartic.TheGartic;
import theGartic.actions.LoseWeightAction;

public class LoseWeight extends CustomCard {
	public static final String RAW_ID = "LoseWeight";
	public static final String ID = GarticMod.makeID(RAW_ID);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String IMG = GarticMod.makeCardPath(RAW_ID + ".png");
	private static final int COST = 1;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardColor COLOR = TheGartic.Enums.GARTIC_COLOR;
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;

	private static final int POWER = 3;
	private static final int UPGRADE_BONUS = 1;

	public LoseWeight() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		baseMagicNumber = POWER;
		magicNumber = baseMagicNumber;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DrawCardAction(p, magicNumber));
		addToBot(new LoseWeightAction());
	}

	public AbstractCard makeCopy() {
		return new LoseWeight();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BONUS);
		}
	}
}
