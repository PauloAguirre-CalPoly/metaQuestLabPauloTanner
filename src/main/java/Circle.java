import java.awt.*;

public class Circle {

    private int color;
    private int x;
    private int y;
    private int diameter;

    public Circle(int x, int y) {
        diameter = 40;
        this.x = x;
        this.y = y;
        this.color = 0;
    }

    public void drawCircle(Graphics g){
        if(color == 0){
            g.setColor(Color.BLACK);
        }else{
            g.setColor(Color.WHITE);
        }
        g.fillOval(x,y,diameter,diameter);
    }
    public void setColor(int num){
        color = num;
    }
    public void setDiameter(int num){
        this.diameter = num;
    }

}
