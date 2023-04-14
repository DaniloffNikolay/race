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

    private Player winner = null;
    private boolean draw;

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

        int stepsPlayerOne = field.getPlayerOneFinishedInSteps();
        int stepsPlayerTwo = field.getPlayerTwoFinishedInSteps();

        if (stepsPlayerOne > stepsPlayerTwo)
            winner = playerOne;
        else if (stepsPlayerOne < stepsPlayerTwo)
            winner = playerTwo;
        else
            draw = true;

        //временный
        System.out.println("Победил игрок: " + getWinner().toString() + ", за " + totalTime/1000 + "секунд");
    }

    public Player getWinner() {
        if (draw)
            return new Player("drow");
        else
            return winner;
    }

    public void action(Step step) {
        if (field.isFieldEnd()) {
            endGame();
        }
        Player player = step.getPlayer();
        player.countIncrement();

        player.setSpeed(step.isBoost(), step.isBrake());
        if (playerOne == player) {
            player.setMap(field.getMapPlayerOne().getMapForPlayer());
            field.movePlayerOne(player, step.getDirection(), player.getSpeed());
        } else if (playerTwo == player) {
            player.setMap(field.getMapPlayerTwo().getMapForPlayer());
            field.movePlayerTwo(player, step.getDirection(), player.getSpeed());
        }
    }

    public Player nextPlayer() {
        System.out.println("был player = " + isNext + ", это написано в game method nextPlayer");
        if (playerOne == isNext) {
            isNext = playerTwo;
            return playerOne;
        }

        if (playerTwo == isNext) {
            isNext = playerOne;
            return playerTwo;
        }

        return null;
    }

    public Player whoIsNext() {
        return isNext;
    }

    public Field getField() {
        return field;
    }

    public boolean isDraw() {
        return draw;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }
}
