package pl.poznan.put.emarketing.mappers;

import lombok.experimental.UtilityClass;
import pl.poznan.put.emarketing.models.dtos.DetectedDeviceDto;
import pl.poznan.put.emarketing.models.entities.DetectedDevice;

@UtilityClass
public class DetectedDeviceMapper {
    public DetectedDevice toEntity(DetectedDeviceDto dto) {
        return DetectedDevice.builder()
                .username(dto.getUsername())
                .mac(dto.getMac())
                .build();
    }

    public DetectedDeviceDto toDto(DetectedDevice entity) {
        return DetectedDeviceDto.builder()
                .username(entity.getUsername())
                .mac(entity.getMac())
                .build();
    }
}
