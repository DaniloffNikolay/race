package engine.field;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private byte x;
    private byte y;

    private final boolean active;
    private boolean isPlayerOneHere = false;
    private boolean isPlayerTwoHere = false;
    private boolean isPlayerOneWasHere = false;
    private boolean isPlayerTwoWasHere = false;

    public Cell(byte x, byte y, boolean active) {
        this.x = x;
        this.y = y;
        this.active = active;
    }

    public Cell(boolean active) {
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
        if(playerOneHere)  {
            isPlayerOneWasHere = true;
        }
    }

    public boolean isPlayerTwoHere() {
        return isPlayerTwoHere;
    }

    public void setPlayerTwoHere(boolean playerTwoHere) {
        isPlayerTwoHere = playerTwoHere;
        if(playerTwoHere)  {
            isPlayerTwoWasHere = true;
        }
    }

    public boolean isPlayerOneWasHere() {
        return isPlayerOneWasHere;
    }

    public boolean isPlayerTwoWasHere() {
        return isPlayerTwoWasHere;
    }

    public void setXY(byte x, byte y) {
        this.x = x;
        this.y = y;
    }

    public byte getX() {
        return x;
    }

    public byte getY() {
        return y;
    }

    @Override
    public String toString() {
        return "" + (isActive() ? 1 : 0);
    }
}
