package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.requests;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.auth.RegisterRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourcesRequestRepository extends JpaRepository<RegisterRequest, Integer> {

}
