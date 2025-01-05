package game;

import game.Jatek;
import game.Player;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JatekTest {

    @Test
    public void testCurrentPlayer() throws NoSuchFieldException, IllegalAccessException {

        Jatek jatek = new Jatek("Player 1", "Player 2");


        Field currentPlayerField = Jatek.class.getDeclaredField("currentPlayer");
        currentPlayerField.setAccessible(true);


        Player currentPlayer = (Player) currentPlayerField.get(jatek);


        assertEquals("Player 1", currentPlayer.getName());
    }
}
