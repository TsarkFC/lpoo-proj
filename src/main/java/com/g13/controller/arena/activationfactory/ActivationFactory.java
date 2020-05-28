package com.g13.controller.arena.activationfactory;

import com.g13.controller.arena.cardactivation.AcSpecialCard;
import com.g13.controller.arena.cardactivation.endofturn.AcAddHpPerTurn;
import com.g13.controller.arena.cardactivation.endofturn.EndOfTurn;
import com.g13.controller.arena.cardactivation.instant.AcFluxModifierAtoB;
import com.g13.controller.arena.cardactivation.instant.AcInstantDamage;
import com.g13.controller.arena.cardactivation.instant.AcStaticModifier;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.endofturn.AddHpPerTurn;
import com.g13.model.arena.specialcards.instant.FluxModifierAtoB;
import com.g13.model.arena.specialcards.instant.InstantDamage;
import com.g13.model.arena.specialcards.instant.StaticModifier;

public class ActivationFactory {

    public AcSpecialCard getActivation(SpecialCard card){
        if (card instanceof AddHpPerTurn)
            return new AcAddHpPerTurn((AddHpPerTurn) card);
        else if (card instanceof FluxModifierAtoB)
            return new AcFluxModifierAtoB((FluxModifierAtoB) card);
        else if (card instanceof StaticModifier)
            return new AcStaticModifier((StaticModifier) card);
        else if (card instanceof InstantDamage)
            return new AcInstantDamage((InstantDamage) card);
        return null;
    }

    public EndOfTurn getEndOfTurnActivation(SpecialCard card){
        if (card instanceof AddHpPerTurn)
            return new AcAddHpPerTurn((AddHpPerTurn) card);
        return null;
    }
}
