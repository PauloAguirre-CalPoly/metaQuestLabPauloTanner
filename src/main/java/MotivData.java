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
    private int wHeight;
    private int wWidth;
    private ArrayList<String> stream;

    public MotivData(int nCircle) {
        super(new Object());
        this.nCircle = nCircle;
        ballList = new ArrayList<>();
        stream = new ArrayList<>();
    }

    public void addData(String str) {
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
        int centerX = wWidth / 2;
        int centerY = wHeight / 2;
        int offset = 250;

        // north
        ballList.add(new Circle(centerX, centerY - offset));
        // west
        ballList.add(new Circle(centerX - offset, centerY));
        // east
        ballList.add(new Circle(centerX + offset, centerY));
        // south
        ballList.add(new Circle(centerX, centerY + offset));
        //        ballList.add(new Circle(150, 250));
        //        ballList.add(new Circle(300, 250));
        //        ballList.add(new Circle(450, 250));
        //        ballList.add(new Circle(600, 250));
    }

    public void setWSize(int wWidth, int wHeight) {
        this.wWidth = wWidth;
        this.wHeight = wHeight;
    }

    public List<Circle> getBallList() {
        return ballList;
    }

}
