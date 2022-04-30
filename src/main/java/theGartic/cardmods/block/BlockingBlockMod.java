package theGartic.cardmods.block;

import com.evacipated.cardcrawl.mod.stslib.blockmods.AbstractBlockModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import static theGartic.GarticMod.makeID;
import com.badlogic.gdx.graphics.Color;

public class BlockingBlockMod extends AbstractBlockModifier {
    
    public static final String ID = makeID("BlockingBlock");
    private Color c = Color.NAVY;
    
    public BlockingBlockMod () {};
    
    @Override
    public void onThisBlockDamaged (DamageInfo info, int loseAmount) {
        if (info.owner != null && loseAmount > 0) {
            this.addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, loseAmount)));
            this.addToBot(new ApplyPowerAction(owner, owner, new LoseDexterityPower(owner, loseAmount)));
        }
    }
    
    @Override
    public String getName() { return BaseMod.getKeywordTitle(ID.toLowerCase()); }
    
    @Override
    public String getDescription() { return BaseMod.getKeywordDescription(ID.toLowerCase()); }
    
    @Override
    public Color blockImageColor() { return c; }
    
    @Override
    public Color blockTextColor() { return c; }
     
    @Override 
    public AbstractBlockModifier makeCopy() { 
        return new BlockingBlockMod();
    }

}