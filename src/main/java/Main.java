import engine.Player;
import engine.field.Cell;
import engine.field.Field;
import engine.Game;
import engine.field.MapGenerator;
import graphics.StartFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

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

    public static void fullTest() {
        Main main = new Main();
        Player playerOne = new Player("Player one");
        Player playerTwo = new Player("Player two");
        Game game = new Game(Field.getInstance(), playerOne, playerTwo);
        main.startApplication(game);
    }

    public static void main(String[] args) throws InterruptedException {
        //fullTest();
        mapGeneratorTest();
    }

    public static void mapGeneratorTest() {
        Cell[][] map = MapGenerator.getFullField(10);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < map[0].length; i++) {
            for (int j = 0; j < map.length; j++) {
                stringBuilder.append(" " + map[j][i]);
            }
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder);
    }
}