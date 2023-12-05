import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class MouseListenerForObjects implements MouseListener, MouseMotionListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
class MouseListenerForRect extends MouseListenerForObjects {

    DrawRect drawRect;

    public MouseListenerForRect(DrawRect drawRect) {
        this.drawRect = drawRect;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawRect.setStartCoordinates(e.getX(),e.getY());
        drawRect.setEndCoordinates(e.getX(),e.getY());
        drawRect.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawRect.setEndCoordinates(e.getX(),e.getY());
        drawRect.repaint();
        drawRect.setMod(true);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        drawRect.setEndCoordinates(e.getX(),e.getY());
        drawRect.repaint();
    }

}

class MouseListenerForLines extends MouseListenerForObjects {

    DrawRect drawRect;
    private int lineIndex;
    public MouseListenerForLines(DrawRect drawRect) {
        this.drawRect = drawRect;
        lineIndex = 0;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawRect.addLine(new Retas());
        drawRect.setStartLineCoordinates(e.getX(),e.getY(),lineIndex);
        drawRect.setEndLineCoordinates(e.getX(),e.getY(),lineIndex);
        drawRect.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawRect.setEndLineCoordinates(e.getX(),e.getY(),lineIndex);
        drawRect.repaint();

        lineIndex++;
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        drawRect.setEndLineCoordinates(e.getX(),e.getY(),lineIndex);
        drawRect.repaint();

    }


}