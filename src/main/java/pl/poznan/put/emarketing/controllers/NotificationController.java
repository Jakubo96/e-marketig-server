package pl.poznan.put.emarketing.controllers;

import org.springframework.web.bind.annotation.*;
import pl.poznan.put.emarketing.models.dtos.NotificationTimeDto;
import pl.poznan.put.emarketing.services.FCMService;

import javax.validation.Valid;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final FCMService fcmService;

    public NotificationController(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    @PutMapping("/{pushToken}")
    public void notificationTimeChanged(@PathVariable String pushToken,
                                        @Valid @RequestBody NotificationTimeDto notificationTime) {
        fcmService.setNotificationTime(pushToken, notificationTime);
    }
}
