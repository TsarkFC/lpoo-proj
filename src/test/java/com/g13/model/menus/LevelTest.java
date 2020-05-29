package com.g13.model.menus;

import com.g13.model.menus.button.Stage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevelTest {
    @Test
    public void constructorTest(){
        Level level = new Level();

        int count = 0;
        for (Stage stage : level.getStages())
            if (stage.isUnlocked()) count++;

        assertEquals(2, count);
    }

    @Test
    public void testCross(){
        Level level = new Level();
        level.crossMoveDown();
        level.crossMoveDown();
        level.crossMoveUp();
        assertEquals(level.getCross(), 1);
        assertEquals(level.getX(), 12);
        level.resetCross();
        assertEquals(level.getCross(), 0);
        assertEquals(level.getNextCross(), true);
    }
}
