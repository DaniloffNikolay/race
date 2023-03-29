package graphics;

import engine.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame {

    public static final int DEFAULT_WIDTH = 601;
    public static final int DEFAULT_HEIGHT = 761;

    public StartFrame(Game game) {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setResizable(false);
        Toolkit kit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = kit.getScreenSize();
        setLocation(screenSize.width / 2 - DEFAULT_WIDTH / 2, screenSize.height / 2 - DEFAULT_HEIGHT / 2);

        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        JButton newGame = new JButton("пуск");
        newGame.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(game);
            game.start();
            gameFrame.setVisible(true);

        });

        centerPanel.add(newGame);
        add(centerPanel, BorderLayout.CENTER);
    }

}
