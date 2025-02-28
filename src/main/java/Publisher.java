import org.eclipse.paho.client.mqttv3.*;

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
			while (true) {
				String content = "this is message " + counter;
				MqttMessage message = new MqttMessage(content.getBytes());
				message.setQos(2);
				if (client.isConnected())
					client.publish(TOPIC, message);
				counter++;
				System.out.println("Message published: " + content);
				Thread.sleep(5000);
        }
		} catch (MqttPersistenceException e) {
				throw new RuntimeException(e);
			} catch (MqttSecurityException e) {
				throw new RuntimeException(e);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} catch (MqttException e) {
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