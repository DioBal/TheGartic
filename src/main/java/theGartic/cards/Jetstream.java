package theGartic.cards;

import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.actions.JetstreamAction;

import java.util.ArrayList;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class Jetstream extends AbstractEasyCard {
    public final static String ID = makeID(Jetstream.class.getSimpleName());
    public final static int MAGIC = 5;
    public final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public Jetstream() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
        cardToPreview = new ArrayList<>();
        cardToPreview.add(new Smite());
        cardToPreview.add(new Safety());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new JetstreamAction());
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}