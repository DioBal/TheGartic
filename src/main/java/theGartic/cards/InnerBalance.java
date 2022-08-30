package theGartic.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.CalmStance;
import com.megacrit.cardcrawl.stances.WrathStance;
import theGartic.GarticMod;

import static theGartic.util.Wiz.atb;

public class InnerBalance extends AbstractEasyCard {
    public final static String ID = GarticMod.makeID(InnerBalance.class.getSimpleName());
    public final static int COST = 1;
    public final static int DAMAGE = 7;
    public final static int UPGRADE_DAMAGE = 2;
    public final static int BLOCK = 5;
    public final static int UPGRADE_BLOCK = 2;
    // Update the card strings if you change this
    public final static int MAGIC = 1;

    private static final Color FLAVOR_BOX_COLOR = Color.WHITE.cpy();
    private static final Color FLAVOR_TEXT_COLOR = Color.BLACK.cpy();

    public InnerBalance() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;

        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }
    
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.player.stance.ID.equals(WrathStance.STANCE_ID))
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if (!AbstractDungeon.player.stance.ID.equals(CalmStance.STANCE_ID))
            blck();
        atb(new DrawCardAction(magicNumber));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (!AbstractDungeon.player.stance.ID.equals(WrathStance.STANCE_ID))
            target = CardTarget.ENEMY;
        else
            target = CardTarget.SELF;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeBlock(UPGRADE_BLOCK);
    }
}