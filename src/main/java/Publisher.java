import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * This class is a simple MQTT publisher that sends messages to a TOPIC.
 * The broker is test.mosquitto.org and the TOPIC is cal-poly/csc/309.
 * (run this and the subscriber at the same time)
 *
 * @author Paulo and Tanner
 * @version 1.0
 */
public class Publisher implements Runnable{
	
	private final static String BROKER = "tcp://test.mosquitto.org:1883";
	private final static String TOPIC = "Lab5_Tanner_Paulo";
	private final static String CLIENT_ID = "jgs-publisher";

	@Override
	public void run() {
        MqttClient client = null;
        try {
            client = new MqttClient(BROKER, CLIENT_ID);
			client.connect();
			System.out.println("Connected to BROKER: " + BROKER);
			int counter = 0;
			String content;
			File file = new File("testData.json");
//			String jsonData = new String(Files.readAllBytes(Paths.get("testData.json")));
			Scanner scanner = new Scanner(file);
			//Scanner scan = new Scanner(file);
			while (scanner.hasNext()) {
				content = "";
				for(int j = 21; j > 0; j--) {
					content += scanner.nextLine();
				}
				//String content = "this is message " + counter;
				MqttMessage message = new MqttMessage(content.getBytes());
				message.setQos(2);
				if (client.isConnected())
					client.publish(TOPIC, message);
				//counter++;
				System.out.println("Message published: " + content);
				Thread.sleep(2000);
        }
			scanner.close();
		} catch (MqttPersistenceException e) {
				throw new RuntimeException(e);
			} catch (MqttSecurityException e) {
				throw new RuntimeException(e);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} catch (MqttException e) {
				throw new RuntimeException(e);
			} catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//	public static void main(String[] args) {
//		try {
//			MqttClient client = new MqttClient(BROKER, CLIENT_ID);
//			client.connect();
//			System.out.println("Connected to BROKER: " + BROKER);
//			int counter = 0;
//			while (true) {
//				String content = "this is message " + counter;
//				MqttMessage message = new MqttMessage(content.getBytes());
//				message.setQos(2);
//				if (client.isConnected())
//					client.publish(TOPIC, message);
//				counter++;
//				System.out.println("Message published: " + content);
//				Thread.sleep(5000);
//			}
//		} catch (MqttException | InterruptedException e) {
//			e.printStackTrace();
//		}
	}

}