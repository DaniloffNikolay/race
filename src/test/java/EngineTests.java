import com.fasterxml.jackson.databind.ObjectMapper;
import engine.Game;
import engine.Player;
import engine.field.Cell;
import engine.field.Field;
import engine.field.MapGenerator;
import engine.field.parts.MapPart;
import engine.field.parts.Util;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EngineTests {

    @Test
    public void parseJSON() throws IOException {
        Player playerOne = new Player("Player one");
        Player playerTwo = new Player("Player two");
        Game game = new Game(Field.getInstance(10), playerOne, playerTwo);
        game.start();

        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(writer, playerOne);

        String result = writer.toString();
        System.out.println(result);
    }

    @Test
    public void mapGeneratorTest() {
        MapPart[] mapParts = MapGenerator.getField(10);

        MapPart part = mapParts[0];
        byte directionExit = part.getDirectionExit();

        for (int i = 1; i < mapParts.length; i++) {
            part = mapParts[i];
            assertEquals(part.getDirectionEnter(), Util.getDirectionEnter(directionExit));
            directionExit = part.getDirectionExit();
        }
    }
}
