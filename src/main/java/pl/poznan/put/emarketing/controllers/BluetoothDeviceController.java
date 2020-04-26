package pl.poznan.put.emarketing.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.poznan.put.emarketing.services.BluetoothDeviceService;

@RestController
@RequestMapping("/bluetooth")
public class BluetoothDeviceController {
    private final BluetoothDeviceService bluetoothDeviceService;

    public BluetoothDeviceController(BluetoothDeviceService bluetoothDeviceService) {
        this.bluetoothDeviceService = bluetoothDeviceService;
    }

    @GetMapping("/test")
    public String test() {
        return "123";
    }
}
