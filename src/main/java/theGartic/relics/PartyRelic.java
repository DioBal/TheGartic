package theGartic.relics;

import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardSave;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import theGartic.GarticMod;
import theGartic.TheGartic;
import theGartic.actions.SummonOrbAction;
import theGartic.summons.AbstractSummonOrb;

import java.util.ArrayList;
import java.util.List;

import static theGartic.GarticMod.makeID;
import static theGartic.GarticMod.partySummons;

public class PartyRelic extends AbstractEasyRelic implements CustomSavable<List<AbstractSummonOrb>>
{
    public static final String ID = makeID(PartyRelic.class.getSimpleName());

    public List<AbstractSummonOrb> party = null;

    public PartyRelic() {
        super(ID, RelicTier.SPECIAL, LandingSound.FLAT, TheGartic.Enums.GARTIC_COLOR);
    }

    @Override
    public void atBattleStart()
    {
        flash();
        for (AbstractSummonOrb summon : party)
            addToBot(new SummonOrbAction(summon));
    }

    public void addToParty(AbstractSummonOrb orb)
    {
        if(party == null)
            party = new ArrayList<AbstractSummonOrb>();
        party.add(orb);
        //getUpdatedDescription();
        flash();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + getSummonsToString();
    }

    private String getSummonsToString()
    {
        String summons = "";
        if(party.size() > 0)
        {
            for (AbstractSummonOrb orb: party)
            {
                OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(orb.ID);
                summons += orbString.NAME;
                if(party.indexOf(orb) < party.size() - 1)
                    summons += DESCRIPTIONS[1];
            }
        }
        return summons;
    }

    @Override
    public List<AbstractSummonOrb> onSave()
    {
        if(party != null)
            return party;
        return null;
    }

    @Override
    public void onLoad(List<AbstractSummonOrb> partyLoad)
    {
        if(partyLoad != null)
            party = partyLoad;
    }
}

