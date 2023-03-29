package engine;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final int x;
    private final int y;

    private final boolean active;
    private boolean isPlayerOneHere = false;
    private boolean isPlayerTwoHere = false;
    private boolean isPlayerOneWasHere = false;
    private boolean isPlayerTwoWasHere = false;

    public Cell(int x, int y, boolean active) {
        this.x = x;
        this.y = y;
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isPlayerOneHere() {
        return isPlayerOneHere;
    }

    public void setPlayerOneHere(boolean playerOneHere) {
        isPlayerOneHere = playerOneHere;
    }

    public boolean isPlayerTwoHere() {
        return isPlayerTwoHere;
    }

    public void setPlayerTwoHere(boolean playerTwoHere) {
        isPlayerTwoHere = playerTwoHere;
    }

    public boolean isPlayerOneWasHere() {
        return isPlayerOneWasHere;
    }

    public void setPlayerOneWasHere(boolean playerOneWasHere) {
        isPlayerOneWasHere = playerOneWasHere;
    }

    public boolean isPlayerTwoWasHere() {
        return isPlayerTwoWasHere;
    }

    public void setPlayerTwoWasHere(boolean playerTwoWasHere) {
        isPlayerTwoWasHere = playerTwoWasHere;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
