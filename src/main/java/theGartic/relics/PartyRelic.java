package theGartic.relics;

import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.cards.CardSave;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.CardStrings;
import theGartic.TheGartic;
import theGartic.cards.summonOptions.AbstractSummonOption;

import java.util.ArrayList;

import static theGartic.GarticMod.makeID;

public class PartyRelic extends AbstractEasyRelic implements CustomSavable<ArrayList<CardSave>>
{
    public static final String ID = makeID(PartyRelic.class.getSimpleName());

    public ArrayList<AbstractSummonOption> party = null;

    public PartyRelic() {
        super(ID, RelicTier.SPECIAL, LandingSound.FLAT, TheGartic.Enums.GARTIC_COLOR);
    }

    @Override
    public void atBattleStart()
    {
        flash();
        for (AbstractSummonOption summon : party){
            summon.OnSummon();
        }
    }

    public void addToParty(AbstractSummonOption summonOption)
    {
        if(party == null)
            getParty();
        party.add(summonOption);
        flash();
    }

    private void getParty()
    {
        party = new ArrayList<>();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + getSummonsToString();
    }

    private String getSummonsToString()
    {
        String summons = "";
        if(party != null && party.size() > 0)
        {
            for (AbstractSummonOption summon: party)
            {
                CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(summon.cardID);
                summons += cardStrings.NAME;
                if(party.indexOf(summon) < party.size() - 1)
                    summons += DESCRIPTIONS[1];
            }
        }
        return summons;
    }

    @Override
    public ArrayList<CardSave> onSave()
    {
        if(party != null)
        {
            ArrayList<CardSave> cardSaves = new ArrayList<>();
            for (AbstractSummonOption card: party)
                cardSaves.add(new CardSave(card.cardID, card.timesUpgraded, card.misc));

            return cardSaves;
        }
        return null;
    }

    @Override
    public void onLoad(ArrayList<CardSave> partyLoad)
    {
        getParty();
        if(partyLoad != null)
        {
            for (CardSave card: partyLoad)
            {
                if (card == null)
                    continue;
                AbstractSummonOption savedCard = (AbstractSummonOption) CardLibrary.getCard(card.id);
                savedCard.timesUpgraded = card.upgrades;
                savedCard.misc = card.misc;

                party.add(savedCard);
            }
        }
        resetDescription();
    }

    public void resetDescription() {
        tips.clear();
        description = getUpdatedDescription();
        tips.add(new PowerTip(name, description));
        initializeTips();
    }
}

