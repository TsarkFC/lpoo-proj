package com.g13.controller.menus;

import com.g13.controller.state.StateRecognizer;
import com.g13.model.menus.Level;
import com.g13.model.menus.button.Stage;
import com.g13.view.menus.LevelViewer;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevelControllerTest {
    @Test
    public void testunlockNextStage(){
        Level model = new Level();
        LevelViewer viewer = Mockito.mock(LevelViewer.class);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        LevelController controller = new LevelController(model, viewer, recognizer);

        int count = countUnlocked(model);
        controller.unlockNextStage();
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
        controller.unlockNextStage();
        assertEquals(count, countUnlocked(model));
    }

    @Test
    public void lockStagesTest(){
        Level model = new Level();
        LevelViewer viewer = Mockito.mock(LevelViewer.class);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        LevelController controller = new LevelController(model, viewer, recognizer);
        controller.unlockNextStage();
        controller.unlockNextStage();

        controller.lockStages();

        assertEquals(countUnlocked(model), 2);
    }

    @Property
    public void testCrossMovement(@ForAll @IntRange(min = 1, max = 10) int iterations){
        Level model = new Level();
        LevelViewer viewer = Mockito.mock(LevelViewer.class);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        LevelController controller = new LevelController(model, viewer, recognizer);
        controller.unlockNextStage();
        controller.unlockNextStage();

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

    private int countUnlocked(Level model){
        int count = 0;
        for (Stage stage : model.getStages())
            if (stage.isUnlocked()) count++;
        return count;
    }
}
