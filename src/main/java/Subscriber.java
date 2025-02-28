import org.eclipse.paho.client.mqttv3.*;

import java.io.IOException;

/**
 * This class is a simple MQTT subscriber that listens to a TOPIC.
 * The BROKER is test.mosquitto.org and the TOPIC is cal-poly/csc/309.
 * (run this and the publisher at the same time)
 *
 * @author Paulo and Tanner
 * @version 1.0
 */
public class Subscriber implements MqttCallback, Runnable {
	
	private final static String BROKER = "tcp://test.mosquitto.org:1883";
	private final static String TOPIC = "Lab5_Tanner_Paulo";
	private final static String CLIENT_ID = "jgs-subscriber";
	//public static BufferedWriter out = null;

	@Override
	public void run() {
        MqttClient client = null;
        try {
			client = new MqttClient(BROKER, CLIENT_ID);
			Subscriber subscriber = new Subscriber();
			client.setCallback(subscriber);
			client.connect();
			System.out.println("Connected to BROKER: " + BROKER);
			client.subscribe(TOPIC);
			System.out.println("Subscribed to TOPIC: " + TOPIC);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }

	}

//	public static void main(String[] args) throws MqttException {
//
//		MqttClient client = new MqttClient(BROKER, CLIENT_ID);
//		Subscriber subscriber = new Subscriber();
//		client.setCallback(subscriber);
//		client.connect();
//		System.out.println("Connected to BROKER: " + BROKER);
//		client.subscribe(TOPIC);
//		System.out.println("Subscribed to TOPIC: " + TOPIC);
//
//    }
	
	@Override
	public void connectionLost(Throwable throwable) {
		System.out.println("Connection lost: " + throwable.getMessage());
	}
	
	@Override
	public void messageArrived(String s, MqttMessage mqttMessage) throws IOException {
		System.out.println("Message arrived. Topic: " + s +
			" Message: " + new String(mqttMessage.getPayload()));
		MotivData.getInstance().addData(new String(mqttMessage.getPayload()));
	}
	
	@Override
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		System.out.println("Delivered complete: " + iMqttDeliveryToken.getMessageId());
    }

}
