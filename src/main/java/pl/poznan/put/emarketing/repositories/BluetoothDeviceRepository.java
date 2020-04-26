package pl.poznan.put.emarketing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.poznan.put.emarketing.models.entities.BluetoothDevice;

public interface BluetoothDeviceRepository extends JpaRepository<BluetoothDevice, Long> {
}
