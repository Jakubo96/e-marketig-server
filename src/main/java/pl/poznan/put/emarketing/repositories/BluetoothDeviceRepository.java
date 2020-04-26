package pl.poznan.put.emarketing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.poznan.put.emarketing.models.entities.BluetoothDevice;

import java.util.List;
import java.util.Optional;

public interface BluetoothDeviceRepository extends JpaRepository<BluetoothDevice, Long> {
    List<BluetoothDevice> findAll();
    Optional<BluetoothDevice> findByMac(String mac);
}
