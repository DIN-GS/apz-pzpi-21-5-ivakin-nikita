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

    /*@GetMapping("/get/demands-of-military-groups")
    public ResponseEntity<Boolean> getDemands() {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }*/

    @GetMapping("/get/all-requests")
    public ResponseEntity<List<SupplyRequest>> getAllRequests(){
        List<SupplyRequest> supplyRequests = logisticCommanderService.getAllRequests();
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    //Change status of request and sign who is going to do it and when
    @PostMapping("/take/execution-of-request")
    public ResponseEntity<Boolean> takeExecutionOfRequest(@RequestBody SupplyRequestDTO requestDTO){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/confirm/execution-of-request")
    public ResponseEntity<Boolean> confirmExecutionOfRequest(@RequestBody SupplyRequestDTO requestDTO){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
