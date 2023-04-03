package engine;

import engine.field.Field;

import java.util.Random;

public class Game {

    private Field field;
    private Player playerOne;
    private Player playerTwo;
    private Player isNext;

    private long timeStart;
    private long totalTime;

    public Game(Field field, Player playerOne, Player playerTwo) {
        this.field = field;

        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        field.setPlayers(playerOne, playerTwo);

        playerOne.setMap(field.getMapPlayerOne().getMapForPlayer());
        playerTwo.setMap(field.getMapPlayerTwo().getMapForPlayer());

        Random random = new Random();
        switch (random.nextInt(2) + 1) {
            case 1 -> isNext = playerOne;
            case 2 -> isNext = playerTwo;
        }
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
            player.setMap(field.getMapPlayerOne().getMapForPlayer());
        } else if (playerTwo == player) {
            field.movePlayerTwo(player, step.getDirection(), player.getSpeed());
            player.setMap(field.getMapPlayerTwo().getMapForPlayer());
        }
    }

    public Player nextPlayer() {
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

    public Player whoIsNext() {
        return isNext;
    }

    public Field getField() {
        return field;
    }
}
