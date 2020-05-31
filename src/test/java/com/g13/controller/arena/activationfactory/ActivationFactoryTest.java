package com.g13.controller.arena.activationfactory;

import com.g13.controller.arena.cardactivation.endofturn.AcAddHpPerTurn;
import com.g13.controller.arena.cardactivation.endofturn.AcDamageGamble;
import com.g13.controller.arena.cardactivation.endofturn.AcOnWinDamage;
import com.g13.controller.arena.cardactivation.instant.AcFluxModifierAtoB;
import com.g13.controller.arena.cardactivation.instant.AcInstantDamage;
import com.g13.controller.arena.cardactivation.instant.AcStaticModifier;
import com.g13.model.arena.specialcards.endofturn.AddHpPerTurn;
import com.g13.model.arena.specialcards.endofturn.DamageGamble;
import com.g13.model.arena.specialcards.endofturn.OnWinDamage;
import com.g13.model.arena.specialcards.instant.FluxModifierAtoB;
import com.g13.model.arena.specialcards.instant.InstantDamage;
import com.g13.model.arena.specialcards.instant.StaticModifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivationFactoryTest {

    //Not testing for return null

    @Test
    public void AddHpPerTurnTest(){

        AddHpPerTurn add = new AddHpPerTurn(0, 'a', "abc", 1, 3);

        ActivationFactory act = new ActivationFactory();
        assertEquals(act.getActivation(add) instanceof AcAddHpPerTurn, true);
        assertEquals(act.getEndOfTurnActivation(add) instanceof AcAddHpPerTurn, true);
    }

    @Test
    public void FluxModifierAtoBTest(){

        FluxModifierAtoB add = new FluxModifierAtoB(0, 'a', "abc", 1, 3);

        ActivationFactory act = new ActivationFactory();
        assertEquals(act.getActivation(add) instanceof AcFluxModifierAtoB, true);
    }

    @Test
    public void StaticModifierTest(){

        StaticModifier add = new StaticModifier(0, 'a', "abc", 1);

        ActivationFactory act = new ActivationFactory();
        assertEquals(act.getActivation(add) instanceof AcStaticModifier, true);
    }

    @Test
    public void InstantDamageTest(){

        InstantDamage add = new InstantDamage(0, 'a', "abc", 1);

        ActivationFactory act = new ActivationFactory();
        assertEquals(act.getActivation(add) instanceof AcInstantDamage, true);
    }

    @Test
    public void OnWinDamageTest(){

        OnWinDamage add = new OnWinDamage(0, 'a', "abc", 1);

        ActivationFactory act = new ActivationFactory();
        assertEquals(act.getActivation(add) instanceof AcOnWinDamage, true);
        assertEquals(act.getEndOfTurnActivation(add) instanceof AcOnWinDamage, true);
    }

    @Test
    public void DamageGambleTest(){

        DamageGamble add = new DamageGamble(0, 'a', "abc", 1);

        ActivationFactory act = new ActivationFactory();
        assertEquals(act.getActivation(add) instanceof AcDamageGamble, true);
        assertEquals(act.getEndOfTurnActivation(add) instanceof AcDamageGamble, true);
    }

}
