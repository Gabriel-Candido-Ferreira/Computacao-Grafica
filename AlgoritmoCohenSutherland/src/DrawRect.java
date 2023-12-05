import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawRect extends JComponent {
    private int x1 = 0;
    private int x2 = 0;
    private int y1 = 0;
    private int y2 = 0;
    private ArrayList<Retas> linhas;
    private boolean mod = false;
    private MouseListenerForObjects mouseListenerForObjects;
    private CohenSutherland cohenSutherland;

    public DrawRect() {

        linhas = new ArrayList<>();
        cohenSutherland = new CohenSutherland(this);
        mouseListenerForObjects = new MouseListenerForRect(this);
        addMouseListener(mouseListenerForObjects);
        addMouseMotionListener(mouseListenerForObjects);
    }

    private void drawTrueRect(Graphics g) {
        int q1 = x2 - x1;
        int q2 = y2 - y1;
        if (q1 > 0 && q2 > 0) {
            g.drawRect(x1, y1, x2 - x1, y2 - y1);
        } else if (q1 < 0 && q2 < 0) {
            g.drawRect(x2, y2, x1 - x2, y1 - y2);
        } else if (q1 > 0 && q2 < 0) {
            g.drawRect(x1, y2, x2 - x1, y1 - y2);
        } else {
            g.drawRect(x2, y1, x1 - x2, y2 - y1);
        }
    }

    public void setStartCoordinates(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public void setEndCoordinates(int x2, int y2) {
        this.x2 = x2;
        this.y2 = y2;
    }

    public void addLine(Retas retas) {
        this.linhas.add(retas);
    }

    public void setStartLineCoordinates(int x, int y, int index) {
        linhas.get(index).setPoint1(x, y);
    }

    public void setEndLineCoordinates(int x, int y, int index) {
        linhas.get(index).setPoint2(x, y);
    }

    public void setMod(boolean mod) {
        this.mod = mod;
        System.out.println("1");
        removeMouseListener(mouseListenerForObjects);
        removeMouseMotionListener(mouseListenerForObjects);
        mouseListenerForObjects = new MouseListenerForLines(this);
        addMouseListener(mouseListenerForObjects);
        addMouseMotionListener(mouseListenerForObjects);
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    private boolean checkIntersect(Retas retas) {
        if (retas.getPoint1().getX() == retas.getPoint2().getX() &&
                (retas.getPoint1().getX() == x1 || retas.getPoint1().getX() == x2)) {
            return true;
        } else if (retas.getPoint1().getY() == retas.getPoint2().getY() &&
                (retas.getPoint1().getY() == y1 || retas.getPoint1().getY() == y2)) {
            return true;
        }
        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTrueRect(g);

        if(mod) {
            Retas retas;
            for (int i = 0; i < this.linhas.size(); i++) {
                retas = this.linhas.get(i);
                int lineClass = cohenSutherland.getClassLine(retas);
                switch (lineClass) {
                    case 0:
                        g.setColor(Color.RED);
                        g.drawLine(retas.getPoint1().getX(), retas.getPoint1().getY(),
                                retas.getPoint2().getX(), retas.getPoint2().getY());
                        break;
                    case 1:
                        g.setColor(Color.BLUE);

                        Retas subRetas = cohenSutherland.findSubLine(retas);

                        g.drawLine(retas.getPoint1().getX(), retas.getPoint1().getY(),
                                subRetas.getPoint1().getX(), subRetas.getPoint1().getY());
                        g.setColor(Color.BLUE);
                        g.drawLine(subRetas.getPoint2().getX(), subRetas.getPoint2().getY() ,
                                retas.getPoint2().getX(), retas.getPoint2().getY());
                        if (checkIntersect(subRetas)) {
                            g.setColor(Color.GREEN);
                        } else {
                            g.setColor(Color.RED);
                        }
                        g.drawLine(subRetas.getPoint1().getX(), subRetas.getPoint1().getY(),
                                subRetas.getPoint2().getX(), subRetas.getPoint2().getY());



                        g.setColor(Color.GREEN);
                        if (subRetas.getPoint1().getX() == x1 || subRetas.getPoint1().getX() == x2 ||
                                subRetas.getPoint1().getY() == y1 || subRetas.getPoint1().getY() == y2) {
                            if (subRetas.getPoint1().getX() <= x2 && subRetas.getPoint1().getX() >= x1) {
                                g.fillOval(subRetas.getPoint1().getX() - 2, subRetas.getPoint1().getY() - 2,6,6);
                            }

                        }
                        if (subRetas.getPoint2().getX() == x1 || subRetas.getPoint2().getX() == x2 ||
                                subRetas.getPoint2().getY() == y1 || subRetas.getPoint2().getY() == y2) {

                            if (subRetas.getPoint2().getX() <= x2 && subRetas.getPoint2().getX() >= x1) {
                                g.fillOval(subRetas.getPoint2().getX() - 2, subRetas.getPoint2().getY() - 2,6,6);
                            }
                        }



                        break;
                    case 2:
                        g.setColor(Color.BLUE);
                        g.drawLine(retas.getPoint1().getX(), retas.getPoint1().getY(),
                                retas.getPoint2().getX(), retas.getPoint2().getY());
                        break;
                }

            }

        }
    }
}