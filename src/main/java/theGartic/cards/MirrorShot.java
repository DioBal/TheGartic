package theGartic.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGartic.GarticMod;
import theGartic.TheGartic;

public class MirrorShot extends CustomCard {
	public static final String RAW_ID = "MirrorShot";
	public static final String ID = GarticMod.makeID(RAW_ID);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String IMG = GarticMod.makeCardPath(RAW_ID + ".png");
	private static final int COST = 1;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardColor COLOR = TheGartic.Enums.GARTIC_COLOR;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	private static final int POWER = 9;
	private static final int UPGRADE_BONUS = 3;
	private static final int MAGIC = 2;
	private static final int UPGRADE_MAGIC = 1;

	public MirrorShot() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		baseDamage = POWER;
		baseMagicNumber = MAGIC;
		magicNumber = baseMagicNumber;
	}

	public void triggerOnGlowCheck() {
		for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
			if (canActivate(m)) {
				glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
				return;
			}
		}
		glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
		addToBot(new AbstractGameAction() {
			@Override
			public void update() {
				if (m != null && canActivate(m)) {
					addToTop(new MakeTempCardInHandAction(new Shiv(), magicNumber));
				}
				isDone = true;
			}
		});
	}

	public AbstractCard makeCopy() {
		return new MirrorShot();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_BONUS);
			upgradeMagicNumber(UPGRADE_MAGIC);
		}
	}

	public boolean canActivate(AbstractCreature m) {
		for (AbstractPower p : m.powers) {
			String n = p.name.toLowerCase();
			for (AbstractPower p2 : AbstractDungeon.player.powers) {
				if (p2.name.toLowerCase().equals(n)) {
					return true;
				}
			}
		}
		return false;
	}
}
