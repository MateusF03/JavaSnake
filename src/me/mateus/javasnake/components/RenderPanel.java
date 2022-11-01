package me.mateus.javasnake.components;

import me.mateus.javasnake.game.Game;

import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {

    private final Game game;
    private int score = 0;

    public RenderPanel(Game game, int size) {
        this.game = game;
        setPreferredSize(new Dimension(size * 10, size * 10));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[][] board = game.boardMatrix();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(), getHeight());
        int currentValue = Color.BLACK.getRGB();
        for (int x = 0; x < getWidth() / 10; x++) {
            for (int y = 0; y < getHeight() / 10; y++) {
                int value = board[x][y];
                Color color = Color.BLACK;
                switch (value) {
                    case 1:
                        color = new Color(0x00ff00);
                        break;
                    case 2:
                        color = new Color(0x378037);
                        break;
                    case 3:
                        color = Color.RED;
                        break;
                    /*default:
                    case 0:
                        color = Color.BLACK;
                        break;*/
                }
                if (currentValue != color.getRGB()) {
                    g.setColor(color);
                    currentValue = color.getRGB();
                }
                g.fillRect(x * 10, y * 10, 10, 10);
            }
        }
        g.setColor(Color.WHITE);
        g.setFont(Font.getFont(Font.MONOSPACED));
        g.drawString("Score: " + score, 10, 10);
    }

    public void incrementScore() {
        score++;
    }

    public int getScore() {
        return score;
    }
}
