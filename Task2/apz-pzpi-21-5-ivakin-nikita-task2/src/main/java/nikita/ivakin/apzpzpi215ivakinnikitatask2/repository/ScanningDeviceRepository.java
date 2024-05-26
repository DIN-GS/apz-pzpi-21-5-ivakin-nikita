package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ScanningDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScanningDeviceRepository extends JpaRepository<ScanningDevice, Integer> {
    Optional<ScanningDevice> findScanningDeviceById(Integer id);
}
