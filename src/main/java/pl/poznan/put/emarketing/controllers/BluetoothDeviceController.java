package pl.poznan.put.emarketing.controllers;

import org.springframework.web.bind.annotation.*;
import pl.poznan.put.emarketing.models.dtos.BluetoothDeviceDto;
import pl.poznan.put.emarketing.services.BluetoothDeviceService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bluetooth")
public class BluetoothDeviceController {
    private final BluetoothDeviceService bluetoothDeviceService;

    public BluetoothDeviceController(BluetoothDeviceService bluetoothDeviceService) {
        this.bluetoothDeviceService = bluetoothDeviceService;
    }

    @GetMapping("/all-devices")
    public List<BluetoothDeviceDto> allDevices() {
        return bluetoothDeviceService.getAllDevices();
    }

    @PostMapping("/device/login")
    public void login(@RequestBody @Valid BluetoothDeviceDto device) {
        bluetoothDeviceService.login(device);
    }

    @DeleteMapping("/device/logout/{mac}")
    public void logout(@PathVariable String mac) {
        bluetoothDeviceService.logout(mac);
    }

    @GetMapping("/device/{mac}")
    public BluetoothDeviceDto deviceDetected(@PathVariable String mac) {
        return bluetoothDeviceService.findDevice(mac);
    }
}
