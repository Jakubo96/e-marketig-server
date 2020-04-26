package pl.poznan.put.emarketing.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BluetoothDeviceDto {
    private String name;
    private String macAddress;
    private String pushToken;
}
