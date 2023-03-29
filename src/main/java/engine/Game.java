package engine;

public class Game {

    private Field field;
    private Player playerOne;
    private Player playerTwo;
    private Player isNext;

    private long timeStart;
    private long totalTime;

    public Game(Field field) {
        this.field = field;
        playerOne = new Player(field.getCellStartPlayerOne(), "Player one");
        playerTwo = new Player(field.getCellStartPlayerTwo(), "Player two");
        playerOne.setAllPossibleDirections(field.getStartDirection());
        playerTwo.setAllPossibleDirections(field.getStartDirection());
        isNext = playerOne;
    }

    public void start() {
        timeStart = System.currentTimeMillis();
    }
    private void endGame() {
        long timeEnd = System.currentTimeMillis();
        totalTime = timeEnd - timeStart;
    }

    public void action(Step step) {
        Player player = step.getPlayer();
        player.setSpeed(step.isBoost(), step.isBrake());
        if (playerOne == player) {
            field.movePlayerOne(player, step.getDirection(), player.getSpeed());
        } else if (playerTwo == player) {
            field.movePlayerTwo(player, step.getDirection(), player.getSpeed());
        }
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
