package me.mateus.javasnake.game;

public enum Direction {

    DOWN(0, 1), UP(0, -1), LEFT(-1, 0), RIGHT(1, 0);

    private final int x;
    private final int y;
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
