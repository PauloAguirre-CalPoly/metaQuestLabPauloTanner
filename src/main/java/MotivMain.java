import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MotivMain extends JFrame {

    public MotivMain() {
        MotivPanel mPanel = new MotivPanel();
        add(mPanel);
        MotivController mController = new MotivController();

        mPanel.addMouseListener(mController);
        mPanel.addMouseMotionListener(mController);
        mPanel.addComponentListener(mController);

        MotivData.getInstance().createCircles();
        MotivData.getInstance().addPropertyChangeListener(mPanel);
        MotivData.getInstance().setWSize(this.getWidth(), this.getHeight());
    }

    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            saveDataToFile("appData.csv", MotivData.getInstance().dataStream());
        }));

        //disable thread2 when collecting data
        Subscriber sub = new Subscriber();
        Thread modelThread1 = new Thread(sub);
        modelThread1.start();

        Publisher pub = new Publisher();
        Thread modelThread2 = new Thread(pub);
        modelThread2.start();

        MotivMain main = new MotivMain();
        main.setTitle("Motive Tester");
        main.setSize(700, 700);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
    }

    public static void saveDataToFile(String fileName, ArrayList<String> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            int i = 0;
            //GameData.getInstance().getAppData().get(i) != null
            while(list.get(i) != null) {
                writer.write(list.get(i) + "\n");
                i++;
            }
            // writer.write(data);
            System.out.println("Data saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }
}
