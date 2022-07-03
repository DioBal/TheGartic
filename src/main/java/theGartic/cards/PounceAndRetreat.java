package theGartic.cards;

import com.evacipated.cardcrawl.mod.stslib.blockmods.AbstractBlockModifier;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theGartic.TheGartic;
import theGartic.cardmods.block.DamageBlockMod;

import java.util.ArrayList;
import java.util.List;

import static theGartic.GarticMod.makeCardPath;
import static theGartic.GarticMod.makeID;

public class PounceAndRetreat extends AbstractEasyCard {

    public final static String ID = makeID("PounceAndRetreat");
    public static final String IMG = makeCardPath("MischievousFox.png");
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    public static final AbstractCard.CardColor COLOR = TheGartic.Enums.GARTIC_COLOR;
    private final static int DAMAGE = 9;
        private final static int UPGRADE_DAMAGE = 9;
        private final static int BLOCK = 9;
        private final static int UPGRADE_BLOCK = 9;
        private final static int COST = 2;


    private ArrayList<AbstractBlockModifier> dBlock = new ArrayList<>();

    public PounceAndRetreat() {
        super(ID, COST, TYPE, RARITY, TARGET, COLOR);
        dBlock.add(new DamageBlockMod());
        baseBlock = block = BLOCK;
        baseDamage = damage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        AbstractDungeon.effectList.add(new FlashAtkImgEffect(p.hb.cX, p.hb.cY, AbstractGameAction.AttackEffect.SHIELD));
        BlockModifierManager.addCustomBlock(this, dBlock, p, BLOCK);
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upgradeDamage(UPGRADE_DAMAGE);
    }

}

