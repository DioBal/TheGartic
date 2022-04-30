package theGartic.cardmods.block;

import com.evacipated.cardcrawl.mod.stslib.blockmods.AbstractBlockModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import static theGartic.GarticMod.makeID;
import com.badlogic.gdx.graphics.Color;

public class PlatedBlockMod extends AbstractBlockModifier {
    
    public static final String ID = makeID("PlatedBlock");
    private Color c = Color.LIGHT_GRAY;
    
    public PlatedBlockMod () {};
    
    @Override
    public void onThisBlockDamaged (DamageInfo info, int loseAmount) {
        if (info.owner != null && loseAmount > 0)
        this.addToBot(new ApplyPowerAction(owner, owner, new PlatedArmorPower(owner, loseAmount)));
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
        return new PlatedBlockMod();
    }

}