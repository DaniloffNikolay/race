

import engine.Field;
import engine.Game;
import graphics.StartFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public void startApplication(Game game) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                StartFrame frame = new StartFrame(game);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        Game game = new Game(Field.getField());
        Main start = new Main();
        start.startApplication(game);
    }
}