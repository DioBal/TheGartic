package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;

import static com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase.COMBAT;
import static theGartic.GarticMod.makeCardPath;
import static theGartic.GarticMod.makeID;

public class CutLosses extends AbstractEasyCard {

    public final static String ID = makeID("CutLosses");
    public static final String IMG = makeCardPath("CutLosses.png");
    private final static int COST = 1;
    private final static int UPGRADED_COST = 0;
    private static int previousHpLossThisCombat = 0;

    public CutLosses() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL);
        baseMagicNumber = magicNumber = 0;
    }

    @Override
    public void applyPowers() {
        baseMagicNumber = magicNumber = GameActionManager.hpLossThisCombat - previousHpLossThisCombat;
        super.applyPowers();
        if(CardCrawlGame.isInARun() && AbstractDungeon.getCurrRoom().phase == COMBAT && magicNumber > 0) {
            rawDescription = cardStrings.DESCRIPTION + cardStrings.UPGRADE_DESCRIPTION;
        }
        else {
            rawDescription = cardStrings.DESCRIPTION;
        }
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new NotStanceCheckAction("Neutral", new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
        this.addToBot(new ChangeStanceAction("Neutral"));
        this.addToBot(new DamageAllEnemiesAction(p, magicNumber, DamageInfo.DamageType.HP_LOSS, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public void atTurnStart() {
        previousHpLossThisCombat = GameActionManager.hpLossThisCombat;
    }

    @Override
    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
        initializeDescription();
    }
}
