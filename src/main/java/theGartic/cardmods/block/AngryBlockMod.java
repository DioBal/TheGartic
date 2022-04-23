package theGartic.cardmods.block;

import com.evacipated.cardcrawl.mod.stslib.blockmods.AbstractBlockModifier;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import basemod.BaseMod;
import com.megacrit.cardcrawl.cards.DamageInfo;
import static theGartic.GarticMod.makeID;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.badlogic.gdx.graphics.Color;

public class AngryBlockMod extends AbstractBlockModifier {
    
    public static final String ID = makeID("AngryBlock");
    private Color c = Color.RED;
    
    public AngryBlockMod () {};

    public int onRemove(boolean lose, DamageInfo info, int rd) {
        if (owner == AbstractDungeon.player && !lose) this.addToBot(new ChangeStanceAction("Wrath"));
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
        return new AngryBlockMod();
    }

}