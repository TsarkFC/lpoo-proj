package com.g13.controller.menus;

import com.g13.controller.state.StateRecognizer;
import com.g13.model.menus.Level;
import com.g13.model.menus.Start;
import com.g13.view.menus.LevelViewer;
import com.g13.view.menus.StartViewer;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartControllerTest {
    @Property
    public void testCrossMovement(@ForAll @IntRange(min = 1, max = 10) int iterations){
        Start model = new Start();
        StartViewer viewer = Mockito.mock(StartViewer.class);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        StartController controller = new StartController(model, viewer, recognizer);

        assertEquals(0, model.getSelection());

        for (int i = 0; i < iterations; i++){
            controller.moveDown();
            controller.moveUp();
        }
        assertEquals(0, model.getSelection());

        for (int i = 0; i < iterations; i++)
            controller.moveUp();

        assertEquals(0, model.getSelection());

        for (int i = 0; i < iterations; i++)
            controller.moveDown();

        if (iterations < 2)
            assertEquals(iterations, model.getSelection());
        else
            assertEquals(1, model.getSelection());
    }
}
