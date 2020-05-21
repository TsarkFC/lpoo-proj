package com.g13.controller.arena.activationfactory;

import com.g13.controller.arena.cardactivation.SpecialCardAc;
import com.g13.controller.arena.cardactivation.endofturn.AddHpPerTurnAc;
import com.g13.controller.arena.cardactivation.instant.FluxModifierAtoBAc;
import com.g13.controller.arena.cardactivation.instant.StaticModifierAc;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.endofturn.AddHpPerTurn;
import com.g13.model.arena.specialcards.instant.FluxModifierAtoB;
import com.g13.model.arena.specialcards.instant.StaticModifier;

public class ActivationFactory {

    public SpecialCardAc getActivation(SpecialCard card){
        if (card instanceof AddHpPerTurn)
            return new AddHpPerTurnAc((AddHpPerTurn) card);
        else if (card instanceof FluxModifierAtoB)
            return new FluxModifierAtoBAc((FluxModifierAtoB) card);
        else if (card instanceof StaticModifier)
            return new StaticModifierAc((StaticModifier) card);
        return null;
    }
}
