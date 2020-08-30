package somapay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import somapay.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
