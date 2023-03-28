package graphics.panels;

import engine.Game;

import javax.swing.*;
import java.awt.*;

public class SouthPanel extends JPanel {

    private final Game game;

    public SouthPanel(Game game) {
        this.game =game;

        JButton topLeft = new JButton("top-left");
        JButton top = new JButton("top");
        JButton topRight = new JButton("top-right");

        JButton left = new JButton("left");
        JButton center = new JButton();
        center.setEnabled(false);
        JButton right = new JButton("right");

        JButton downLeft = new JButton("down-left");
        JButton down = new JButton("down");
        JButton downRight = new JButton("down-right");

        JButton run = new JButton("run");
        JButton brake = new JButton("BRAKE");
        JButton boost = new JButton("BOOST");



        GridLayout layout = new GridLayout(4, 3, 3, 3);
        setLayout(layout);

        add(topLeft);
        add(top);
        add(topRight);

        add(left);
        add(center);
        add(right);

        add(downLeft);
        add(down);
        add(downRight);

        add(run);
        add(brake);
        add(boost);
    }
}
