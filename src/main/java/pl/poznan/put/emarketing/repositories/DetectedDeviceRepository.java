package pl.poznan.put.emarketing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.poznan.put.emarketing.models.entities.DetectedDevice;

public interface DetectedDeviceRepository extends JpaRepository<DetectedDevice, Long> {
}
