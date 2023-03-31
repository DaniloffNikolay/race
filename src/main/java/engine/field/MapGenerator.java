package engine.field;

import engine.field.parts.*;

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
                directionExit = map[i].getDirectionExit();
            } else {
                map[i] = getOftenPart(random, directionExit);
                directionExit = map[i].getDirectionExit();
            }
        }

        return map;
    }

    private static MapPart getStartPart(Random random) {
        return Util.getMapPart(StandardPartMapStart.getInitPartStart(random.nextInt(StandardPartMapStart.COUNT)), "");
    }

    private static MapPart getFinishPart(Random random, byte directionExit) {
        return Util.getMapPart(StandardPartMapFinish.getInitPartFinish(random, directionExit), "");
    }

    private static MapPart getOftenPart(Random random, byte directionExit) {
        return Util.getMapPart(StandardPartMap.getInitPart(random, directionExit), "");
    }
}
