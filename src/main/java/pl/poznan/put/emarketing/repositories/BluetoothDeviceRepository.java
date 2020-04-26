package pl.poznan.put.emarketing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.poznan.put.emarketing.models.entities.BluetoothDevice;

import java.util.Optional;

public interface BluetoothDeviceRepository extends JpaRepository<BluetoothDevice, Long> {
    Optional<BluetoothDevice> findByMac(String mac);

    void deleteByMac(String mac);
}
