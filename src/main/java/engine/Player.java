package engine;

import engine.field.Cell;
import engine.field.parts.MapPart;

public class Player {
    private Cell playerCell;
    private Cell[][] map;
    private byte speed = 1;
    private String name;
    private Direction direction;
    private int countStep = 0;

    public Player(String name) {
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

    public void setSpeedAfterCrash() {
        this.speed = 1;
    }

    public byte getSpeed() {
        return speed;
    }

    /**
     * @param lastDirection направление  1 2 3
     *                                   4 * 5
     *                                   6 7 8
     */
    public void setAllPossibleDirections(byte lastDirection) {
        if (direction == null)
            direction = new Direction();
        setAllDirectionFalse();
        switch (lastDirection) {
            case 0 -> {
                direction.topLeft = true;
                direction.top = true;
                direction.topRight = true;
                direction.left = true;
                direction.right = true;
                direction.downLeft = true;
                direction.down = true;
                direction.downRight = true;
            }
            case 1 -> {
                direction.left = true;
                direction.topLeft = true;
                direction.top = true;
            }
            case 2 -> {
                direction.topLeft = true;
                direction.top = true;
                direction.topRight = true;
            }
            case 3 -> {
                direction.top = true;
                direction.topRight = true;
                direction.right = true;
            }
            case 4 -> {
                direction.topLeft = true;
                direction.left = true;
                direction.downLeft = true;
            }
            case 5 -> {
                direction.topRight = true;
                direction.right = true;
                direction.downRight = true;
            }
            case 6 -> {
                direction.left = true;
                direction.downLeft = true;
                direction.down = true;
            }
            case 7 -> {
                direction.downLeft = true;
                direction.down = true;
                direction.downRight = true;
            }
            case 8 -> {
                direction.right = true;
                direction.downRight = true;
                direction.down = true;
            }
        }
    }

    private void setAllDirectionFalse() {
        direction.topLeft = false;
        direction.top = false;
        direction.topRight = false;
        direction.left = false;
        direction.right = false;
        direction.downLeft = false;
        direction.down = false;
        direction.downRight = false;
    }

    public Direction getDirection() {
        return direction;
    }

    public class Direction {
        private boolean topLeft;
        private boolean top;
        private boolean topRight;
        private boolean left;
        private boolean right;
        private boolean downLeft;
        private boolean down;
        private boolean downRight;

        public boolean isTopLeft() {
            return topLeft;
        }

        public boolean isTop() {
            return top;
        }

        public boolean isTopRight() {
            return topRight;
        }

        public boolean isLeft() {
            return left;
        }

        public boolean isRight() {
            return right;
        }

        public boolean isDownLeft() {
            return downLeft;
        }

        public boolean isDown() {
            return down;
        }

        public boolean isDownRight() {
            return downRight;
        }
    }

    public Cell[][] getMap() {
        return map;
    }

    public void setMap(Cell[][] map) {
        this.map = map;
    }

    public Cell getPlayerCell() {
        return playerCell;
    }

    public void setPlayerCell(Cell playerCell) {
        this.playerCell = playerCell;
    }

    public int getCountStep() {
        return countStep;
    }

    public int countIncrement() {
        return countStep++;
    }

    @Override
    public String toString() {
        return name;
    }
}
