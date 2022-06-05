package theGartic.cards.summonOptions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.cards.AbstractEasyCard;
import theGartic.relics.PartyRelic;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static theGartic.util.Wiz.adp;

public abstract class AbstractSummonOption  extends AbstractEasyCard
{
    private boolean summon;
    private boolean addToParty;

    public AbstractSummonOption(String ID, CardType type, boolean summon, boolean addToParty)
    {
        super(ID, -2, type, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        this.summon = summon;
        this.addToParty = addToParty;
    }

    public static LinkedHashMap<String, Integer> weightedSummons;

    public static void makeWeightedList() {
        if (weightedSummons == null)
        {
            weightedSummons = new LinkedHashMap<>();
            weightedSummons.put(FireImpOption.ID, 40);
            weightedSummons.put(PandaOption.ID, 20);
            weightedSummons.put(HungryFoxOption.ID, 40);
        }
    }

    public static AbstractCard returnRandomSummon(boolean summon, boolean addToParty)
    {
        makeWeightedList();

        int summonRoll = AbstractDungeon.cardRandomRng.random( 1,
                weightedSummons.keySet().stream()
                        .mapToInt(f -> weightedSummons.get(f))
                        .sum()
        );

        for (String cardID : weightedSummons.keySet())
        {
            summonRoll -= weightedSummons.get(cardID);
            if (summonRoll <= 0) {
                AbstractSummonOption card = (AbstractSummonOption)CardLibrary.getCard(cardID);
                card.summon = summon;
                card.addToParty = addToParty;
                return card.makeCopy();
            }
        }

        return null;
    }

    public static ArrayList<AbstractCard> returnRandomSummons(boolean summon, boolean addToParty, int num)
    {
        makeWeightedList();
        ArrayList<AbstractCard> summonList = new ArrayList<>();

        String removeLaterID = null;

        LinkedHashMap<String, Integer> weightedCopy = new LinkedHashMap<>(weightedSummons);

        for (int i = 0; i < num; i++) {
            if (weightedCopy.isEmpty())
                return summonList;

            int summonRoll = AbstractDungeon.cardRandomRng.random(1,
                    weightedCopy.keySet().stream()
                            .mapToInt(weightedCopy::get)
                            .sum()
            );

            for (String cardID : weightedCopy.keySet()) {
                summonRoll -= weightedCopy.get(cardID);
                if (summonRoll <= 0) {
                    AbstractSummonOption card = (AbstractSummonOption)CardLibrary.getCard(cardID);
                    card.summon = summon;
                    card.addToParty = addToParty;
                    summonList.add(card.makeCopy());
                    removeLaterID = cardID;
                    break;
                }
            }

            if (removeLaterID != null)
                weightedCopy.remove(removeLaterID);
        }

        return summonList;
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
        AbstractSummonOption card = (AbstractSummonOption) super.makeCopy();

        card.summon = summon;
        card.addToParty = addToParty;

        return card;
    }
}
