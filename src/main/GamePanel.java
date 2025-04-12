package main;

import entity.Player;
import tile.Tile;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Screen settings
    final int originalTileSize = 16; // 16X16 tiles
    final int scale = 3; // Scale things by 3 to accommodate modern resolution monitors

    public final int tileSize = originalTileSize * scale; // This will be the actual size of the tiles (48X48)
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12; // This will give us an aspect ratio of 4/3
    public final int screenWidth = tileSize * maxScreenCol; // 48 X 16 = 768px
    public final int screenHeight = tileSize * maxScreenRow; // 48 X 12 = 576px

    // World map params
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyH);
    TileManager tileM = new TileManager(this);

    // FPS frame per second
    int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS; // 0.01666 seconds to be able to draw 60 frame in a second
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        // Game Loop created here
        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update(); // Update character position
                repaint(); // Repaint the character
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }


    public void paintComponent(Graphics g) {
        // IMPORTANT NOTE: Draw order matters, things will appear on top of each other. if you don't
        // follow a specific order
        super.paintComponent(g); // the Parent class Jpanel has method called paintComponent
        Graphics2D g2 = (Graphics2D)g; // Ready to draw 2d graphics

        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
