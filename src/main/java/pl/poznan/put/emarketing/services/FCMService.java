package pl.poznan.put.emarketing.services;

import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.poznan.put.emarketing.models.dtos.BluetoothDeviceDto;

import java.text.MessageFormat;

@Service
public class FCMService {
    private final FirebaseMessaging fcmInstance = FirebaseMessaging.getInstance();
    private final Logger logger = LoggerFactory.getLogger(FCMService.class);


    public void sendNotificationToGivenDevice(BluetoothDeviceDto device) {
        Message message = buildMessage(device);

        ApiFutures.addCallback(fcmInstance.sendAsync(message), new ApiFutureCallback<>() {
            @Override
            public void onFailure(Throwable t) {
                logger.error(MessageFormat.format("Error - message couldn't be sent: {0}", t));
            }

            @Override
            public void onSuccess(String result) {
                logger.info(MessageFormat.format("Successfully sent message {0}", result));
            }
        }, MoreExecutors.directExecutor());
    }

    private Message buildMessage(BluetoothDeviceDto device) {
        return Message.builder()
                .setNotification(
                        Notification.builder()
                                .setTitle(MessageFormat.format("Hello {0}", device.getUsername()))
                                .setBody(MessageFormat.format("Your device has just been detected.\nBeware!", device.getMac()))
                                .setImage("https://i.kym-cdn.com/entries/icons/original/000/002/361/maxresdefault.jpg")
                                .build()
                )
                .setToken(device.getPushToken())
                .build();
    }
}
