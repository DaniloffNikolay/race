package graphics;

import engine.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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

        JButton goGame = new JButton("пуск");
        goGame.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(game);
            game.start();
            gameFrame.setVisible(true);
        });

        JButton instruction = new JButton("инструкция");
        instruction.addActionListener(e -> {
            InstructionFrame instructionFrame = new InstructionFrame();
            instructionFrame.setVisible(true);

        });

        JButton hrefButton = new JButton("Ссылка на проект");
        hrefButton.addActionListener(a -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/DaniloffNikolay/race/tree/main"));
            } catch (IOException e) {
                System.out.println("Ошибка IOException");
            } catch (URISyntaxException e) {
                System.out.println("Ошибка URISyntaxException");
            }
        });

        JButton printAllMap = new JButton("Показать всю карту");
        printAllMap.addActionListener(a -> {
            MapFrame gameFrame = new MapFrame(game);
            gameFrame.setVisible(true);
        });

        centerPanel.add(goGame);
        centerPanel.add(instruction);
        centerPanel.add(hrefButton);
        centerPanel.add(printAllMap);
        add(centerPanel, BorderLayout.CENTER);
    }
}
