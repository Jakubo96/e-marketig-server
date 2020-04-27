package pl.poznan.put.emarketing.models.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class DetectedDeviceDto {
    private String username;
    @NotBlank
    @Pattern(regexp = "^([0-9A-F]{2}):([0-9A-F]{2}):([0-9A-F]{2}):([0-9A-F]{2}):([0-9A-F]{2}):([0-9A-F]{2})$")
    private String mac;
    @NotBlank
    private String pushToken;
}
