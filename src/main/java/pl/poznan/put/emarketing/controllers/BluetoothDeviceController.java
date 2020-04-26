package pl.poznan.put.emarketing.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.emarketing.models.dtos.BluetoothDeviceDto;
import pl.poznan.put.emarketing.services.BluetoothDeviceService;

import javax.websocket.server.PathParam;
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
}
