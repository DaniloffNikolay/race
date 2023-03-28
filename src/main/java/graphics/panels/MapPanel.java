package graphics.panels;

import engine.Cell;
import engine.Field;
import graphics.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class MapPanel extends JComponent {

    private final Field field;
    private GameFrame gameFrame;

    public MapPanel(Field field, GameFrame gameFrame) {
        this.field = field;
        this.gameFrame = gameFrame;
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { //только клик
                //System.out.println(e.getX() + " " + e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) { //каждое нажатие
                //System.out.println(e.getX() / 5 + " " + e.getY() / 5);
            }

            @Override
            public void mouseReleased(MouseEvent e) {   //каждое отжатие
            }

            @Override
            public void mouseEntered(MouseEvent e) {        //каждое появление мышки на панели
            }

            @Override
            public void mouseExited(MouseEvent e) {         //каждый выход мышки за панель
                //System.out.println(e.getX() + " " + e.getY());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        int size = 13;
        int half = 7;
        Graphics2D g2 = (Graphics2D)g;
        Cell[][] map = field.getCells();
        for (int i = 0; i < map.length; i++) {
            for (int y = 0; y < map[i].length; y++) {
                Rectangle2D rectangle = new Rectangle2D.Double(y * size, i * size, size, size);
                Cell cell = map[i][y];
                if (cell.isActive()) {
                    g2.setColor(Color.WHITE);
                    if (cell.isPlayerOneHere()) {
                        g2.setColor(Color.BLUE);
                        System.out.println("Player ONE");
                    } else if (cell.isPlayerTwoHere()) {
                        g2.setColor(Color.GREEN);
                        System.out.println("Player TWO");
                    }
                } else {
                    g2.setColor(Color.YELLOW);
                }

                g2.fill(rectangle);

                rectangle = new Rectangle2D.Double((y * size) + half, i * size, 1, size);
                g2.setColor(Color.BLUE);
                g2.fill(rectangle);

                rectangle = new Rectangle2D.Double(y * size, (i * size) + half, size, 1);
                g2.fill(rectangle);
            }
        }

    }

}
