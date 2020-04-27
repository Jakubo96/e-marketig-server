package pl.poznan.put.emarketing.models.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class NotificationTimeDto {
    @NotNull
    private Date time;
}
