import engine.field.Cell;
import engine.field.MapGenerator;
import engine.field.parts.MapPart;
import engine.field.parts.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EngineTests {

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
