package somapay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import somapay.models.Enterprise;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

}
