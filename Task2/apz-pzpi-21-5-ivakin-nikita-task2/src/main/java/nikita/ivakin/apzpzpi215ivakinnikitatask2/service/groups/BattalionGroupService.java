package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups.BattalionGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class BattalionGroupService {

    @Autowired
    private final BattalionGroupRepository battalionGroupRepository;

    @Transactional
    public void save(BattalionGroup battalionGroup) {
        battalionGroupRepository.save(battalionGroup);
    }
}

