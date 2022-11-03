package me.illia.snakeai.snake;

public class SnakeLauncher implements Launcher {
    public static final SnakeLauncher INSTANCE = new SnakeLauncher();
    public static void main(String[] args) {
        INSTANCE.launch(900, 900);
    }
    @Override
    public void launch(int windowWidth, int windowHeight) {
        new Snake(windowWidth, windowHeight);
    }
}
