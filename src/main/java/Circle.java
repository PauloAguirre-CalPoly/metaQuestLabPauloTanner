import java.awt.*;

public class Circle {

    private int x;
    private int y;
    private int diameter;

    public Circle(int x, int y) {
        diameter = 20;
        this.x = x;
        this.y = y;
    }

    public void drawCircle(Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval(x,y,diameter,diameter);
    }




}
