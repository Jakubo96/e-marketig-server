package pl.poznan.put.emarketing.mappers;

import lombok.experimental.UtilityClass;
import pl.poznan.put.emarketing.models.dtos.BluetoothDeviceDto;
import pl.poznan.put.emarketing.models.entities.BluetoothDevice;

@UtilityClass
public class BluetoothDeviceMapper {
    public BluetoothDevice toEntity(BluetoothDeviceDto dto) {
        return BluetoothDevice.builder()
                .name(dto.getName())
                .macAddress(dto.getMacAddress())
                .pushToken(dto.getPushToken())
                .build();
    }

    public BluetoothDeviceDto toDto(BluetoothDevice entity) {
        return BluetoothDeviceDto.builder()
                .name(entity.getName())
                .macAddress(entity.getMacAddress())
                .pushToken(entity.getPushToken())
                .build();
    }
}
