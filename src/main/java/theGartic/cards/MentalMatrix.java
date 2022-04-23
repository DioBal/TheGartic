package theGartic.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase.COMBAT;
import static theGartic.GarticMod.makeCardPath;
import static theGartic.GarticMod.makeID;

public class MentalMatrix extends AbstractEasyCard {

    public final static String ID = makeID("MentalMatrix");
    public static final String IMG = makeCardPath("MentalMatrix.png");
    private final static int COST = 1;
    private final static int BLOCK = 5;
    private final static int UPGRADE_PLUS_BLK = 2;
    private static int previousCardsAddedToDrawOrDiscard = 0;

    public MentalMatrix() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = 0;
    }

    // TODO: get number of cards added to draw/discard this turn
    @Override
    public void applyPowers() {
        //baseMagicNumber = magicNumber = GameActionManager.CardsAddedToDrawOrDiscard - previousCardsAddedToDrawOrDiscard;
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
        this.addToBot(new GainBlockAction(p, p, block));
        for(int i = 0; i < magicNumber; i++) {
            this.addToBot(new GainBlockAction(p, p, block));
        }
    }

    @Override
    public void atTurnStart() {
    //    previousCardsAddedToDrawOrDiscard = GameActionManager.CardsAddedToDrawOrDiscard;
    }

    @Override
    public void upp() {
        upgradeBlock(UPGRADE_PLUS_BLK);
        initializeDescription();
    }
}
