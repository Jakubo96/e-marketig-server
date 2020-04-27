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
import pl.poznan.put.emarketing.models.enums.MessageType;

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

        Message message = buildRecurringMessage(pushToken, hour, minute);
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

    private Message buildRecurringMessage(String pushToken, int hour, int minute) {
        return Message.builder()
                .putData("hour", String.valueOf(hour))
                .putData("minute", String.valueOf(minute))
                .putData("messageType", MessageType.RECURRING.name())
                .putData("messageTitle", "Recurring message")
                .putData("messageBody", "That's the recurring message")
                .putData("messageImage", "https://png2.cleanpng.com/sh/51a334175cd7f666ef30a17eea42d9b3/L0KzQYm3VMA1N5h5iZH0aYP2gLBuTfVud5tuRdpAbXHxPcTyif4ua5DxhAQ2d3H5dX7rggJsNaRwgdC2bHnqeMW0kBtqdl5tedDtLXXwf7vwTcVia2VrS6I7M0CzcrO6TsY0OGQ7TKM9MUW1Qom8VscxPmIATqo3cH7q/kisspng-emoji-human-skin-color-wave-dark-skin-light-skin-hand-emoji-5ac4f302300bb3.6303641415228567061968.png")
                .setNotification(
                        Notification.builder()
                                .setTitle("Your attention is required")
                                .setBody("Click on the message to setup new recurring notification")
                                .setImage("https://upload.wikimedia.org/wikipedia/en/3/34/Metric_clock.JPG")
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
