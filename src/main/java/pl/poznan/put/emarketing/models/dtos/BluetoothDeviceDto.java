package pl.poznan.put.emarketing.models.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class BluetoothDeviceDto {
    @NotBlank
    private String username;
    @NotBlank
    private String mac;
    @NotBlank
    private String pushToken;
}
