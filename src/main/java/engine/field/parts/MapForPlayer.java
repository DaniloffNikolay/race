package engine.field.parts;

import engine.Player;
import engine.field.Cell;

import java.util.HashSet;
import java.util.Set;

public class MapForPlayer {
    private final Player player;
    private Cell[][] mapForPlayer;
    private final MapPart[] map;
    private byte indexNextPart = 0;

    private Set<Cell> allActivesCellsNextPart;

    public MapForPlayer(Player player, MapPart[] map) {
        this.player = player;
        this.map = map;
        setMapForPlayer(map[indexNextPart], map[indexNextPart + 1]);
        indexNextPart++;
    }

    public Cell[][] getMapForPlayer() {
        if (allActivesCellsNextPart.contains(player.getPlayerCell())) {
            setMapForPlayer(map[indexNextPart], map[indexNextPart + 1]);
            indexNextPart++;
        }

        return mapForPlayer;
    }

    private void setMapForPlayer(MapPart firstPart, MapPart nextPart) {
        Cell[][] cellsFirstPart = firstPart.getPart();
        Cell[][] cellsNextPart = nextPart.getPart();
        allActivesCellsNextPart = new HashSet<>();

        switch (firstPart.getDirectionExit()) {
            case 1 -> {                             //тогда первый снизу второй сверху
                mapForPlayer = new Cell[32][16];

                for (int i = 0; i < cellsNextPart.length; i++) {
                    for (int y = 0; y < cellsNextPart[i].length; y++) {
                        mapForPlayer[i][y] = cellsNextPart[i][y];

                        if (mapForPlayer[i][y].isActive()) {
                            allActivesCellsNextPart.add(mapForPlayer[i][y]);
                        }
                    }
                }

                for (int i = 0; i < cellsFirstPart.length; i++) {
                    for (int y = 0; y < cellsFirstPart[i].length; y++) {
                        mapForPlayer[i + 16][y] = cellsFirstPart[i][y];
                    }
                }
            }
            case 2 -> {                             //тогда первый сверху второй снизу
                mapForPlayer = new Cell[32][16];

                for (int i = 0; i < cellsFirstPart.length; i++) {
                    for (int y = 0; y < cellsFirstPart[i].length; y++) {
                        mapForPlayer[i][y] = cellsFirstPart[i][y];
                    }
                }

                for (int i = 0; i < cellsNextPart.length; i++) {
                    for (int y = 0; y < cellsNextPart[i].length; y++) {
                        mapForPlayer[i + 16][y] = cellsNextPart[i][y];

                        if (mapForPlayer[i + 16][y].isActive()) {
                            allActivesCellsNextPart.add(mapForPlayer[i + 16][y]);
                        }
                    }
                }
            }
            case 3 -> {                             //тогда первый справа второй слева
                mapForPlayer = new Cell[16][32];

                for (int i = 0; i < cellsNextPart.length; i++) {
                    for (int y = 0; y < cellsNextPart[i].length; y++) {
                        mapForPlayer[i][y] = cellsNextPart[i][y];

                        if (mapForPlayer[i][y].isActive()) {
                            allActivesCellsNextPart.add(mapForPlayer[i][y]);
                        }
                    }
                }

                for (int i = 0; i < cellsFirstPart.length; i++) {
                    for (int y = 0; y < cellsFirstPart[i].length; y++) {
                        mapForPlayer[i][y + 16] = cellsFirstPart[i][y];
                    }
                }
            }
            case 4 -> {                             //тогда первый слева второй справа
                mapForPlayer = new Cell[16][32];

                for (int i = 0; i < cellsFirstPart.length; i++) {
                    for (int y = 0; y < cellsFirstPart[i].length; y++) {
                        mapForPlayer[i][y] = cellsFirstPart[i][y];
                    }
                }

                for (int i = 0; i < cellsNextPart.length; i++) {
                    for (int y = 0; y < cellsNextPart[i].length; y++) {
                        mapForPlayer[i][y + 16] = cellsNextPart[i][y];

                        if (mapForPlayer[i][y + 16].isActive()) {
                            allActivesCellsNextPart.add(mapForPlayer[i][y + 16]);
                        }
                    }
                }
            }
        }
    }
}
