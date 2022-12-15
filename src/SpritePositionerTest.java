import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpritePositionerTest {

    @Test
    void createSprite() {
        assertEquals("###", SpritePositioner.createSprite(1));
        assertEquals("...............###", SpritePositioner.createSprite(16));
        assertEquals("....###", SpritePositioner.createSprite(5));
    }

    @Test
    void createSpriteValue() {
        assertEquals("#", SpritePositioner.getValue(0,1));
        assertEquals("#", SpritePositioner.getValue(1,1));
        assertEquals(".", SpritePositioner.getValue(2,16));
        assertEquals(".", SpritePositioner.getValue(3,5));
        assertEquals(".", SpritePositioner.getValue(3,5));


    }
}