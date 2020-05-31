package com.g13.model.arena;

import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.endofturn.AddHpPerTurn;
import com.g13.model.arena.specialcards.endofturn.DamageGamble;
import com.g13.model.arena.specialcards.endofturn.OnWinDamage;
import com.g13.model.arena.specialcards.instant.FluxModifierAtoB;
import com.g13.model.arena.specialcards.instant.InstantDamage;
import com.g13.model.arena.specialcards.instant.StaticModifier;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialCardTest {
    @Test
    public void testConstructor(){
        SpecialCard card = new SpecialCard(5, '+', "Testing card info");

        assertEquals(5, card.getCost());
        assertEquals('+', card.getSymbol());
        assertEquals("Testing card info", card.getCardInfo());
    }
    @Test
    public void specialCardsTest(){
        StaticModifier card1 = new StaticModifier(2, '*', " ", 5);
        InstantDamage card2 = new InstantDamage(2, '*', " ", 5);
        OnWinDamage card3 = new OnWinDamage(2, '*', " ", 5);
        AddHpPerTurn card4 = new AddHpPerTurn(4, '*', " ", 5, 2);
        DamageGamble card5 = new DamageGamble(2, '*', " ", 5);
        assertEquals(card1.getModNum(), card2.getDamage());
        assertEquals(card2.getDamage(), card3.getDamage());
        assertEquals(card3.getDamage(), card4.getHPPerTurn());
        assertEquals(card4.getHPPerTurn(), card5.getmanaPerDamageTaken());

        card4.decrementRoundsLeft();
        assertEquals(1, card4.getRoundsLeft());
    }

    @Property
    public void fluxTest(@ForAll @IntRange(min = 2, max = 5) int min, @ForAll @IntRange(min = 6, max = 10) int max){
        FluxModifierAtoB flux = new FluxModifierAtoB(2, '*', " ", min, max);
        assertEquals(flux.getMaxModNum(), max);
        assertEquals(flux.getMinModNum(), min);
    }
}
