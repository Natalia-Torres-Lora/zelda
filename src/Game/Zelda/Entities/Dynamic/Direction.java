package Game.Zelda.Entities.Dynamic;

/**
 * Created by AlexVR on 3/15/2020
 */
public enum Direction {
    LEFT("Left"),
    RIGHT("Right"),
    UP("Up"),
    DOWN("Down");

    private String direction;

    Direction(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return this.direction;
    }

    public static Direction getDirection(String direction) {
        return Direction.valueOf(direction.toUpperCase());
    }
}
