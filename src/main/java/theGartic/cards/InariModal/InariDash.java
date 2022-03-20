package theGartic.cards.InariModal;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import theGartic.cards.AngelicGlide;
import theGartic.cards.EasyModalChoiceCard;
import theGartic.powers.InariDashPower;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class InariDash extends EasyModalChoiceCard {

    public static final String ID = makeID(AngelicGlide.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public InariDash(String name, String description, int magicNumber) {
        super(name, description, new Runnable() {
            @Override
            public void run() {
                AbstractPlayer p = AbstractDungeon.player;
                atb(new ApplyPowerAction(p, p, new InariDashPower(magicNumber), 1));
            }
        });

        baseMagicNumber = magicNumber;
    }
}
