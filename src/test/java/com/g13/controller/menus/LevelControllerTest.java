package com.g13.controller.menus;

import com.g13.controller.state.StateRecognizer;
import com.g13.model.menus.Level;
import com.g13.model.menus.button.Stage;
import com.g13.view.menus.LevelViewer;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevelControllerTest {
    @Test
    public void testunlockNextStage(){
        Level model = new Level();
        LevelViewer viewer = Mockito.mock(LevelViewer.class);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        LevelController controller = new LevelController(model, viewer, recognizer);

        int count = countUnlocked(model);
        controller.unlockNextStage(1);
        assertEquals(Math.min(count + 1, model.getStages().size()), countUnlocked(model));
    }

    @Test
    public void testAllUnlocked(){
        Level model = new Level();
        LevelViewer viewer = Mockito.mock(LevelViewer.class);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        LevelController controller = new LevelController(model, viewer, recognizer);

        for (Stage stage : model.getStages())
            if (!stage.isUnlocked()) stage.setUnlocked(true);

        int count = countUnlocked(model);
        controller.unlockNextStage(3);
        assertEquals(count, countUnlocked(model));
    }

    @Test
    public void lockStagesTest(){
        Level model = new Level();
        LevelViewer viewer = Mockito.mock(LevelViewer.class);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        LevelController controller = new LevelController(model, viewer, recognizer);
        controller.unlockNextStage(1);
        controller.unlockNextStage(2);

        controller.lockStages();

        assertEquals(countUnlocked(model), 2);
    }

    @Property
    public void testCrossMovement(@ForAll @IntRange(min = 1, max = 10) int iterations){
        Level model = new Level();
        LevelViewer viewer = Mockito.mock(LevelViewer.class);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        LevelController controller = new LevelController(model, viewer, recognizer);
        controller.unlockNextStage(1);
        controller.unlockNextStage(2);

        assertEquals(0, model.getCross());

        for (int i = 0; i < iterations; i++){
            controller.moveDown();
            controller.moveUp();
        }
        assertEquals(0, model.getCross());

        for (int i = 0; i < iterations; i++)
            controller.moveUp();

        assertEquals(0, model.getCross());

        for (int i = 0; i < iterations; i++)
            controller.moveDown();

        if (iterations < 4)
            assertEquals(iterations, model.getCross());
        else
            assertEquals(3, model.getCross());
    }

    @Test
    public void renderTest() throws IOException {
        Level model = new Level();
        LevelViewer viewer = Mockito.mock(LevelViewer.class);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        LevelController controller = new LevelController(model, viewer, recognizer);
        Mockito.doNothing().when(viewer).draw();
        controller.render();

        Mockito.verify(viewer, Mockito.times(1)).draw();
    }

    @Test
    public void isFinishedTest(){
        Level model = new Level();
        LevelViewer viewer = Mockito.mock(LevelViewer.class);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        LevelController controller = new LevelController(model, viewer, recognizer);
        assertEquals(false, controller.isFinished());
    }

    private int countUnlocked(Level model){
        int count = 0;
        for (Stage stage : model.getStages())
            if (stage.isUnlocked()) count++;
        return count;
    }
}
