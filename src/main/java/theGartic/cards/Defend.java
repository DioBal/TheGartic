package theGartic.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theGartic.GarticMod.makeID;

public class Defend extends AbstractEasyCard {
    public final static String ID = makeID("Defend");
    private final static int BLOCK = 5;
    private final static int UPGRADE_BLOCK = 3;
    private final static int COST = 1;

    public Defend() {
        super(ID, COST, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = BLOCK;
        tags.add(CardTags.STARTER_DEFEND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}