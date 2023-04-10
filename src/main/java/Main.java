import engine.Player;
import engine.field.Field;
import engine.Game;
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

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(2));
        }


        /*Main main = new Main();
        Player playerOne = new Player("Player one");
        Player playerTwo = new Player("Player two");
        Game game = new Game(Field.getInstance(), playerOne, playerTwo);
        main.startApplication(game);*/
    }
}