package pl.poznan.put.emarketing.services;

import org.springframework.stereotype.Service;
import pl.poznan.put.emarketing.mappers.DetectedDeviceMapper;
import pl.poznan.put.emarketing.models.dtos.DetectedDevicesDto;
import pl.poznan.put.emarketing.models.entities.DetectedDevice;
import pl.poznan.put.emarketing.repositories.DetectedDeviceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetectedDeviceService {
    private final DetectedDeviceRepository detectedDeviceRepository;

    public DetectedDeviceService(DetectedDeviceRepository detectedDeviceRepository) {
        this.detectedDeviceRepository = detectedDeviceRepository;
    }

    public DetectedDevicesDto getAllDevices() {
        return new DetectedDevicesDto(detectedDeviceRepository.findAll()
                .stream().map(DetectedDeviceMapper::toDto).collect(Collectors.toList()));
    }

    public void overwrite(DetectedDevicesDto detectedDevices) {
        List<DetectedDevice> devicesEntities = detectedDevices.getDetectedDevices().stream()
                .map(DetectedDeviceMapper::toEntity)
                .collect(Collectors.toList());

        detectedDeviceRepository.deleteAll();
        detectedDeviceRepository.saveAll(devicesEntities);
    }
}
