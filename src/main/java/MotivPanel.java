import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MotivPanel extends JPanel implements PropertyChangeListener {

    @Override
    protected void paintComponent(Graphics g){
        //ball set goes here
        super.paintComponent(g);
        setBackground(Color.PINK);
        for(Circle circle: MotivData.getInstance().getBallList()){
            circle.drawCircle(g);
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
