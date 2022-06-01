package theGartic.cards.summonOptions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.cards.AbstractEasyCard;
import theGartic.cards.EasyModalChoiceCard;
import theGartic.relics.PartyRelic;

import java.util.LinkedHashMap;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.adp;

public abstract class AbstractSummonOption  extends AbstractEasyCard
{
    private boolean summon;
    private boolean addToParty;
    private String ID;
    public AbstractSummonOption(String ID, String name, String description, CardType type, boolean summon, boolean addToParty)
    {
        super(ID, -2, type, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        this.name = this.originalName = name;
        this.rawDescription = description;
        this.ID = ID;
        initializeTitle();
        initializeDescription();
        this.summon = summon;
        this.addToParty = addToParty;
    }

    public static LinkedHashMap<AbstractSummonOption, Integer> weightedSummons;

    public static AbstractCard returnRandomSummon(boolean summon, boolean addToParty)
    {
        if (weightedSummons == null)
        {
            weightedSummons = new LinkedHashMap<>();
            weightedSummons.put(new FireImpOption(summon, addToParty),       40);
            weightedSummons.put(new PandaOption(summon, addToParty),     21);
        }

        int summonRoll = AbstractDungeon.cardRandomRng.random( 1,
                weightedSummons.keySet().stream()
                        .mapToInt(f -> weightedSummons.get(f))
                        .sum()
        );

        for (AbstractSummonOption summonOption: weightedSummons.keySet())
        {
            summonRoll -= weightedSummons.get(summonOption);
            if (summonRoll <= 0)
                return summonOption.makeCopy();
        }

        return new Madness();
    }

    @Override
    public void onChoseThisOption() {
        OnPickOption(false, true);
}

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        OnPickOption(false, true);
    }

    public void OnPickOption(boolean summon, boolean addToParty)
    {
        if(summon)
            OnSummon();
        if(addToParty)
            AddToParty();
    }

    public abstract void OnSummon();

    public void AddToParty()
    {
        if(!adp().hasRelic(PartyRelic.ID))
        {
            adp().relics.add(new PartyRelic());
            adp().reorganizeRelics();
        }
        ((PartyRelic)adp().getRelic(PartyRelic.ID)).addToParty(this);
    }

    @Override
    public void upp() {

    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public AbstractCard makeCopy() {
        return new AbstractSummonOption(ID, name, rawDescription, type, summon, addToParty)
        {
            @Override
            public void OnSummon()
            {

            }
        };
    }
}
