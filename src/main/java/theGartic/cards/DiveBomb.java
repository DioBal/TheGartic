package theGartic.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.powers.PlayerFlightPower;

import static theGartic.util.Wiz.*;
import static theGartic.GarticMod.makeID;

public class DiveBomb extends AbstractEasyCard {
    public final static String ID = makeID(DiveBomb.class.getSimpleName());
    private final static int DAMAGE = 12;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 1;

    public DiveBomb() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        isMultiDamage = true;
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, new Color(1.0f, 0.25f, 0.25f, 1.0f));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!adp().hasPower(PlayerFlightPower.POWER_ID))
            return;
        for (int i = 0; i < adp().getPower(PlayerFlightPower.POWER_ID).amount; i++)
            atb(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        atb(new RemoveSpecificPowerAction(adp(), adp(), PlayerFlightPower.POWER_ID));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}