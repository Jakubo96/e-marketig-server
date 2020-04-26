package pl.poznan.put.emarketing.controllers;

import org.springframework.web.bind.annotation.*;
import pl.poznan.put.emarketing.models.dtos.DetectedDevicesDto;
import pl.poznan.put.emarketing.services.DetectedDeviceService;

import javax.validation.Valid;

@RestController
@RequestMapping("/detected")
public class DetectedDeviceController {
    private final DetectedDeviceService detectedDeviceService;

    public DetectedDeviceController(DetectedDeviceService detectedDeviceService) {
        this.detectedDeviceService = detectedDeviceService;
    }

    @GetMapping("/all-devices")
    public DetectedDevicesDto allDevices() {
        return detectedDeviceService.getAllDevices();
    }

    @PostMapping("/overwrite")
    public void overwrite(@Valid @RequestBody DetectedDevicesDto detectedDevices) {
        detectedDeviceService.overwrite(detectedDevices);
    }
}
