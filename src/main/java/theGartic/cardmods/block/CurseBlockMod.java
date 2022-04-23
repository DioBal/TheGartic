package theGartic.cardmods.block;

import com.evacipated.cardcrawl.mod.stslib.blockmods.AbstractBlockModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import basemod.BaseMod;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import static theGartic.GarticMod.makeID;
import com.badlogic.gdx.graphics.Color;

public class CurseBlockMod extends AbstractBlockModifier {
    
    public static final String ID = makeID("CurseBlock");
    private Color c = Color.BLACK;
    
    public CurseBlockMod () {};

    public int onRemove(boolean lose, DamageInfo info, int rd) {
        if (owner == AbstractDungeon.player && !lose) {
            this.addToBot(new ApplyPowerAction(owner, owner, new VulnerablePower(owner, 1, false)));
            this.addToBot(new ApplyPowerAction(owner, owner, new WeakPower(owner, 1, false)));
            this.addToBot(new ApplyPowerAction(owner, owner, new FrailPower(owner, 1, false)));
        }

        return rd;
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
        return new CurseBlockMod();
    }

}