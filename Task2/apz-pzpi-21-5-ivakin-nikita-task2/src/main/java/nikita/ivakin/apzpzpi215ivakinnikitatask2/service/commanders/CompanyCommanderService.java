package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.CompanyCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.CompanyCommanderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CompanyCommanderService {

    @Autowired
    private final CompanyCommanderRepository companyCommanderRepository;

    @Transactional
    public void save(CompanyCommander companyCommander) {
        companyCommanderRepository.save(companyCommander);
    }
}

