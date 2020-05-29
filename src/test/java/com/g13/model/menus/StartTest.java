package com.g13.model.menus;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartTest {
    @Property
    public void startTest(@ForAll int selection){
        Start start = new Start();
        start.setFinished(true);
        assertEquals(true, start.isFinished());
        assertEquals(start.getButtons().size(), 2);
        assertEquals(start.getButtons().get(0).getMessage(), "Play game");
        assertEquals(start.getButtons().get(0).getX(), 20);
        assertEquals(start.getButtons().get(0).getY(), 13);

        start.setSelection(selection);
        start.selectionMoveDown();
        start.selectionMoveUp();
        assertEquals(selection, start.getSelection());

        assertEquals(start.getTitle_tyrant().length, start.getTitle_void().length);
    }
}
