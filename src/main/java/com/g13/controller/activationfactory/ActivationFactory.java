package com.g13.controller.activationfactory;

import com.g13.controller.cardactivation.SpecialCardAc;
import com.g13.controller.cardactivation.endofturn.AddHpPerTurnAc;
import com.g13.controller.cardactivation.instant.FluxModifierAtoBAc;
import com.g13.controller.cardactivation.instant.StaticModifierAc;
import com.g13.model.specialcards.SpecialCard;
import com.g13.model.specialcards.endofturn.AddHpPerTurn;
import com.g13.model.specialcards.instant.FluxModifierAtoB;
import com.g13.model.specialcards.instant.StaticModifier;

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
