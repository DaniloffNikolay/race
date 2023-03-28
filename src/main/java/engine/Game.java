package engine;

public class Game {

    private Field field;
    private Player playerOne;
    private Player playerTwo;
    private Player isNext;

    private long timeStart;

    public Game(Field field) {
        this.field = field;
        playerOne = new Player(field.getCellStartPlayerOne(), "Player one");
        playerTwo = new Player(field.getCellStartPlayerTwo(), "Player two");
    }

    public void start() {
        timeStart = System.currentTimeMillis();
    }

    public void action(Step step) {
        System.out.println("Player = " + step.getPlayer());
        System.out.println("Direction = " + step.getDirection());
        System.out.println("Boost = " + step.isBoost());
        System.out.println("Brake = " + step.isBrake());
    }

    public Player whoIsNext() {
        if (playerOne == isNext) {
            isNext = playerTwo;
            return playerOne;
        }

        if (playerTwo == isNext) {
            isNext = playerOne;
            return playerTwo;
        }

        System.out.println("Error in whoIsNext method in Game class");
        return null;
    }

    public Field getField() {
        return field;
    }
}
