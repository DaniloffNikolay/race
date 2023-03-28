package engine;

public class Game {

    private Field field;
    private Player playerOne;
    private Player playerTwo;

    public Game(Field field) {
        this.field = field;
        playerOne = new Player(field.getCellStartPlayerOne());
        playerTwo = new Player(field.getCellStartPlayerTwo());
    }

    public void start() {

    }

    public Field getField() {
        return field;
    }
}
