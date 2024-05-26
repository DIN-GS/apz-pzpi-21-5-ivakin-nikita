package nikita.ivakin.apzpzpi215ivakinnikitatask2.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.SupplyCarDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ScanningDevice;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyCar;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.ScanningDeviceNotFound;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.ScanningDeviceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ScanningDeviceService {

    private final ScanningDeviceRepository scanningDeviceRepository;
    private final SupplyCarService supplyCarService;
    private final CarCheckService carCheckService;

    public boolean scanCar(SupplyCarDTO supplyCarDTO, Integer scanningDeviceId) {
        SupplyCar supplyCar = supplyCarService.mapDtoToEntity(supplyCarDTO);
        ScanningDevice scanningDevice = findScanningDeviceById(scanningDeviceId);
        carCheckService.checkCar(supplyCar, scanningDevice);
        return true;
    }

    public ScanningDevice findScanningDeviceById(Integer id){
        Optional<ScanningDevice> tempScanningDevice = scanningDeviceRepository.findScanningDeviceById(id);
        if (tempScanningDevice.isPresent()) {
            return tempScanningDevice.get();
        } else {
            throw new ScanningDeviceNotFound("Scanning device with id " + id + " doesn't exist");
        }
    }

    @Transactional
    public ScanningDevice save(ScanningDevice scanningDevice) {
        return scanningDeviceRepository.save(scanningDevice);
    }


}

