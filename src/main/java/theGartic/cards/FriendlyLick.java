package theGartic.cards;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.actions.RemoveRandomDebuffAction;

import static theGartic.GarticMod.makeID;

public class FriendlyLick extends AbstractEasyCard {
    public final static String ID = makeID("FriendlyLick");
    // intellij stuff skill, self, basic, , ,  5, 3, ,
    private static final int MAGIC = 3;
    private static final int DEBUFFS_TO_REMOVE = 1;

    public FriendlyLick() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HealAction(p, p, magicNumber));
        if (upgraded){
            addToBot(new RemoveRandomDebuffAction(DEBUFFS_TO_REMOVE));
        }
    }

    public void upp() {
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}