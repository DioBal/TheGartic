package theGartic.cards.InariModal;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import theGartic.cards.EasyModalChoiceCard;
import theGartic.powers.InariDashPower;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class InariEnergy extends InariCard {

    public static final String ID = makeID(InariEnergy.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public InariEnergy() {
        super(NAME, DESCRIPTION, () -> {
            AbstractPlayer p = AbstractDungeon.player;
            atb(new ApplyPowerAction(p, p, new EnergizedPower(p, 1)));
        });

        baseMagicNumber = magicNumber;
        initializeDescription();
    }

    public InariEnergy(int magicNumber){
        super(NAME, DESCRIPTION, () -> {
            AbstractPlayer p = AbstractDungeon.player;
            atb(new ApplyPowerAction(p,p, new EnergizedPower(p,2*magicNumber)));
        });

        baseMagicNumber = magicNumber * 2;
        initializeDescription();
    }

    @Override
    public void updateMagicNumber(int amount){
        magicNumber += amount;
        baseMagicNumber = magicNumber * 2;
    }
}
