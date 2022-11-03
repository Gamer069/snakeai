package me.illia.snakeai.snake;

import java.awt.event.*;

import static me.illia.snakeai.snake.SnakeConsts.dir;

public class SnakeKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a' -> {
                if (dir != "Right") dir = "Left";
                break;
            }
            case 'd' -> {
                if (dir != "Left") dir = "Right";
                break;
            }
            case 'w' -> {
                if (dir != "Down") dir = "Up";
            }
            case 's' -> {
                if (dir != "Up") dir = "Down";
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
