package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.SupplyRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders.LogisticCommanderService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests.SupplyRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/log-com")
@AllArgsConstructor
public class LogisticCommanderController {

    private final LogisticCommanderService logisticCommanderService;

    @GetMapping("/get/all-requests")
    public ResponseEntity<List<SupplyRequest>> getAllRequests(){
        List<SupplyRequest> supplyRequests = logisticCommanderService.getAllRequests();
        return new ResponseEntity<>(supplyRequests, HttpStatus.OK);
    }

    //Change status of request and sign who is going to do it and when
    @PostMapping("/take/execution-of-supply-request/{id}")
    public ResponseEntity<Boolean> takeExecutionOfRequest(@PathVariable Integer id){
        boolean result = logisticCommanderService.takeExecutionOfRequest(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/confirm/delivery-of-supply-request/{id}")
    public ResponseEntity<Boolean> confirmDeliveryOfRequest(@PathVariable Integer id){
        boolean result = logisticCommanderService.confirmExecutionOfRequest(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
