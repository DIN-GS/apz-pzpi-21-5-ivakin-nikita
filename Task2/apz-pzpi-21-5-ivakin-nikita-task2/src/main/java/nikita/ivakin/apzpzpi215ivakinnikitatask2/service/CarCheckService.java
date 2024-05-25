package nikita.ivakin.apzpzpi215ivakinnikitatask2.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.CarCheckRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CarCheckService {

    private final CarCheckRepository carCheckRepository;
}
