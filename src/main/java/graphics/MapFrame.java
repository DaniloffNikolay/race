package graphics;

import engine.Game;
import graphics.panels.MapPanel;

import javax.swing.*;
import java.awt.*;

public class MapFrame extends JDialog {
    private Game game;

    private MapPanel mapPanel;

    public MapFrame(Game game) {
        this.game = game;


        int width = StartFrame.DEFAULT_WIDTH;
        int height = StartFrame.DEFAULT_HEIGHT;

        Toolkit kit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = kit.getScreenSize();

        setSize(width, height);
        setResizable(true);
        setLocation(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2);
        setLayout(new BorderLayout());

        mapPanel = new MapPanel(game.getField(), game, null);
        mapPanel.setPaintAllMap(true);
        add(mapPanel, BorderLayout.CENTER);
    }
}
