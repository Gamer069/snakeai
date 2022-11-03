package me.illia.snakeai.snake;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static me.illia.snakeai.snake.SnakeConsts.*;

public class Snake extends JPanel {
    public static final Font bold = new Font("Bold", Font.BOLD, 45);
    public static int units;
    public static int[] XPositions;
    public static int[] YPositions;
    protected static Timer timer;
    public Snake(int windowWidth, int windowHeight) {
        SnakeConsts.windowWidth = windowWidth;
        SnakeConsts.windowHeight = windowHeight;
        units = (windowWidth * windowHeight) / 25;
        XPositions = new int[units];
        YPositions = new int[units];
        isSnakeRunning = true;
        timer = new Timer(delay, new SnakeActionListener());
        timer.start();
        createFrame(windowWidth, windowHeight);
        while (isSnakeRunning) moveSnake();
    }
    Snake() {
        
    }
    public void createFrame(int windowWidth, int windowHeight) {
        JFrame frame = new JFrame("Snake");
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(windowWidth, windowHeight);
        frame.setPreferredSize(new Dimension(windowWidth, windowHeight));
        frame.setBackground(Color.black);
        frame.setFocusable(true);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        this.addKeyListener(new SnakeKeyListener());
        this.setBackground(Color.black);
    }
    public Random getRandom() {
        return new Random();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (isSnakeRunning) {
            for (int i = 0; i < windowHeight / 25; i++) {
                graphics.drawLine(i * 25, 0, i * 25, windowHeight);
            }
            graphics.setColor(Color.green);
            drawApples(graphics);
            spawnApple();
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    graphics.setColor(Color.darkGray);
                    graphics.fillRect(XPositions[0], YPositions[0], 25, 25);
                } else {
                    graphics.setColor(Color.lightGray);
                    graphics.fillRect(XPositions[i], YPositions[i], 25, 25);
                }
            }
            graphics.setColor(Color.white);
            graphics.setFont(bold);
            FontMetrics fontMetrics = getFontMetrics(bold);
            graphics.drawString("" + score, (windowWidth - fontMetrics.stringWidth("" + score)) / 2, bold.getSize());
        } else {
            fail(graphics);
        }
    }
    public void fail(Graphics graphics) {
        graphics.setColor(new Color(126,22,20));
        graphics.setFont(bold);
        FontMetrics fontMetrics = getFontMetrics(bold);
        graphics.drawString("You DIED!", (windowWidth - fontMetrics.stringWidth("You DIED!")) / 2, windowHeight / 2);
        graphics.drawString("" + score, (windowWidth - fontMetrics.stringWidth("" + score)) / 2, windowHeight / 3);
    }
    public void moveSnake() {
        for (int i = bodyParts; i > 0; i--) {
             XPositions[i] = XPositions[i--];
             YPositions[i] = YPositions[i--];
        }
        switch (dir) {
            case "Up" -> YPositions[0] =- 25;
            case "Down" -> YPositions[0] =+ 25;
            case "Right" -> XPositions[0] =+ 25;
            case "Left" -> XPositions[0] =- 25;
        }
    }

    public void checkCollision() {
        for (int i = bodyParts; i > 0; i--) {
            if (XPositions[0] == XPositions[i] && YPositions[0] == YPositions[i]) {
                isSnakeRunning = false;
            }
        }
        if (XPositions[0] < 0) isSnakeRunning = false;
        if (XPositions[0] > windowWidth) isSnakeRunning = false;

        if (YPositions[0] < 0) isSnakeRunning = false;
        if (YPositions[0] > windowHeight) isSnakeRunning = false;

        if (!isSnakeRunning) timer.stop();
    }
    public void spawnApple() {
        appleX = getRandom().nextInt((windowWidth / 25) * 25);
        appleY = getRandom().nextInt((windowHeight / 25) * 25);
    }
    public void checkCollisionForApples() {
        if (XPositions[0] == appleX && YPositions[0] == appleY) {
            bodyParts++;
            score++;
            spawnApple();
        }
    }
    public void drawApples(Graphics graphics) {
        graphics.fillOval(appleX, appleY, 25, 25);
    }
}
