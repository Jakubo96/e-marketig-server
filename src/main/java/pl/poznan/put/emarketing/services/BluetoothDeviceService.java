package pl.poznan.put.emarketing.services;

import org.springframework.stereotype.Service;
import pl.poznan.put.emarketing.mappers.BluetoothDeviceMapper;
import pl.poznan.put.emarketing.models.dtos.BluetoothDeviceDto;
import pl.poznan.put.emarketing.models.entities.BluetoothDevice;
import pl.poznan.put.emarketing.repositories.BluetoothDeviceRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BluetoothDeviceService {
    private final BluetoothDeviceRepository bluetoothDeviceRepository;

    public BluetoothDeviceService(BluetoothDeviceRepository bluetoothDeviceRepository) {
        this.bluetoothDeviceRepository = bluetoothDeviceRepository;
    }

    public List<BluetoothDeviceDto> getAllDevices() {
        return this.bluetoothDeviceRepository.findAll()
                .stream().map(BluetoothDeviceMapper::toDto).collect(Collectors.toList());
    }

    public void login(BluetoothDeviceDto device) {
        Optional<BluetoothDevice> existingDevice = this.bluetoothDeviceRepository.findByMac(device.getMac());
        existingDevice.ifPresent(this.bluetoothDeviceRepository::delete);
        this.bluetoothDeviceRepository.save(BluetoothDeviceMapper.toEntity(device));
    }
}
