package pl.poznan.put.emarketing.services;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;
import pl.poznan.put.emarketing.models.dtos.BluetoothDeviceDto;

import java.text.MessageFormat;

@Service
public class FCMService {
    private final FirebaseMessaging fcmInstance = FirebaseMessaging.getInstance();

    public void sendNotificationToGivenDevice(BluetoothDeviceDto device) throws FirebaseMessagingException {
        Message message = this.buildMessage(device);

        String response = fcmInstance.send(message);
        System.out.println(MessageFormat.format("Successfully sent message: {0}", response));
    }

    private Message buildMessage(BluetoothDeviceDto device) {
        return Message.builder()
                .setNotification(
                        Notification.builder()
                                .setTitle(MessageFormat.format("Hello {0}", device.getUsername()))
                                .setBody(MessageFormat.format("Your device was just detected.\n Beware!", device.getMac()))
                                .setImage("https://i.kym-cdn.com/entries/icons/original/000/002/361/maxresdefault.jpg")
                                .build()
                )
                .setToken(device.getPushToken())
                .build();
    }
}
