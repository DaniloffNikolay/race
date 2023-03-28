package engine;

public class Step {

    private Player player;
    /**
     * 2 3 4
     * 1 * 5
     * 6 7 8
     */
    private byte direction;
    private boolean isBoost;
    private boolean isBrake;

    public Step(Player player, byte direction, boolean isBoost, boolean isBrake) {
        this.player = player;
        this.direction = direction;
        this.isBoost = isBoost;
        this.isBrake = isBrake;
    }

    public Player getPlayer() {
        return player;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isBoost() {
        return isBoost;
    }

    public boolean isBrake() {
        return isBrake;
    }
}
