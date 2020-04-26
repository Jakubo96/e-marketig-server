package pl.poznan.put.emarketing.services;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final FCMService fcmService;

    public BluetoothDeviceService(BluetoothDeviceRepository bluetoothDeviceRepository, FCMService fcmService) {
        this.bluetoothDeviceRepository = bluetoothDeviceRepository;
        this.fcmService = fcmService;
    }

    public List<BluetoothDeviceDto> getAllDevices() {
        return this.bluetoothDeviceRepository.findAll()
                .stream().map(BluetoothDeviceMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public void login(BluetoothDeviceDto device) {
        Optional<BluetoothDevice> existingDevice = this.bluetoothDeviceRepository.findByMac(device.getMac());
        existingDevice.ifPresent(this.bluetoothDeviceRepository::delete);
        this.bluetoothDeviceRepository.save(BluetoothDeviceMapper.toEntity(device));
    }

    @Transactional
    public void logout(String mac) {
        this.bluetoothDeviceRepository.deleteByMac(mac);
    }

    public BluetoothDeviceDto findDevice(String mac) throws FirebaseMessagingException {
        BluetoothDeviceDto foundDevice = this.bluetoothDeviceRepository.findByMac(mac)
                .map(BluetoothDeviceMapper::toDto)
                .orElse(null);
        
        if(foundDevice != null) {
            this.sendPushNotification(foundDevice);
        }
        
        return foundDevice;
    }

    private void sendPushNotification(BluetoothDeviceDto foundDevice) throws FirebaseMessagingException {
        this.fcmService.sendNotificationToGivenDevice(foundDevice);
    }
}
