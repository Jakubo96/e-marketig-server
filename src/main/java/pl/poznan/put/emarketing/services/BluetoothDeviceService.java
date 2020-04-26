package pl.poznan.put.emarketing.services;

import org.springframework.stereotype.Service;
import pl.poznan.put.emarketing.repositories.BluetoothDeviceRepository;

@Service
public class BluetoothDeviceService {
    private final BluetoothDeviceRepository bluetoothDeviceRepository;

    public BluetoothDeviceService(BluetoothDeviceRepository bluetoothDeviceRepository) {
        this.bluetoothDeviceRepository = bluetoothDeviceRepository;
    }
}
