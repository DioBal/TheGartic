package theGartic.cards.papers;

import theGartic.cards.AbstractPaperCard;
import static theGartic.GarticMod.makeID;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.actions.common.DamageAction;

public class PaperSword extends AbstractPaperCard {
    public final static String ID = makeID("PaperSword");
    
    public PaperSword() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        baseDamage = damage = 10;
        baseMagicNumber = magicNumber = 3;
    }
    
    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        addToBot(new DamageAction(p, new DamageInfo(p, magicNumber, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }
    
    public void upp() {
        upgradeDamage(4);
    }
}