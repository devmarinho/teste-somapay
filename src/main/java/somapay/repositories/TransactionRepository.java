package somapay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import somapay.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
