package me.mateus.javasnake.game;


import java.util.ArrayList;
import java.util.List;

public class Snake {

    private final List<Location> body = new ArrayList<>();
    private Direction direction;
    public Snake() {
        body.add(new Location(0, 0));
        body.add(new Location(1, 0));
        body.add(new Location(2, 0));
        body.add(new Location(3, 0));
        body.add(new Location(4, 0));
        direction = Direction.DOWN;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public Location getHead() {
        return body.get(body.size() - 1);
    }

    public List<Location> getBody() {
        return body;
    }

    public void takeStep(Location newPos) {
        body.remove(0);
        body.add(newPos);
    }

    public void extendBody(Location newPos) {
        body.add(newPos);
    }

    public boolean containsLocation(Location loc) {
        for (Location location : body) {
            if (location.getX() == loc.getX() && location.getY() == loc.getY())
                return true;
        }
        return false;
    }
}
