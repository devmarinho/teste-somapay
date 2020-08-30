package somapay.controllers;
import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import somapay.models.Employee;
import somapay.models.Enterprise;
import somapay.models.Transaction;
import somapay.repositories.EmployeeRepository;
import somapay.repositories.EnterpriseRepository;
import somapay.repositories.TransactionRepository;
@RestController
@RequestMapping(path="/transactions", produces = "application/json")
public class TransactionController {
	 	@Autowired
	    private EnterpriseRepository enterpriseRepository;
	    @Autowired
	    private EmployeeRepository employeeRepository;
	    @Autowired
	    private TransactionRepository transactionRepository;

	    @PostMapping(path="/register")
	    public Transaction registerTransaction(@RequestParam long idEnterprise,@RequestParam long idEmployee,@RequestParam BigDecimal value) {
	    	
	    	Optional<Enterprise> enterprise = enterpriseRepository.findById(idEnterprise);
	    	if(enterprise.isPresent()) {
	    		Optional<Employee> employee = employeeRepository.findById(idEmployee);
	    		if(employee.isPresent()) {
	    			Transaction transaction = new Transaction();
	    			if(transaction.calculateTransaction(employee.get(), enterprise.get(), value))
	    			return transactionRepository.save(transaction);
	    			
	    		}
	    	}
	    	return null;	
	    }
}
