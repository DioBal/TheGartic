package theGartic.powers;

import theGartic.summons.AbstractSummonOrb;

public interface OnSummonPower {

    default void onSummon(AbstractSummonOrb orb) {}
}
