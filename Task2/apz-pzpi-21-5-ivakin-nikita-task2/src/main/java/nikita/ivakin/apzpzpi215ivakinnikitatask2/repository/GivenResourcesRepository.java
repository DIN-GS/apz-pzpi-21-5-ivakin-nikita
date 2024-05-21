package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.GivenResources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GivenResourcesRepository extends JpaRepository<GivenResources, Integer> {
}
