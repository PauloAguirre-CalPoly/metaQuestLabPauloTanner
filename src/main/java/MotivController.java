import java.awt.event.*;

public class MotivController implements MouseListener, MouseMotionListener, ComponentListener {

    @Override
    public void componentResized(ComponentEvent e) {
        // update the window size
        MotivData.getInstance().setWSize(e.getComponent().getWidth(), e.getComponent().getHeight());
        // create new circles based on size
        MotivData.getInstance().getBallList().clear();
        MotivData.getInstance().createCircles();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

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
