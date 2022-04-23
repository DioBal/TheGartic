package theGartic.cards.papers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.actions.PaperBunnyAction;
import theGartic.cards.AbstractPaperCard;
import static theGartic.GarticMod.makeID;

public class PaperBunny extends AbstractPaperCard {
    public final static String ID = makeID("PaperBunny");
    
    public PaperBunny() {
        super(ID, 2, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 2;
    }
    
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new PaperBunnyAction(this));
    }
    
    public void upp() {
        upMagic(2);
    }
}