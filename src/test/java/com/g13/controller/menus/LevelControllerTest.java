package com.g13.controller.menus;

import com.g13.controller.state.StateRecognizer;
import com.g13.model.menus.Level;
import com.g13.model.menus.button.Stage;
import com.g13.view.menus.LevelViewer;
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

    private int countUnlocked(Level model){
        int count = 0;
        for (Stage stage : model.getStages())
            if (stage.isUnlocked()) count++;
        return count;
    }
}
