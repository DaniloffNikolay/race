package graphics;

import engine.field.Field;
import engine.Game;
import engine.Step;
import graphics.panels.MapPanel;
import graphics.panels.ControlPanel;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JDialog {

    private Field field;

    private final Game game;

    private MapPanel mapPanel;
    private JComponent statusPanel;
    private JPanel controlPanel;



    public GameFrame(Game game) {
        this.game = game;
        field = game.getField();

        int width = 391;
        int height = 537;

        Toolkit kit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = kit.getScreenSize();
        //setUndecorated(true);
        setSize(width, height);
        setResizable(true);
        setLocation(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2);
        setLayout(new BorderLayout());

        mapPanel = new MapPanel(field, game, this);
        add(mapPanel, BorderLayout.CENTER);

        statusPanel = new JPanel();
        statusPanel.setSize(450, 50);
        statusPanel.setBackground(Color.RED);
        add(statusPanel, BorderLayout.NORTH);


        controlPanel = new ControlPanel(this, game);
//        controlPanel.setBackground(Color.GREEN);
        add(controlPanel, BorderLayout.SOUTH);
        mapPanel.setPaintAllMap(false);
    }

    public void action(Step step) {
        game.action(step);
        mapPanel.repaint();
    }

    public void update() {
        mapPanel.repaint();
    }

    public Game getGame() {
        return game;
    }

}
