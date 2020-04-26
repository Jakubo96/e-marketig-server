package pl.poznan.put.emarketing.controllers;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.emarketing.models.dtos.BluetoothDeviceDto;
import pl.poznan.put.emarketing.services.BluetoothDeviceService;

import java.util.List;

@RestController
@RequestMapping("/bluetooth")
public class BluetoothDeviceController {
    private final BluetoothDeviceService bluetoothDeviceService;

    public BluetoothDeviceController(BluetoothDeviceService bluetoothDeviceService) {
        this.bluetoothDeviceService = bluetoothDeviceService;
    }

    @GetMapping("/all-devices")
    public List<BluetoothDeviceDto> test() {
        return this.bluetoothDeviceService.getAllDevices();
    }

    @PostMapping("/device/login")
    public void login(@RequestBody @Validated BluetoothDeviceDto device) {
        this.bluetoothDeviceService.login(device);
    }

    @DeleteMapping("/device/logout/{mac}")
    public void logout(@PathVariable String mac) {
        this.bluetoothDeviceService.logout(mac);
    }

    @GetMapping("/device/{mac}")
    public BluetoothDeviceDto deviceDetected(@PathVariable String mac) throws FirebaseMessagingException {
        return this.bluetoothDeviceService.findDevice(mac);
    }
}
