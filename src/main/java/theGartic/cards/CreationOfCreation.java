package theGartic.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.WhiteNoise;
import com.megacrit.cardcrawl.cards.colorless.JackOfAllTrades;
import com.megacrit.cardcrawl.cards.green.Distraction;
import com.megacrit.cardcrawl.cards.red.InfernalBlade;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.GarticMod;
import theGartic.actions.CreationOfCreationAction;

public class CreationOfCreation extends AbstractEasyCard {
    public final static String ID = GarticMod.makeID("CreationOfCreation");
    private final static int COST = 0;
    private final static int MAGIC = 1;
    
    public CreationOfCreation() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        magicNumber = baseMagicNumber = MAGIC;
        exhaust = true;
        cardToPreview.add(new InfernalBlade());
        cardToPreview.add(new Distraction());
        cardToPreview.add(new WhiteNoise());
        cardToPreview.add(new JackOfAllTrades());
    }
    
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new CreationOfCreationAction(upgraded));
    }
    
    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        cardToPreview.clear();
        cardToPreview.add(new InfernalBlade());
        cardToPreview.add(new Distraction());
        cardToPreview.add(new WhiteNoise());
        cardToPreview.add(new JackOfAllTrades());
        for (AbstractCard c : cardToPreview)
            c.upgrade();
        initializeDescription();
    }
}