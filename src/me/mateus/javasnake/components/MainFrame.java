package me.mateus.javasnake.components;

import me.mateus.javasnake.game.Game;

import javax.swing.*;

public class MainFrame extends JFrame  {

    private final int size;

    public MainFrame(int size) {
        setResizable(false);
        setTitle("Snake");
        this.size = size;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void initComponents() {
        Game game = new Game(size);
        RenderPanel renderPanel = new RenderPanel(game, size);
        game.setRenderPanel(renderPanel);
        addKeyListener(game);
        Thread gameThread = new Thread(() -> {
            long taskTime;
            long sleepTime = 1000/15L;
            boolean running = true;
            while (running) {
                taskTime = System.currentTimeMillis();
                renderPanel.repaint();

                if (!game.play()) {
                    JOptionPane.showMessageDialog(this, "GAME OVER!", "Final Score: " + renderPanel.getScore(), JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    running = false;
                }
                taskTime = System.currentTimeMillis() - taskTime;
                if (sleepTime - taskTime > 0) {
                    try {
                        Thread.sleep(sleepTime - taskTime);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        add(renderPanel);
        gameThread.start();
    }
}
