package theGartic.cards.InariModal;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theGartic.cards.EasyModalChoiceCard;

import java.util.Iterator;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class InariSabotage extends InariCard {

    public static final String ID = makeID(InariSabotage.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public InariSabotage() {
        super(NAME, DESCRIPTION, () -> {
            AbstractPlayer p = AbstractDungeon.player;
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                Iterator iterator = AbstractDungeon.getMonsters().monsters.iterator();
                while(iterator.hasNext()) {
                    AbstractMonster monster = (AbstractMonster)iterator.next();
                    if (!monster.isDead && !monster.isDying) {
                        atb(new ApplyPowerAction(monster, p, new VulnerablePower(monster, 1, false), 1));
                    }
                }

            }
        });

        baseMagicNumber = 1;
        initializeDescription();
    }

    public InariSabotage(int magicNumber) {
        super(NAME, DESCRIPTION, () -> {
            AbstractPlayer p = AbstractDungeon.player;
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                Iterator iterator = AbstractDungeon.getMonsters().monsters.iterator();
                while(iterator.hasNext()) {
                    AbstractMonster monster = (AbstractMonster)iterator.next();
                    if (!monster.isDead && !monster.isDying) {
                        atb(new ApplyPowerAction(monster, p, new VulnerablePower(monster, magicNumber + 1, false), magicNumber + 1));
                    }
                }

            }
        });

        baseMagicNumber = magicNumber + 1;
        initializeDescription();
    }

    @Override
    public void updateMagicNumber(int amount){
        magicNumber += amount;
        baseMagicNumber = magicNumber + 1;
    }



}
