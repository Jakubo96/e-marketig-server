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
import pl.poznan.put.emarketing.models.dtos.NotificationTimeDto;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
public class FCMService {
    private final FirebaseMessaging fcmInstance = FirebaseMessaging.getInstance();
    private final Logger logger = LoggerFactory.getLogger(FCMService.class);


    public void sendNotificationToGivenDevice(BluetoothDeviceDto device) {
        Message message = buildMessage(device);
        sendMessage(message);
    }

    public void setNotificationTime(String pushToken, NotificationTimeDto notificationTime) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(notificationTime.getTime());

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        Message message = buildCyclicalMessage(pushToken, hour, minute);
        sendMessage(message);
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

    private Message buildCyclicalMessage(String pushToken, int hour, int minute) {
        return Message.builder()
                .setNotification(
                        Notification.builder()
                                .setTitle("Your attention is required")
                                .setBody("Click onm the message to setup new cyclical attention")
                                .setImage("https://i.kym-cdn.com/entries/icons/original/000/002/361/maxresdefault.jpg")
                                .build()
                )
                .setToken(pushToken)
                .build();
    }

    private void sendMessage(Message message) {
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
}
