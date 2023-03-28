package graphics;

import engine.Field;
import engine.Game;
import graphics.panels.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JDialog {

    private Field field;

    private final Game game;

    private MapPanel mapPanel;
    private JComponent statusPanel;
    private JComponent controlPanel;



    public GameFrame(Game game) {

        this.game = game;
        field = game.getField();

        int width = StartFrame.DEFAULT_WIDTH + 15;
        int height = StartFrame.DEFAULT_HEIGHT;

        Toolkit kit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = kit.getScreenSize();
        //setUndecorated(true);
        setSize(width, height);
        setResizable(false);
        setLocation(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2);
        setLayout(new BorderLayout());

        mapPanel = new MapPanel(field, this);
        add(mapPanel, BorderLayout.CENTER);

        statusPanel = new JPanel();
        statusPanel.setSize(450, 50);
        statusPanel.setBackground(Color.RED);
        add(statusPanel, BorderLayout.NORTH);

        controlPanel = new JPanel();
        controlPanel.setBackground(Color.GREEN);
        add(controlPanel, BorderLayout.SOUTH);
    }


    public void update() {
        mapPanel.repaint();
    }

    public Game getGame() {
        return game;
    }

}
