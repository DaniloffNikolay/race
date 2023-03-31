package engine.field.parts;

import engine.field.Cell;

public class MapPart {
    /**
     * минимум 3 максимум 10
     */
    private byte enterSize;
    /**
     * минимум 3 максимум 10
     */
    private byte exitSize;
    /**
     * top 1
     * down 2
     * left 3
     * right 4
     */
    private byte directionEnter;
    /**
     * top 1
     * down 2
     * left 3
     * right 4
     */
    private byte directionExit;

    private Coordinates[] coordinatesEnter;
    private Coordinates[] coordinatesExit;

    private Cell[][] part;

    private String pathToBackground; //путь к файлу картинки фона

    private boolean isStartingPart;
    private boolean isFinalPart;

    private Cell cellStartPlayerOne;
    private Cell cellStartPlayerTwo;
    private Cell[] finishСells;

    public MapPart(byte enterSize, 
                   byte exitSize, 
                   byte directionEnter, 
                   byte directionExit, 
                   Coordinates[] coordinatesEnter, 
                   Coordinates[] coordinatesExit, 
                   Cell[][] part, 
                   String pathToBackground, 
                   boolean isStartingPart, 
                   boolean isFinalPart, 
                   Cell cellStartPlayerOne, 
                   Cell cellStartPlayerTwo, 
                   Cell[] finishСells) {
        this.enterSize = enterSize;
        this.exitSize = exitSize;
        this.directionEnter = directionEnter;
        this.directionExit = directionExit;
        this.coordinatesEnter = coordinatesEnter;
        this.coordinatesExit = coordinatesExit;
        this.part = part;
        this.pathToBackground = pathToBackground;
        this.isStartingPart = isStartingPart;
        this.isFinalPart = isFinalPart;
        this.cellStartPlayerOne = cellStartPlayerOne;
        this.cellStartPlayerTwo = cellStartPlayerTwo;
        this.finishСells = finishСells;
    }

    public byte getEnterSize() {
        return enterSize;
    }

    public byte getExitSize() {
        return exitSize;
    }

    public byte getDirectionEnter() {
        return directionEnter;
    }

    public byte getDirectionExit() {
        return directionExit;
    }

    public Coordinates[] getCoordinatesEnter() {
        return coordinatesEnter;
    }

    public Coordinates[] getCoordinatesExit() {
        return coordinatesExit;
    }

    public Cell[][] getPart() {
        return part;
    }

    public String getPathToBackground() {
        return pathToBackground;
    }

    public boolean isStartingPart() {
        return isStartingPart;
    }

    public boolean isFinalPart() {
        return isFinalPart;
    }

    public Cell getCellStartPlayerOne() {
        return cellStartPlayerOne;
    }

    public Cell getCellStartPlayerTwo() {
        return cellStartPlayerTwo;
    }

    public Cell[] getFinishСells() {
        return finishСells;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < part.length; i++) {
            for (int y = 0; y < part[i].length; y++) {
                if (part[i][y].isActive())
                    stringBuilder.append("1 ");
                else
                    stringBuilder.append("0 ");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("x = " + getCoordinatesEnter()[0].x());
        stringBuilder.append(", ");
        stringBuilder.append("y = " + getCoordinatesEnter()[0].y() + "\n");

        stringBuilder.append("direction enter = " + getDirectionEnter() + ", direction exit = " + getDirectionExit() + "\n");

        return stringBuilder.toString();
    }
}
