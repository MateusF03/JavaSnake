package me.mateus.javasnake.game;

import me.mateus.javasnake.components.RenderPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Game implements KeyListener {

    private final Snake snake;
    private RenderPanel renderPanel;
    private final int size;
    private final Map<Integer, Runnable> keyCodeEvent = new HashMap<>();
    private Apple apple;


    private final Random random = new Random();
    private final int EMPTY = 0;
    private final int SNAKE_BODY = 1;
    private final int SNAKE_HEAD = 2;
    private final int APPLE = 3;

    public Game(int size) {
        this.size = size;
        this.snake = new Snake();
        generateApple();
        //up
        keyCodeEvent.put(KeyEvent.VK_W, () -> {
            if (snake.getDirection() != Direction.DOWN) {
                snake.setDirection(Direction.UP);
            }
        });
        keyCodeEvent.put(KeyEvent.VK_UP, () -> {
            if (snake.getDirection() != Direction.DOWN) {
                snake.setDirection(Direction.UP);
            }
        });
        //down
        keyCodeEvent.put(KeyEvent.VK_S, () -> {
            if (snake.getDirection() != Direction.UP) {
                snake.setDirection(Direction.DOWN);
            }
        });
        keyCodeEvent.put(KeyEvent.VK_DOWN, () -> {
            if (snake.getDirection() != Direction.UP) {
                snake.setDirection(Direction.DOWN);
            }
        });
        //left
        keyCodeEvent.put(KeyEvent.VK_A, () -> {
            if (snake.getDirection() != Direction.RIGHT) {
                snake.setDirection(Direction.LEFT);
            }
        });
        keyCodeEvent.put(KeyEvent.VK_LEFT, () -> {
            if (snake.getDirection() != Direction.RIGHT) {
                snake.setDirection(Direction.LEFT);
            }
        });
        //right
        keyCodeEvent.put(KeyEvent.VK_D, () -> {
            if (snake.getDirection() != Direction.LEFT) {
                snake.setDirection(Direction.RIGHT);
            }
        });
        keyCodeEvent.put(KeyEvent.VK_RIGHT, () -> {
            if (snake.getDirection() != Direction.LEFT) {
                snake.setDirection(Direction.RIGHT);
            }
        });

    }

    public void setRenderPanel(RenderPanel renderPanel) {
        this.renderPanel = renderPanel;
    }

    public boolean play() {
        Location pos = nextPosition(snake.getHead(), snake.getDirection());
        if (snake.containsLocation(pos)) {
            return false;
        }
        Location appleLoc = apple.getLocation();
        if (pos.getX() == appleLoc.getX() && pos.getY() == appleLoc.getY()) {
            if (renderPanel != null) {
                renderPanel.incrementScore();
            }
            snake.extendBody(pos);
            generateApple();
        } else {
            snake.takeStep(pos);
        }
        return true;
    }

    private void generateApple() {
        Location appleLoc;
        do {
            int x = random.nextInt(size);
            int y = random.nextInt(size);
            appleLoc = new Location(x, y);
        } while (snake.containsLocation(appleLoc));
        apple = new Apple(appleLoc);
    }

    public int[][] boardMatrix() {
        int[][] matrix = new int[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                matrix[x][y] = EMPTY;
            }
        }
        for (Location location : snake.getBody()) {
            matrix[location.getX()][location.getY()] = SNAKE_BODY;
        }
        Location head = snake.getHead();
        matrix[head.getX()][head.getY()] = SNAKE_HEAD;
        Location appleLocation = apple.getLocation();
        matrix[appleLocation.getX()][appleLocation.getY()] = APPLE;
        return matrix;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode());
        int keyCode = e.getKeyCode();
        if (keyCodeEvent.containsKey(keyCode)) {
            keyCodeEvent.get(keyCode).run();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private Location nextPosition(Location position, Direction step) {
        int x = position.getX() + step.getX();
        int y = position.getY() + step.getY();
        if (y < 0) {
            y = size - 1;
        } else if (y >= size) {
            y = 0;
        }
        if (x < 0) {
            x = size - 1;
        } else if (x >= size) {
            x = 0;
        }
        return new Location(x, y);
    }
}
