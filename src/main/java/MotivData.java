import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class MotivData extends PropertyChangeSupport {
    /**
     * Constructs a {@code PropertyChangeSupport} object.
     *
     * @param sourceBean The bean to be given as the source for any events.
     */
    private static MotivData instance;
    private int nCircle;
    private List<Circle> ballList;
    private ArrayList<String> stream;
    private float eyeFixationX;
    private float armHeight;
    private int binColor;

    public MotivData(int nCircle) {
        super(new Object());
        this.nCircle = nCircle;
        ballList = new ArrayList<>();
        stream = new ArrayList<>();
        this.eyeFixationX = 0;
        this.armHeight = 0;

    }

    public void addData(String str){
        stream.add(str);
    }

    public ArrayList<String> dataStream() {
        return stream;
    }

    public static MotivData getInstance() {
        if (instance == null) {
            instance = new MotivData(0);
        }
        return instance;
    }

    public void createCircles() {
        ballList.add(new Circle(150, 250));
        ballList.add(new Circle(350, 100));
        ballList.add(new Circle(350, 400));
        ballList.add(new Circle(600, 250));
    }

//    public void setWSize(int wWidth, int wHeight) {
//        this.wWidth = wWidth;
//        this.wHeight = wHeight;
//    }

    public void setEyeFixation(float eyeFixationX) {
        this.eyeFixationX = eyeFixationX;
    }

    public void setArmHeight(float armHeight) {
        this.armHeight = armHeight;
    }

    public void computeCircle(Graphics g){
        computeBinary();
        if(eyeFixationX < 0 ){
            ballList.get(3).setColor(binColor);
            ballList.get(3).drawCircle(g);
        }else{
            ballList.get(0).setColor(binColor);
            ballList.get(0).drawCircle(g);
        }
//        if(eyeFixationY < 0){
//            ballList.get(2).drawCircle(g);
//        }else{
//            ballList.get(1).drawCircle(g);
//        }
    }
    public void computeBinary(){
        if(armHeight >= 0){
            binColor = 0;
        }else{
            binColor = 1;
        }
    }

    public List<Circle> getBallList() {
        return ballList;
    }

    public void repaint() {
        firePropertyChange("repaint", null, null);
    }

}
