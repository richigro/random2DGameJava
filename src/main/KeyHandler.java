package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            // move the character up
            upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            // move the character up
            downPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            // move the character up
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            // move the character up
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            // move the character up
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            // move the character up
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            // move the character up
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            // move the character up
            rightPressed = false;
        }
    }
}
