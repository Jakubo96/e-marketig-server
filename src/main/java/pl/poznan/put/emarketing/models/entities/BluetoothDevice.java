package pl.poznan.put.emarketing.models.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@Builder
public class BluetoothDevice {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    private String name;
    private String macAddress;
    private String pushToken;
}
