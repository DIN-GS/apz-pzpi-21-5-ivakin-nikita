package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.ScanningDeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/device")
@AllArgsConstructor
public class ScanningDeviceController {

    private final ScanningDeviceService scanningDeviceService;

    @PreAuthorize("hasAnyRole('SCANNING_DEVICE')")
    @PostMapping("/scan-car")
    public ResponseEntity<String> scanCar(@RequestParam Integer supplyCarId, @RequestParam Integer scanningDeviceId){
        String result = scanningDeviceService.scanCar(supplyCarId, scanningDeviceId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
