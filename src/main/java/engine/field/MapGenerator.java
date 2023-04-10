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

    private static class BorderMap {
        private MapPart[][] allMap;

        public BorderMap(int size) {
            allMap = getHeightAndWidth(size);



        }

        private MapPart[][] getHeightAndWidth(int size) {
            Random random = new Random();

            if (size <= 10)
                return tenField(random);
            else if (size <= 20)
                return twentyField(random);
            else if (size <= 30)
                return thirtyField(random);
            else if (size <= 40)
                return fortyField(random);
            else if (size <= 50)
                return fiftyField(random);
            else if (size <= 100)
                return oneHundredField(random);

            return null;
        }

        private MapPart[][] tenField(Random random) {
            int i = random.nextInt(3);
            switch (i) {
                case 0 :
                    return new MapPart[3][3];
                case 1:
                    return new MapPart[2][5];
                case 2:
                    return new MapPart[5][2];
            }
            return null;
        }

        private MapPart[][] twentyField(Random random) {
            int i = random.nextInt(5);
            switch (i) {
                case 0 :
                case 1:
                case 2:
                    return new MapPart[3][3];
                case 3:
                    return new MapPart[2][5];
                case 4:
                    return new MapPart[5][2];
            }
            return null;
        }

        private MapPart[][] thirtyField(Random random) {
            int i = random.nextInt(5);
            switch (i) {
                case 0 :
                    return new MapPart[4][4];
                case 1:
                    return new MapPart[3][6];
                case 2:
                    return new MapPart[6][3];
                case 3:
                    return new MapPart[5][6];
                case 4:
                    return new MapPart[6][5];
            }
            return null;
        }

        private MapPart[][] fortyField(Random random) {
            int i = random.nextInt(5);
            switch (i) {
                case 0 :
                    return new MapPart[5][5];
                case 1:
                    return new MapPart[4][7];
                case 2:
                    return new MapPart[7][4];
                case 3:
                    return new MapPart[5][8];
                case 4:
                    return new MapPart[8][5];

            }
            return null;
        }

        private MapPart[][] fiftyField(Random random) {
            int i = random.nextInt(7);
            switch (i) {
                case 0 :
                    return new MapPart[7][7];
                case 1:
                    return new MapPart[10][5];
                case 2:
                    return new MapPart[5][10];
                case 3:
                    return new MapPart[8][6];
                case 4:
                    return new MapPart[6][8];
                case 5:
                    return new MapPart[5][9];
                case 6:
                    return new MapPart[9][5];
            }
            return null;
        }

        private MapPart[][] oneHundredField(Random random) {
            return new MapPart[10][10];
        }
    }
}
