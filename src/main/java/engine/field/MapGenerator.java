package engine.field;

import engine.field.parts.MapPart;
import engine.field.parts.StandardPartMapStart;
import engine.field.parts.Util;

import java.util.Random;

public class MapGenerator {

    public static MapPart[] getField(int size) {
        MapPart[] map = new MapPart[size];
        Random random = new Random();
        map[0] = getStartPart(random);
        byte directionExit = map[0].getDirectionExit();

        for (int i = 1; i < size; i++) {
            if (i == size - 1) { //последний парт
                map[i] = getFinishPart(random, directionExit);
            } else {
                map[i] = getOftenPart(random, directionExit);
            }
        }

        return map;
    }

    private static MapPart getStartPart(Random random) {
        return Util.getMapPart(StandardPartMapStart.getInitPartStart(random.nextInt(StandardPartMapStart.COUNT)), "");
    }

    private static MapPart getFinishPart(Random random, byte direction) {
        return null;
    }

    private static MapPart getOftenPart(Random random, byte direction) {
        return null;
    }
}
