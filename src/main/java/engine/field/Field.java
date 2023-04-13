package engine.field;

import engine.Player;
import engine.field.parts.MapForPlayer;
import engine.field.parts.MapPart;

public class Field {
    private MapForPlayer mapPlayerOne;
    private MapForPlayer mapPlayerTwo;
    private final MapPart[] map;
    private final Cell[][] fullMap;
    private final Cell cellStartPlayerOne;
    private final Cell cellStartPlayerTwo;

    private Player playerOne;
    private Player playerTwo;

    private final Cell[] finishСells;
    private byte startDirection;

    private int playerOneFinishedInSteps = 0;
    private int playerTwoFinishedInSteps = 0;


    private Field(MapPart[] map) {
        fullMap = null;
        this.map = map;
        cellStartPlayerOne = map[0].getCellStartPlayerOne();
        cellStartPlayerTwo = map[0].getCellStartPlayerTwo();
        cellStartPlayerOne.setPlayerOneHere(true);
        cellStartPlayerTwo.setPlayerTwoHere(true);
        finishСells = map[map.length - 1].getFinishСells();

        if (map[0].getDirectionEnter() == 1) //начало сверху
            startDirection = 7;
        else if (map[0].getDirectionEnter() == 2) //начало снизу
            startDirection = 2;
        else if (map[0].getDirectionEnter() == 3) //начало слева
            startDirection = 5;
        else if (map[0].getDirectionEnter() == 4) //начало справа
            startDirection = 4;
        else
            startDirection = 0;
    }

    private Field(MapPart[][] allMap) {
        map = null;

        int constanta = 16;
        fullMap = new Cell[allMap.length * constanta][allMap[0].length * constanta];

        Cell tempCellStartPlayerOne = null;
        Cell tempCellStartPlayerTwo = null;
        Cell[] tempFinishСells = null;

        for (int i = 0; i < allMap.length; i++) {
            for (int j = 0; j < allMap[i].length; j++) {
                if (allMap[i][j] != null) {
                    if (allMap[i][j].isStartingPart()) {
                        tempCellStartPlayerOne = allMap[i][j].getCellStartPlayerOne();
                        tempCellStartPlayerTwo = allMap[i][j].getCellStartPlayerTwo();
                    } else if (allMap[i][j].isFinalPart()) {
                        tempFinishСells = allMap[i][j].getFinishСells();
                    }

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

        cellStartPlayerOne = tempCellStartPlayerOne;
        cellStartPlayerTwo = tempCellStartPlayerTwo;
        finishСells = tempFinishСells;

        for (int i = 0; i < fullMap.length; i++) {
            for (int j = 0; j < fullMap[i].length; j++) {
                fullMap[i][j].setXY(j, i);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < fullMap[0].length; i++) {
            for (int j = 0; j < fullMap.length; j++) {
                if (fullMap[j][i].isActive())
                    stringBuilder.append(" 1");
                else
                    stringBuilder.append(" 0");
            }
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());
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

    public static Field getInstance() {
        return getRandomField();
    }

    /**
     * генерирует случайное поле
     * @return сгенерируемое случайное поле
     */
    private static Field getRandomField() {
        return new Field(MapGenerator.getAllMapPart(10));
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
}
