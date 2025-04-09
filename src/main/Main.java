package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        // Close the window when user click on close button
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Don't allow Resizing of Window
        window.setResizable(false);
        // Window title, will be the Game's title
        window.setTitle("A Random 2D Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack(); // Causes window to be sized to fit the preferred size

        // Show the window at the center of the screen
        window.setLocationRelativeTo(null);
        // Display the window
        window.setVisible(true);

        // start the game loop
        gamePanel.startGameThread();
    }
}
