package engine.field;

import engine.Player;
import engine.field.parts.MapForPlayer;
import engine.field.parts.MapPart;

public class Field {
    private MapForPlayer mapPlayerOne;
    private MapForPlayer mapPlayerTwo;
    private final Cell[][] fullMap;
    private final Cell cellStartPlayerOne;
    private final Cell cellStartPlayerTwo;

    private Player playerOne;
    private Player playerTwo;

    private final Cell[] finishСells;
    private byte startDirection;

    private int playerOneFinishedInSteps = 0;
    private int playerTwoFinishedInSteps = 0;

    private Field(MapPart[][] allMap) {
        int constanta = 16;
        fullMap = new Cell[allMap.length * constanta][allMap[0].length * constanta];

        Cell tempCellStartPlayerOne = null;
        Cell tempCellStartPlayerTwo = null;
        Cell[] tempFinishСells = null;

        for (int i = 0; i < allMap.length; i++) {
            for (int j = 0; j < allMap[i].length; j++) {

                MapPart part = allMap[i][j];

                if (part != null) {
                    if (part.isStartingPart()) {
                        tempCellStartPlayerOne = part.getCellStartPlayerOne();
                        tempCellStartPlayerTwo = part.getCellStartPlayerTwo();

                        tempCellStartPlayerOne.setPlayerOneHere(true);
                        tempCellStartPlayerTwo.setPlayerTwoHere(true);

                        if (part.getDirectionEnter() == 1) //начало сверху
                            startDirection = 7;
                        else if (part.getDirectionEnter() == 2) //начало снизу
                            startDirection = 2;
                        else if (part.getDirectionEnter() == 3) //начало слева
                            startDirection = 5;
                        else if (part.getDirectionEnter() == 4) //начало справа
                            startDirection = 4;
                        else
                            startDirection = 0;

                    } else if (part.isFinalPart()) {
                        tempFinishСells = part.getFinishСells();
                    }

                    Cell[][] partMap = part.getPart();
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

        cellStartPlayerOne = tempCellStartPlayerOne;
        cellStartPlayerTwo = tempCellStartPlayerTwo;
        finishСells = tempFinishСells;

        for (int i = 0; i < fullMap.length; i++) {
            for (int j = 0; j < fullMap[i].length; j++) {
                fullMap[i][j].setXY(j, i);
            }
        }
    }


    public boolean movePlayerOne(Player player, byte direction, byte speed) {
        if (checkFinish(player.getPlayerCell())) {
            if (playerOneFinishedInSteps == 0) {
                playerOneFinishedInSteps = player.getCountStep();
            }
            return false;
        }

        if (speed == 0) {
            player.setAllPossibleDirections(direction);
            return true;
        }

        Cell directionCell = getCellDirection(playerOne.getPlayerCell(), mapPlayerOne, direction);
        if (!directionCell.isActive()) {//врезался
            player.setSpeedAfterCrash();
            player.setAllPossibleDirections((byte) 0);
            return false;
        } else {
            playerOne.getPlayerCell().setPlayerOneHere(false);
            playerOne.setPlayerCell(directionCell);
            playerOne.getPlayerCell().setPlayerOneHere(true);
            speed--;
        }

        return movePlayerOne(player, direction, speed);
    }

    public boolean movePlayerTwo(Player player, byte direction, byte speed) {
//        System.out.println("ходит игрок: " + player + ", cell x = " +
//                player.getPlayerCell().getX() + ", y = " + player.getPlayerCell().getY() +
//                ", direction = " + direction + ", speed = " + speed);
        if (checkFinish(player.getPlayerCell())) {
            if (playerTwoFinishedInSteps == 0) {
                playerTwoFinishedInSteps = player.getCountStep();
            }
            return false;
        }

        if (speed == 0) {
            player.setAllPossibleDirections(direction);
            return true;
        }

        Cell directionCell = getCellDirection(playerTwo.getPlayerCell(), mapPlayerTwo, direction);
        if (!directionCell.isActive()) {//врезался
            player.setSpeedAfterCrash();
            player.setAllPossibleDirections((byte) 0);
            return false;
        } else {
            playerTwo.getPlayerCell().setPlayerTwoHere(false);
            playerTwo.setPlayerCell(directionCell);
            playerTwo.getPlayerCell().setPlayerTwoHere(true);
            speed--;
        }

        return movePlayerTwo(player, direction, speed);
    }

    /**
     * метод определяет ячейку направления
     * @param cellStart ячейка где стоит игрок
     * @param direction направление  1 2 3
                                     4 * 5
                                     6 7 8
     * @return ячейку направления
     */
    private Cell getCellDirection(Cell cellStart, MapForPlayer mapPlayer, byte direction) {
        return mapPlayer.getCellDirection(cellStart, direction);
    }

    public static Field getInstance(int size) {
        return getRandomField(size);
    }

    /**
     * генерирует случайное поле
     * @return сгенерируемое случайное поле
     */
    private static Field getRandomField(int size) {
        return new Field(MapGenerator.getAllMapPart(size));
//        return new Field(MapGenerator.getField(10));
//        return getTestField();
    }

    public byte getStartDirection() {
        return startDirection;
    }

    public void setPlayers(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        playerOne.setAllPossibleDirections(getStartDirection());
        playerTwo.setAllPossibleDirections(getStartDirection());

        playerOne.setPlayerCell(cellStartPlayerOne);
        playerTwo.setPlayerCell(cellStartPlayerTwo);

        mapPlayerOne = new MapForPlayer(playerOne, fullMap);
        mapPlayerTwo = new MapForPlayer(playerTwo, fullMap);
    }

    public MapForPlayer getMapPlayerOne() {
        return mapPlayerOne;
    }

    public MapForPlayer getMapPlayerTwo() {
        return mapPlayerTwo;
    }

    private boolean checkFinish(Cell checkedCell) {
        for (Cell cell : finishСells) {
            if (checkedCell == cell)
                return true;
        }

        return false;
    }

    public boolean isFieldEnd() {
        if (playerOneFinishedInSteps > 0 && playerTwoFinishedInSteps > 0)
            return true;
        return false;
    }

    public int getPlayerOneFinishedInSteps() {
        return playerOneFinishedInSteps;
    }

    public int getPlayerTwoFinishedInSteps() {
        return playerTwoFinishedInSteps;
    }

    public Cell[][] getFullMap() {
        return fullMap;
    }
}
