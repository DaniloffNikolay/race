package graphics.panels;

import engine.Game;
import engine.Player;
import engine.Step;
import graphics.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

    private GameFrame gameFrame;
    private final Game game;

    private Player player;
    private byte direction;
    private boolean isBoost;
    private boolean isBrake;
    private JButton buttonDirectionPressed;

    private JButton brake;
    private JButton boost;
    private JButton buttonMovePressed;

    private static Color PASSIVE_COLOR = new Color(0xbcbcbc);
    private static Color ACTIVE_COLOR = new Color(0x379748);

    public ControlPanel(GameFrame gameFrame, Game game) {
        this.gameFrame = gameFrame;
        this.game =game;
        setBackground(new Color(0x544F4F));

        initialization();
    }

    private void initialization() {
        player = game.whoIsNext();
        Player.Direction playerDirection = player.getDirection();

        GridLayout layout = new GridLayout(4, 2, 3, 3);
        setLayout(layout);

        if (playerDirection.isTopLeft()) {
            JButton topLeft = new JButton("top-left");
            topLeft.addActionListener(getActionListenerForDirection((byte) 1, topLeft));
            add(topLeft);
        }

        if (playerDirection.isTop()) {
            JButton top = new JButton("top");
            top.addActionListener(getActionListenerForDirection((byte) 2, top));
            add(top);
        }

        if (playerDirection.isTopRight()) {
            JButton topRight = new JButton("top-right");
            topRight.addActionListener(getActionListenerForDirection((byte) 3, topRight));
            add(topRight);
        }

        if (playerDirection.isLeft()) {
            JButton left = new JButton("left");
            left.addActionListener(getActionListenerForDirection((byte) 4, left));
            add(left);
        }

        if (playerDirection.isRight()) {
            JButton right = new JButton("right");
            right.addActionListener(getActionListenerForDirection((byte) 5, right));
            add(right);
        }

        if (playerDirection.isDownLeft()) {
            JButton downLeft = new JButton("down-left");
            downLeft.addActionListener(getActionListenerForDirection((byte) 6, downLeft));
            add(downLeft);
        }

        if (playerDirection.isDown()) {
            JButton down = new JButton("down");
            down.addActionListener(getActionListenerForDirection((byte) 7, down));
            add(down);
        }

        if (playerDirection.isDownRight()) {
            JButton downRight = new JButton("down-right");
            downRight.addActionListener(getActionListenerForDirection((byte) 8, downRight));
            add(downRight);
        }

        JButton center = new JButton();
        center.setEnabled(false);
        center.setBackground(PASSIVE_COLOR);

        JButton run = new JButton("run");
        run.addActionListener(a -> {
            if (direction != 0) {
                Step step = new Step(player, direction, isBoost, isBrake);
                gameFrame.action(step);
                player = game.whoIsNext();
            } else {
                System.out.println("нужно выбрать направление");
            }
        });
        brake = new JButton("BRAKE");
        brake.addActionListener(getActionListenerForMove(brake, 1));
        boost = new JButton("BOOST");
        boost.addActionListener(getActionListenerForMove(boost, 2));

        //add(center);
        add(run);
        add(brake);
        add(boost);
    }

    private void setButtonDirectionPressed(JButton button) {
        if (buttonDirectionPressed != null) {
            buttonDirectionPressed.setBackground(PASSIVE_COLOR);
            buttonDirectionPressed = button;
            buttonDirectionPressed.setBackground(ACTIVE_COLOR);
        } else {
            buttonDirectionPressed = button;
            buttonDirectionPressed.setBackground(ACTIVE_COLOR);
        }
    }

    private void setDirection(byte newDirection) {
        direction = newDirection;
    }

    private ActionListener getActionListenerForDirection(byte newDirection, JButton button) {
        button.setBackground(PASSIVE_COLOR);
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDirection(newDirection);
                setButtonDirectionPressed(button);
            }
        };
    }

    private ActionListener getActionListenerForMove(JButton button, int i) {
        button.setBackground(PASSIVE_COLOR);
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonMovePressed == null) {
                    buttonMovePressed = button;
                    buttonMovePressed.setBackground(ACTIVE_COLOR);
                    setMove(i);
                } else {
                    if (buttonMovePressed == button) {
                        buttonMovePressed = null;
                        button.setBackground(PASSIVE_COLOR);
                        setMove(0);
                    } else {
                        buttonMovePressed.setBackground(PASSIVE_COLOR);
                        buttonMovePressed = button;
                        buttonMovePressed.setBackground(ACTIVE_COLOR);
                        setMove(i);
                    }
                }
            }
        };
    }

    private void setMove(int i) {
        switch (i) {
            case 0 -> {
                isBoost = false;
                isBrake = false;
            }
            case 1 -> {
                isBoost = false;
                isBrake = true;
            }
            case 2 -> {
                isBoost = true;
                isBrake = false;
            }
        }
    }
}
