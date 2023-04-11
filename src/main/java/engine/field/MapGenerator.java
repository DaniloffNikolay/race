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

    public static Cell[][] getFullField(int size) {
        BorderMap map = new BorderMap(size);
        map.fillMap();
        return map.buildFullField();
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
        private Random random;
        private MapPart[][] allMap;

        private int coordinateXLastPart;
        private int coordinateYLastPart;
        private byte directionExitLastPart;
        private byte[] exitDirectionWhichCannotBeUsed;

        private BorderMap(int size) {
            random = new Random();
            allMap = getHeightAndWidth(size);
        }

        private void fillMap() {
            boolean isAdd = false;

            while (!isAdd) {
                isAdd = addPart(getStartPart(random));
            }

            while (true) {
                if (checkIsNextPartLast()) {
                    isAdd = addPart(getFinishPart(random, directionExitLastPart));
                    if (isAdd)
                        break;
                } else {
                    addPart(getOftenPart(random, directionExitLastPart));
                }
            }
        }

        /**
         * Метод добавляет кусок карты
         * @return false если не получилось добавить
         */
        private boolean addPart(MapPart part) {
            if (part.isStartingPart()) { //начало трассы
                int y = random.nextInt(allMap.length);
                int x = random.nextInt(allMap[y].length);
                byte directionExit = part.getDirectionExit();

                if (checkBorderOnExit(x, y, directionExit)) { //добавить можно
                    allMap[y][x] = part;
                    coordinateXLastPart = x;
                    coordinateYLastPart = y;
                    directionExitLastPart = directionExit;
                    return true;
                }
            } else if (part.isFinalPart()) {
                byte directionExit = part.getDirectionExit();
                switch (directionExit) {
                    case 1:
                        if (part.getDirectionEnter() != Util.getDirectionEnter(directionExitLastPart))
                            break;
                        if (checkBorderOnExit(coordinateXLastPart, coordinateYLastPart - 1, directionExit)) {
                            if (checkPart(coordinateXLastPart, coordinateYLastPart - 1)) {
                                allMap[coordinateYLastPart - 1][coordinateXLastPart] = part;
                                coordinateYLastPart = coordinateYLastPart - 1;
                                directionExitLastPart = directionExit;
                                return true;
                            }
                        }
                        break;
                    case 2:
                        if (part.getDirectionEnter() != Util.getDirectionEnter(directionExitLastPart))
                            break;
                        if (checkBorderOnExit(coordinateXLastPart, coordinateYLastPart + 1, directionExit)) {
                            if (checkPart(coordinateXLastPart, coordinateYLastPart + 1)) {
                                allMap[coordinateYLastPart + 1][coordinateXLastPart] = part;
                                coordinateYLastPart = coordinateYLastPart + 1;
                                directionExitLastPart = directionExit;
                                return true;
                            }
                        }
                        break;
                    case 3:
                        if (part.getDirectionEnter() != Util.getDirectionEnter(directionExitLastPart))
                            break;
                        if (checkBorderOnExit(coordinateXLastPart - 1, coordinateYLastPart, directionExit)) {
                            if (checkPart(coordinateXLastPart - 1, coordinateYLastPart)) {
                                allMap[coordinateYLastPart][coordinateXLastPart - 1] = part;
                                coordinateXLastPart = coordinateXLastPart - 1;
                                directionExitLastPart = directionExit;
                                return true;
                            }
                        }
                        break;
                    case 4:
                        if (part.getDirectionEnter() != Util.getDirectionEnter(directionExitLastPart))
                            break;
                        if (checkBorderOnExit(coordinateXLastPart + 1, coordinateYLastPart, directionExit)) {
                            if (checkPart(coordinateXLastPart + 1, coordinateYLastPart)) {
                                allMap[coordinateYLastPart][coordinateXLastPart + 1] = part;
                                coordinateXLastPart = coordinateXLastPart + 1;
                                directionExitLastPart = directionExit;
                                return true;
                            }
                        }
                        break;
                }
            } else {
                byte directionExit = part.getDirectionExit();
                switch (directionExit) {
                    case 1:
                        return addPart(coordinateXLastPart, coordinateYLastPart - 1, part);
                    case 2:
                        return addPart(coordinateXLastPart, coordinateYLastPart + 1, part);
                    case 3:
                        return addPart(coordinateXLastPart - 1, coordinateYLastPart, part);
                    case 4:
                        return addPart(coordinateXLastPart + 1, coordinateYLastPart, part);
                }
            }

            return false;
        }

        private boolean addPart(int x, int y, MapPart part) {
            byte directionExit = part.getDirectionExit();
            if (part.getDirectionEnter() != Util.getDirectionEnter(directionExitLastPart))
                return false;

            if (exitDirectionWhichCannotBeUsed != null) {
                for (byte b : exitDirectionWhichCannotBeUsed) {
                    if (b == directionExit)
                        return false;
                }
            }

            if (checkBorderOnExit(x, y, directionExit)) {
                if (checkPart(x, y)) {
                    allMap[y][x] = part;
                    coordinateXLastPart = x;
                    coordinateYLastPart = y;
                    directionExitLastPart = directionExit;
                    return true;
                }
            }

            return false;
        }

        /**
         * Метод проверяет будет ли даная часть последней
         * @return
         */
        private boolean checkIsNextPartLast() {
            fillExitDirectionWhichCannotBeUsed();
            if (exitDirectionWhichCannotBeUsed != null) {
                if (exitDirectionWhichCannotBeUsed.length > 3)
                    return true;
            }
            return false;
        }

        private void fillExitDirectionWhichCannotBeUsed() {
            switch (directionExitLastPart) {
                case 1:
                    fillExitDirectionWhichCannotBeUsed(coordinateXLastPart, coordinateYLastPart - 1);
                    break;
                case 2:
                    fillExitDirectionWhichCannotBeUsed(coordinateXLastPart, coordinateYLastPart + 1);
                    break;
                case 3:
                    fillExitDirectionWhichCannotBeUsed(coordinateXLastPart - 1, coordinateYLastPart);
                    break;
                case 4:
                    fillExitDirectionWhichCannotBeUsed(coordinateXLastPart + 1, coordinateYLastPart);
                    break;
            }
        }

        /**
         * Получает координаты ячейки и проверяет все запрещенные направления
         *          *                              top 1
         *          *                              down 2
         *          *                              left 3
         *          *                              right 4
         * @param x
         * @param y
         */
        private void fillExitDirectionWhichCannotBeUsed(int x, int y) {
            boolean top = false;
            boolean down = false;
            boolean left = false;
            boolean right = false;


            if (x == 0) { //находимя слева
                if (y == 0) { //находимя в углу слева сверху
                    down = checkPart(0, 1);
                    left = checkPart(1, 0);
                } else if (y == allMap.length - 1) { //находимя в углу слева снизу

                } else { //находимя слева без углов

                }
            } else if (x == allMap[0].length - 1) { //находимя справа
                if (y == 0) { //находимя в углу справа сверху

                } else if (y == allMap.length - 1) { //находимя в углу справа снизу

                } else { //находимя справа без углов

                }
            } else {
                if (y == 0) { //находимя на верху без углов

                } else if (y == allMap.length - 1) { //находимя внизу без углов

                } else { //НАХОДИТЬСЯ НЕ НА ГРАНИЦЕ
                    top = checkPart(x, y - 1);
                    down = checkPart(x, y + 1);
                    left = checkPart(x - 1, y );
                    right = checkPart(x + 1, y);
                }
            }

            fillExitDirectionWhichCannotBeUsed(top, down, left, right);
        }

        private void fillExitDirectionWhichCannotBeUsed(boolean top, boolean down, boolean left, boolean right) {
            if (top || down || left || right) {

            } else {
                exitDirectionWhichCannotBeUsed = new byte[0];
            }
        }

        /**
         * Проверяет свободен ли кусок карты
         * @param x координата
         * @param y координата
         * @return true если свободен
         */
        private boolean checkPart(int x, int y) {
            return allMap[y][x] == null;
        }

        /**
         * Проверяет направление выхода с границами
         * @param x координата
         * @param y координата
         * @param direction направление
         *                              top 1
         *                              down 2
         *                              left 3
         *                              right 4
         * @return можно ли добавить
         */
        private boolean checkBorderOnExit(int x, int y, byte direction) {
            switch (direction) {
                case 1:
                    return y != 0;
                case 2:
                    return y != (allMap.length - 1);
                case 3:
                    return x != 0;
                case 4:
                    return x != (allMap[y].length - 1);
            }
            return false;
        }

        private MapPart[][] getHeightAndWidth(int size) {
            if (size <= 10)
                return tenField();
            else if (size <= 20)
                return twentyField();
            else if (size <= 30)
                return thirtyField();
            else if (size <= 40)
                return fortyField();
            else if (size <= 50)
                return fiftyField();
            else if (size <= 100)
                return oneHundredField();

            return null;
        }

        private MapPart[][] tenField() {
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

        private MapPart[][] twentyField() {
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

        private MapPart[][] thirtyField() {
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

        private MapPart[][] fortyField() {
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

        private MapPart[][] fiftyField() {
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

        private MapPart[][] oneHundredField() {
            return new MapPart[10][10];
        }

        private Cell[][] buildFullField() {
            int constanta = 16;
            Cell[][] fullMap = new Cell[allMap.length * constanta][allMap[0].length * constanta];

            for (int i = 0; i < allMap.length; i++) {
                for (int j = 0; j < allMap[i].length; j++) {
                    if (allMap[i][j] != null) {
                        Cell[][] partMap = allMap[i][j].getPart();
                        for (int k = 0; k < partMap.length; k++) {
                            for (int l = 0; l < partMap[k].length; l++) {
                                fullMap[i * constanta + k][j * constanta + l] = partMap[k][l];
                            }
                        }
                    } else {
                        for (int k = 0; k < 16; k++) {
                            for (int l = 0; l < 16; l++) {
                                fullMap[i * constanta + k][j * constanta + l] = new Cell(false);
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < fullMap.length; i++) {
                for (int j = 0; j < fullMap[i].length; j++) {
                    fullMap[i][j].setXY(j, i);
                }
            }

            return fullMap;
        }
    }
}
