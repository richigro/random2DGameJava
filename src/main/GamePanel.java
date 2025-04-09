package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Screen settings
    final int originalTileSize = 16; // 16X16 tiles
    final int scale = 3; // Scale things by 3 to accommodate modern resolution monitors

    final int tileSize = originalTileSize * scale; // This will be the actual size of the tiles (48X48)
    final int maxScreenCol = 16;
    final int maxScreenRow = 12; // This will give us an aspect ratio of 4/3
    final int screenWidth = tileSize * maxScreenCol; // 48 X 16 = 768px
    final int screenHeight = tileSize * maxScreenRow; // 48 X 12 = 576px

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // Set player size
    final int playerWidth = tileSize;
    final int playerHeight = tileSize;
    // Set player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

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
        double drawInterval = (double) 1000000000 /FPS; // 0.01666 seconds to be able to draw 60 frame in a second
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
        if(keyH.upPressed) {
            playerY -= playerSpeed;
        } else if(keyH.downPressed) {
            playerY += playerSpeed;
        } else if( keyH.rightPressed) {
            playerX += playerSpeed;
        } else if(keyH.leftPressed) {
            playerX -= playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // the Parent class Jpanel has method called paintComponent

        Graphics2D g2 = (Graphics2D)g; // Ready to draw 2d graphics

        g2.setColor(Color.white); // the color to paint with
        g2.fillRect(playerX, playerY, playerWidth, playerHeight);

        g2.dispose();
    }
}
