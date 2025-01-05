package game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayerConstructorAndGetters() {

        String expectedName = "jatekos 1";
        String expectedColor = "jatekos 2";

        Player player = new Player(expectedName, expectedColor);

        assertEquals(expectedName, player.getName(), "Player name should match the input value.");
        assertEquals(expectedColor, player.getColor(), "Player color should match the input value.");
    }
}
