package theGartic.cards;

import static theGartic.GarticMod.makeID;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import theGartic.cardmods.block.VoidBlockMod;
import theGartic.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModContainer;
import com.evacipated.cardcrawl.mod.stslib.actions.common.GainCustomBlockAction;

public class VoidDefend extends AbstractEasyCard {
    public final static String ID = makeID("VoidDefend");
    
    public VoidDefend() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseBlock = block = 6;
    }
    
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new GainCustomBlockAction(new BlockModContainer(this, new VoidBlockMod()), p, block));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToTop(new GainCustomBlockAction(new BlockModContainer(this, new VoidBlockMod()), mo, block));
        }
    }
    
    public void upp() {
        upgradeBlock(2);
    }
}
