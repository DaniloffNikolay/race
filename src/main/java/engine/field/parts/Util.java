package engine.field.parts;

import engine.field.Cell;

public class Util {

    public static MapPart getMapPart(byte[][] initPart, String pathToBackground) {
        byte enterSize;
        byte exitSize;
        byte directionEnter;
        byte directionExit;
        Coordinates[] coordinatesEnter;
        Coordinates[] coordinatesExit;

        Cell[][] partMap;

        boolean isStartingPart;
        boolean isFinalPart;

        Cell cellStartPlayerOne;
        Cell cellStartPlayerTwo;
        Cell[] finishCells;

        partMap = new Cell[initPart[0].length][initPart.length];
        byte sizeEnter = 0;
        byte sizeExit = 0;
        byte sizeFinish = 0;

        for (byte i = 0; i < partMap.length; i++) {
            for (byte y = 0; y < partMap[i].length; y++) {
                if(initPart[i][y] != 0) {
                    if (initPart[i][y] == 4)
                        sizeFinish++;
                    if (initPart[i][y] == 5)
                        sizeEnter++;
                    if (initPart[i][y] == 6)
                        sizeExit++;
                }
            }
        }

        enterSize = sizeEnter;
        exitSize = sizeExit;
        isFinalPart = sizeFinish > 0;

        int countEnter = 0;
        coordinatesEnter = new Coordinates[sizeEnter];
        int countExit = 0;
        coordinatesExit = new Coordinates[sizeExit];
        int countFinish = 0;
        finishCells = new Cell[sizeFinish];

        Cell playerOne = null;
        Cell playerTwo = null;

        for (byte i = 0; i < partMap.length; i++) {
            for (byte y = 0; y < partMap[i].length; y++) {
                if(initPart[i][y] != 0) {
                    if (initPart[i][y] == 1) {
                        partMap[i][y] = new Cell(y, i, true);
                    } else if (initPart[i][y] == 2) { //2 это стартовая позиция первого игрока
                        partMap[i][y] = new Cell(y, i, true);
                        playerOne = partMap[i][y];
                    } else if (initPart[i][y] == 3) { //3 это стартовая позиция второго игрока
                        partMap[i][y] = new Cell(y, i, true);
                        playerTwo = partMap[i][y];
                    } else if (initPart[i][y] == 4) { //4 это поячейка финиша
                        partMap[i][y] = new Cell(y, i, true);
                        finishCells[countFinish] = partMap[i][y];
                        countFinish++;
                    } else if (initPart[i][y] == 5) { //5 это координата входа
                        partMap[i][y] = new Cell(y, i, true);
                        coordinatesEnter[countEnter] = new Coordinates(y, i);
                        countEnter++;
                    } else if (initPart[i][y] == 6) { //6 это координата выхода
                        partMap[i][y] = new Cell(y, i, true);
                        coordinatesExit[countExit] = new Coordinates(y, i);
                        countExit++;
                    }

                } else
                    partMap[i][y] = new Cell(y, i, false);
            }
        }

        byte direction = 0;
        if (coordinatesEnter[0].x() == 0)
            direction = 3;
        else if (coordinatesEnter[0].x() == 15)
            direction = 4;
        else if (coordinatesEnter[0].y() == 0)
            direction = 1;
        else if (coordinatesEnter[0].y() == 15)
            direction = 2;
        directionEnter = direction;

        direction = 0;
        if (coordinatesExit[0].x() == 0)
            direction = 3;
        else if (coordinatesExit[0].x() == 15)
            direction = 4;
        else if (coordinatesExit[0].y() == 0)
            direction = 1;
        else if (coordinatesExit[0].y() == 15)
            direction = 2;
        directionExit = direction;

        cellStartPlayerOne = playerOne;
        cellStartPlayerTwo = playerTwo;

        isStartingPart = cellStartPlayerOne != null && cellStartPlayerTwo != null;

        return new MapPart(enterSize,
                exitSize,
                directionEnter,
                directionExit,
                coordinatesEnter,
                coordinatesExit,
                partMap,
                pathToBackground,
                isStartingPart,
                isFinalPart,
                cellStartPlayerOne,
                cellStartPlayerTwo,
                finishCells);
    }

    /**
     * Возвращает какое направление должно быть в случае если предыдущая часть была с направлением выхода
     * top - 1;
     * down - 2;
     * left - 3;
     * right - 4;
     * @param directionExit направление выхода в предыдущей части
     * @return направление входа которое должно быть в новой части
     */
    public static byte getDirectionEnter(byte directionExit) {
        switch (directionExit) {
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 4;
            case 4:
                return 3;
        }
        return 0;
    }
}
