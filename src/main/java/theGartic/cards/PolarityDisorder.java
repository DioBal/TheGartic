package theGartic.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;

import java.util.ArrayList;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.getRandomSlash;

public class PolarityDisorder extends AbstractEasyCard {
    public final static String ID = makeID(PolarityDisorder.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 1;
    public static final ArrayList<String> stancesThisTurn = new ArrayList<>();

    public PolarityDisorder() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < stancesThisTurn.size() + 1; i++)
            dmg(m, getRandomSlash());
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }

    public static void onChangeStance(AbstractStance stance) {
        if (!stancesThisTurn.contains(stance.ID) && (!stance.ID.equals(NeutralStance.STANCE_ID)))
            stancesThisTurn.add(stance.ID);
    }

    public static void reset() {
        stancesThisTurn.clear();
    }
}