package nikita.ivakin.apzpzpi215ivakinnikitatask2.service;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.CarCheck;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ScanningDevice;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyCar;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.CarCheckException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.CarCheckRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CarCheckService {

    private final CarCheckRepository carCheckRepository;
    private final SupplyCarService supplyCarService;

    public void checkCar(SupplyCar supplyCar, ScanningDevice scanningDevice) {
        try {
            CarCheck carCheck = CarCheck.builder()
                    .supplyCar(supplyCar)
                    .scanningDevice(scanningDevice)
                    .localDateTime(LocalDateTime.now()).build();
            save(carCheck);
        } catch (Exception e) {
            throw new CarCheckException("Error in checking ca in car check service.");
        }

    }

    public List<CarCheck> findCarChecksForCar(Integer id) {
        SupplyCar supplyCar = supplyCarService.findSupplyCarById(id);
        List<CarCheck> carChecks = carCheckRepository.findBySupplyCar(supplyCar);
        return carChecks;
    }

    @Transactional
    public void save(CarCheck carCheck){
        carCheckRepository.save(carCheck);
    }


}
