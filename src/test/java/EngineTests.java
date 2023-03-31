import engine.field.MapGenerator;
import engine.field.parts.MapPart;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class EngineTests {

    @Test
    public void mapGeneratorTest() {
        MapPart[] mapParts = MapGenerator.getField(10);

        for (MapPart part : mapParts)
            System.out.println(part);

    }

}
