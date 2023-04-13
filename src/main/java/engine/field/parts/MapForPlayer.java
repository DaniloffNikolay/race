package engine.field.parts;

import engine.Player;
import engine.field.Cell;

import java.util.HashSet;
import java.util.Set;

public class MapForPlayer {
    private static final int SIZE = 25;
    private static final int MIDDLE = 13;

    private final Player player;
    private Cell[][] mapForPlayer;
    //private final MapPart[] map;
    private final Cell[][] fullMap;
    //private byte indexNextPart = 1;

    //private Set<Cell> allActivesCellsNextPart;

    /*public MapForPlayer(Player player, MapPart[] map) {
        this.player = player;
        this.fullMap = null;
        this.map = map;
        setMapForPlayer(map[indexNextPart - 1], map[indexNextPart]);
    }*/

    public MapForPlayer(Player player, Cell[][] fullMap) {
        this.player = player;
        this.fullMap = fullMap;

        reloadMapForPlayer();
    }

    private void reloadMapForPlayer() {
        Cell playerCell = player.getPlayerCell();
        mapForPlayer = calculateMapBorders(playerCell.getX(), playerCell.getY());
    }

    private Cell[][] calculateMapBorders(int x, int y) {
        Cell[][] playerMap = new Cell[SIZE][SIZE];

        int sizeY = fullMap.length - 1;
        int sizeX = fullMap[0].length - 1;

        if (x < MIDDLE || y < MIDDLE || x > (sizeX - MIDDLE) || y > (sizeY - MIDDLE)) { // поля карты игрока выходят за границы карты
            for (int i = y - MIDDLE, k = 0; i < y + MIDDLE - 1; i++, k++) {
                for (int j = x - MIDDLE, l = 0; j < x + MIDDLE - 1; j++, l++) {
                    if (i < 0 || j < 0 || i >= sizeY || j >= sizeX) {
                        playerMap[k][l] = new Cell(false);
                    } else {
                        playerMap[k][l] = fullMap[i][j];
                    }
                }
            }
        } else {
            for (int i = y - MIDDLE, k = 0; i < y + MIDDLE - 1; i++, k++) {
                    for (int j = x - MIDDLE, l = 0; j < x + MIDDLE - 1; j++, l++) {
                        playerMap[k][l] = fullMap[i - MIDDLE][j - MIDDLE];
                }
            }
        }

        return playerMap;
    }

    public Cell[][] getMapForPlayer() {
        reloadMapForPlayer();
        return mapForPlayer;
    }

    public Cell getCellDirection(Cell cellStart, byte direction) {
        int x = cellStart.getX();
        int y = cellStart.getY();

        switch (direction) {
            case 1 -> { //top left
                x = x - 1;
                y = y - 1;
            }
            case 2 -> { //top
                y = y - 1;
            }
            case 3 -> { //top right
                x = x + 1;
                y = y - 1;
            }
            case 4 -> { //left
                x = x - 1;
            }
            case 5 -> { //right
                x = x + 1;
            }
            case 6 -> { //down left
                x = x - 1;
                y = y + 1;
            }
            case 7 -> { //down
                y = y + 1;
            }
            case 8 -> { //down right
                x = x + 1;
                y = y + 1;
            }
        }

        return fullMap[y][x];
    }

    /*public Cell[][] getMapForPlayer() {
        if (indexNextPart > 8) {
            setMapForPlayer(map[indexNextPart - 1], map[indexNextPart]);
            return mapForPlayer;
        }
        if (allActivesCellsNextPart.contains(player.getPlayerCell())) {
            setMapForPlayer(map[indexNextPart], map[indexNextPart + 1]);
            indexNextPart++;
        } else {
            setMapForPlayer(map[indexNextPart - 1], map[indexNextPart]);
        }

        return mapForPlayer;
    }*/

    /*private void setMapForPlayer(MapPart firstPart, MapPart nextPart) {
        Cell[][] cellsFirstPart = firstPart.getPart();
        Cell[][] cellsNextPart = nextPart.getPart();
        allActivesCellsNextPart = new HashSet<>();

        switch (firstPart.getDirectionExit()) {
            case 1 -> {                             //тогда первый снизу второй сверху
                mapForPlayer = new Cell[32][16];

                for (byte i = 0; i < cellsNextPart.length; i++) {
                    for (byte y = 0; y < cellsNextPart[i].length; y++) {
                        mapForPlayer[i][y] = cellsNextPart[i][y];
                        mapForPlayer[i][y].setXY(y, i);

                        if (mapForPlayer[i][y].isActive()) {
                            allActivesCellsNextPart.add(mapForPlayer[i][y]);
                        }
                    }
                }

                for (byte i = 0; i < cellsFirstPart.length; i++) {
                    for (byte y = 0; y < cellsFirstPart[i].length; y++) {
                        mapForPlayer[i + 16][y] = cellsFirstPart[i][y];
                        mapForPlayer[i + 16][y].setXY(y, (byte) (i + 16));
                    }
                }
            }
            case 2 -> {                             //тогда первый сверху второй снизу
                mapForPlayer = new Cell[32][16];

                for (byte i = 0; i < cellsFirstPart.length; i++) {
                    for (byte y = 0; y < cellsFirstPart[i].length; y++) {
                        mapForPlayer[i][y] = cellsFirstPart[i][y];
                        mapForPlayer[i][y].setXY(y, i);
                    }
                }

                for (byte i = 0; i < cellsNextPart.length; i++) {
                    for (byte y = 0; y < cellsNextPart[i].length; y++) {
                        mapForPlayer[i + 16][y] = cellsNextPart[i][y];

                        mapForPlayer[i + 16][y].setXY(y, (byte) (i + 16));
                        if (mapForPlayer[i + 16][y].isActive()) {
                            allActivesCellsNextPart.add(mapForPlayer[i + 16][y]);
                        }
                    }
                }
            }
            case 3 -> {                             //тогда первый справа второй слева
                mapForPlayer = new Cell[16][32];

                for (byte i = 0; i < cellsNextPart.length; i++) {
                    for (byte y = 0; y < cellsNextPart[i].length; y++) {
                        mapForPlayer[i][y] = cellsNextPart[i][y];
                        mapForPlayer[i][y].setXY(y, i);

                        if (mapForPlayer[i][y].isActive()) {
                            allActivesCellsNextPart.add(mapForPlayer[i][y]);
                        }
                    }
                }

                for (byte i = 0; i < cellsFirstPart.length; i++) {
                    for (byte y = 0; y < cellsFirstPart[i].length; y++) {
                        mapForPlayer[i][y + 16] = cellsFirstPart[i][y];
                        mapForPlayer[i][y + 16].setXY((byte) (y + 16), i);
                    }
                }
            }
            case 4 -> {                             //тогда первый слева второй справа
                mapForPlayer = new Cell[16][32];

                for (byte i = 0; i < cellsFirstPart.length; i++) {
                    for (byte y = 0; y < cellsFirstPart[i].length; y++) {
                        mapForPlayer[i][y] = cellsFirstPart[i][y];
                        mapForPlayer[i][y].setXY(y, i);
                    }
                }

                for (byte i = 0; i < cellsNextPart.length; i++) {
                    for (byte y = 0; y < cellsNextPart[i].length; y++) {
                        Cell cell = cellsNextPart[i][y];
                        mapForPlayer[i][y + 16] = cell;
                        cell.setXY((byte) (y + 16), i);
                        if (cell.isActive()) {
                            allActivesCellsNextPart.add(cell);
                        }
                    }
                }
            }
        }
    }*/
}
