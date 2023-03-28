package engine;

public class Player {
    private Cell playerCell;
    private byte speed = 1;
    private String name;

    public Player(Cell playerCell, String name) {
        this.playerCell = playerCell;
        this.name = name;
    }

    private void speedBoost() {
        if (speed < 6)
            speed++;
    }

    private void speedBrake() {
        if (speed > 1)
            speed--;
    }

    public void setSpeed(boolean isBoost, boolean isBrake) {
        if (isBoost && isBrake)
            return;
        if (isBoost)
            speedBoost();
        if (isBrake)
            speedBrake();
    }

    public byte getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return name;
    }
}
