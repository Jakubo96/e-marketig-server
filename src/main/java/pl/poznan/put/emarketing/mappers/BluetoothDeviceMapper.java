package pl.poznan.put.emarketing.mappers;

import lombok.experimental.UtilityClass;
import pl.poznan.put.emarketing.models.dtos.BluetoothDeviceDto;
import pl.poznan.put.emarketing.models.entities.BluetoothDevice;

@UtilityClass
public class BluetoothDeviceMapper {
    public BluetoothDevice toEntity(BluetoothDeviceDto dto) {
        return BluetoothDevice.builder()
                .username(dto.getUsername())
                .mac(dto.getMac())
                .pushToken(dto.getPushToken())
                .build();
    }

    public BluetoothDeviceDto toDto(BluetoothDevice entity) {
        return BluetoothDeviceDto.builder()
                .username(entity.getUsername())
                .mac(entity.getMac())
                .pushToken(entity.getPushToken())
                .build();
    }
}
