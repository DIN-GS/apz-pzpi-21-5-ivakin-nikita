package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.SupplyCarDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyCar;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.ScanningDeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/device")
@AllArgsConstructor
public class ScanningDeviceController {

    private final ScanningDeviceService scanningDeviceService;


    @PostMapping("/scan-car")
    public ResponseEntity<Boolean> scanCar(@RequestBody SupplyCarDTO supplyCarDTO, @RequestParam Integer scanningDeviceId){
        boolean result = scanningDeviceService.scanCar(supplyCarDTO, scanningDeviceId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
