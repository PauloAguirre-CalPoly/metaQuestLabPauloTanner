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
        //MotivData.getInstance().getBallList().get(0).drawCircle(g);
//        for(Circle circle: MotivData.getInstance().getBallList()){
//            if(){
//                circle.drawCircle(g);
//            }
//        }
        MotivData.getInstance().computeCircle(g);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
