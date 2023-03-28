package engine;

public class Player {
    private Cell playerCell;
    private String name;

    public Player(Cell playerCell, String name) {
        this.playerCell = playerCell;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
