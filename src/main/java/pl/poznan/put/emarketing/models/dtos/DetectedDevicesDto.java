package pl.poznan.put.emarketing.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetectedDevicesDto {
    @Valid
    @NotEmpty
    private List<DetectedDeviceDto> detectedDevices;
}
