package theGartic.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.GarticMod;
import theGartic.actions.CreationOfCreationAction;
import theGartic.powers.BlockAndLoadPower;
import theGartic.stances.ResoluteStance;

public class BlockAndLoad  extends AbstractEasyCard {
    public final static String ID = GarticMod.makeID("BlockAndLoad");
    
    public BlockAndLoad() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
    }
    
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChangeStanceAction(new ResoluteStance()));
        addToBot(new ApplyPowerAction(p, p, new BlockAndLoadPower(p, 1, upgraded)));
    }
    
    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    }
}