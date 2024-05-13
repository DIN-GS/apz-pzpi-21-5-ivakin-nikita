package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BrigadeGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups.BrigadeGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class BrigadeGroupService {

    @Autowired
    private final BrigadeGroupRepository brigadeGroupRepository;

    @Transactional
    public void save(BrigadeGroup brigadeGroup) {
        brigadeGroupRepository.save(brigadeGroup);
    }
}
