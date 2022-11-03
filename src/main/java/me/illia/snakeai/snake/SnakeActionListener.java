package me.illia.snakeai.snake;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static me.illia.snakeai.snake.SnakeConsts.isSnakeRunning;

public class SnakeActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Snake snake = new Snake();
        while (isSnakeRunning) {
            snake.moveSnake();
            snake.checkCollisionForApples();
            snake.checkCollision();
        }
        snake.repaint();
    }
}
