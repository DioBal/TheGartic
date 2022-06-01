package theGartic.relics;

import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardSave;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.OrbStrings;
import theGartic.GarticMod;
import theGartic.TheGartic;
import theGartic.actions.SummonOrbAction;
import theGartic.cards.summonOptions.AbstractSummonOption;
import theGartic.summons.AbstractSummonOrb;

import java.util.ArrayList;
import java.util.List;

import static theGartic.GarticMod.makeID;
import static theGartic.GarticMod.partySummons;

public class PartyRelic extends AbstractEasyRelic // implements CustomSavable<List<CardSave>>
{
    public static final String ID = makeID(PartyRelic.class.getSimpleName());

    public List<AbstractSummonOption> party = null;

    public PartyRelic() {
        super(ID, RelicTier.SPECIAL, LandingSound.FLAT, TheGartic.Enums.GARTIC_COLOR);
    }

    @Override
    public void atBattleStart()
    {
        flash();
        for (AbstractSummonOption summon : party)
            summon.OnSummon();
    }

    public void addToParty(AbstractSummonOption summonOption)
    {
        if(party == null)
            GetParty();
        party.add(summonOption);
        //getUpdatedDescription();
        flash();
    }

    private void GetParty()
    {
        party = new ArrayList<AbstractSummonOption>();
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
/*
    @Override
    public List<CardSave> onSave()
    {
        if(party != null)
        {
            List<CardSave> cardSaves = new ArrayList<>();
            for (AbstractSummonOption card: party)
                cardSaves.add(new CardSave(card.cardID, card.timesUpgraded, card.misc));

            return cardSaves;
        }
        return null;
    }

    @Override
    public void onLoad(List<CardSave> partyLoad)
    {
        if(partyLoad != null)
        {
            for (CardSave card: partyLoad)
            {
                AbstractSummonOption savedCard = (AbstractSummonOption) CardLibrary.getCard(card.id);
                savedCard.timesUpgraded = card.upgrades;
                savedCard.misc = card.misc;

                party.add(savedCard);
            }
        }
    }*/
}

