package theGartic.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.cards.AbstractPaperCard;
import static theGartic.GarticMod.makeID;

public class SheetofPaper extends AbstractPaperCard {
    public final static String ID = makeID("SheetofPaper");
    
    public SheetofPaper() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        cardToPreview = paperCardsList;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {}

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    public void upp() {
        upgradeCardToPreview();
        uDesc();
    }

}