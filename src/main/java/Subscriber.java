import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONObject;

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

	private float torsoX = 0.0f, torsoY = 1.4f, torsoZ = 0.0f;
	private float leftHandX = -0.3f, leftHandY = 1.0f, leftHandZ = 0.5f;
	private float rightHandX = 0.3f, rightHandY = 1.0f, rightHandZ = 0.5f;
	private float leftGazeX = 0.0f, leftGazeY = 0.0f, leftGazeZ = 1.0f;
	private float rightGazeX = 0.0f, rightGazeY = 0.0f, rightGazeZ = 1.0f;

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

	private void parseData(String json) {
		try {
			JSONObject obj = new JSONObject(json);

			JSONObject torso = obj.getJSONObject("torso");
			torsoX = (float) torso.getDouble("x");
			torsoY = (float) torso.getDouble("y");
			torsoZ = (float) torso.getDouble("z");

			// ✅ Parse hands and feet
			JSONObject leftHand = obj.getJSONObject("leftHand");
			leftHandX = (float) leftHand.getDouble("x");
			leftHandY = (float) leftHand.getDouble("y");
			JSONObject rightHand = obj.getJSONObject("rightHand");
			rightHandX = (float) rightHand.getDouble("x");
			rightHandY = (float) rightHand.getDouble("y");
			JSONObject leftFoot = obj.getJSONObject("leftFoot");

			// LeftGAZE
			JSONObject leftEyeGaze = obj.getJSONObject("leftEyeGaze");
			leftGazeX = (float) leftEyeGaze.getDouble("x");
			leftGazeY = (float) leftEyeGaze.getDouble("y");
			leftGazeZ = (float) leftEyeGaze.getDouble("z");
			// Right GAZE
			JSONObject rightEyeGaze = obj.getJSONObject("rightEyeGaze");
			rightGazeX = (float) rightEyeGaze.getDouble("x");
			rightGazeY = (float) rightEyeGaze.getDouble("y");
			rightGazeZ = (float) rightEyeGaze.getDouble("z");
			// ✅ Compute fixation point using both gaze rays
			float[] leftGazeDir = {leftGazeX, leftGazeY, leftGazeZ};
			float[] rightGazeDir = {rightGazeX, rightGazeY, rightGazeZ};

		} catch (Exception e) {
			System.out.println("Error parsing JSON: " + e.getMessage());
		}
	}
	
	@Override
	public void connectionLost(Throwable throwable) {
		System.out.println("Connection lost: " + throwable.getMessage());
	}
	
	@Override
	public void messageArrived(String s, MqttMessage mqttMessage) throws IOException {
		System.out.println("Message arrived. Topic: " + s +
			" Message: " + new String(mqttMessage.getPayload()));
		String payLoad = new String(mqttMessage.getPayload());
		MotivData.getInstance().addData(payLoad);
		parseData(payLoad);

	}
	
	@Override
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		System.out.println("Delivered complete: " + iMqttDeliveryToken.getMessageId());
    }

}
