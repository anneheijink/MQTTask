package com.anneheijink.mqttask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by anne on 1-6-15.
 */
public class TaskerReceiver extends BroadcastReceiver {
    public String adress;
    public String topic;
    public String messageText;

    @Override
    public void onReceive(final Context context, final Intent intent) {
        Log.i("TaskerReceiver", "Received");
        Bundle bundle = intent.getExtras();
        adress = "tcp://"+bundle.getString("com.anneheijink.mqttask.extra.STRING_IP");
        topic = bundle.getString("com.anneheijink.mqttask.extra.STRING_TOPIC");
        messageText = bundle.getString("com.anneheijink.mqttask.extra.STRING_MESSAGE");

        try {
            MemoryPersistence persistence = new MemoryPersistence();
            MqttClient client = new MqttClient(adress,"thisismyid", persistence);
            client.connect();
            MqttMessage message = new MqttMessage();
            message.setPayload(messageText.getBytes());
            client.publish(topic, message);
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
            Log.e("Error", e.toString());
        }
    }
}
