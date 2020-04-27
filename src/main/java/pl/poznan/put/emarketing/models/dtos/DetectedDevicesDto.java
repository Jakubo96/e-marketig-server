package pl.poznan.put.emarketing.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetectedDevicesDto {
    @Valid
    private List<DetectedDeviceDto> detectedDevices;
}
