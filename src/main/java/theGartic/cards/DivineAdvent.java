package theGartic.cards;

import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.powers.DivineAdventPower;

import java.util.ArrayList;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.applyToSelf;

public class DivineAdvent extends AbstractEasyCard {
    public final static String ID = makeID(DivineAdvent.class.getSimpleName());
    private final static int COST = 1;

    public DivineAdvent() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        cardToPreview = new ArrayList<>();
        cardToPreview.add(new Smite());
        cardToPreview.add(new Safety());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DivineAdventPower(1));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}