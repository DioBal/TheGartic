package theGartic.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.GarticMod;
import theGartic.TheGartic;

public class Illusions extends CustomCard {
	public static final String RAW_ID = "Illusions";
	public static final String ID = GarticMod.makeID(RAW_ID);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String IMG = GarticMod.makeCardPath(RAW_ID + ".png");
	private static final int COST = 2;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardColor COLOR = TheGartic.Enums.GARTIC_COLOR;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	private static final int DAMAGE = 8;
	private static final int UPGRADE_DAMAGE = 2;
	private static final int BLOCK = 8;
	private static final int UPGRADE_BLOCK = 2;
	private static final int MAGIC = 2;
	private static final int UPGRADE_MAGIC = 1;

	public Illusions() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		baseDamage = DAMAGE;
		baseBlock = BLOCK;
		baseMagicNumber = MAGIC;
		magicNumber = baseMagicNumber;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new GainBlockAction(p, p, block));
		addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
		addToBot(new BetterDiscardPileToHandAction(magicNumber));
	}

	public AbstractCard makeCopy() {
		return new Illusions();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_DAMAGE);
			upgradeBlock(UPGRADE_BLOCK);
			upgradeMagicNumber(UPGRADE_MAGIC);
		}
	}
}
