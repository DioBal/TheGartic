package theGartic.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.GarticMod;
import theGartic.actions.SummonOrbAction;
import theGartic.summons.MirroredFoxSummon;

import java.util.ArrayList;
import java.util.List;

import static theGartic.GarticMod.makeID;

public class MirroredFox extends AbstractEasyCard {
    public final static String ID = makeID(MirroredFox.class.getSimpleName());
    
    public MirroredFox() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        magicNumber = baseMagicNumber = 1;
        tags.add(GarticMod.Enums.SUMMON);
    }
    
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SummonOrbAction(new MirroredFoxSummon(), magicNumber));
    }
    
    public void upp() {
        upgradeBaseCost(1);
    }
    
    @Override
    public List<String> getCardDescriptors() {
        List<String> tags = new ArrayList<>();
        tags.add(BaseMod.getKeywordTitle("garticmod:summon"));
        return tags;
    }
}