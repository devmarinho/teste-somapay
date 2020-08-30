package somapay.controllers;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import somapay.models.Employee;
import somapay.models.Enterprise;
import somapay.repositories.EmployeeRepository;
import somapay.repositories.EnterpriseRepository;
@RestController
@RequestMapping(path="/employees", produces = "application/json")
public class EmployeeController {
    @Autowired
    private EnterpriseRepository enterpriseRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping(path="/register")
    public Employee registerEmployee(@RequestParam String name,@RequestParam(required=false) BigDecimal currentAccount,@RequestParam String cpf,@RequestParam String address,@RequestParam(required=false) Long id) {
    	try {
    	Employee employee = new Employee();
    	employee.setAddress(address);
    	employee.setCpf(cpf);
    	employee.setName(name);
    	if(currentAccount != null)	employee.setCurrentAccount(currentAccount);
    	
    	if(id!=null) {
    		Optional<Enterprise> enterprise = enterpriseRepository.findById(id);
    		if(enterprise.isPresent())
    		employee.setEnterprise(enterprise.get());
    	}
    	
    	return employeeRepository.save(employee);
    	}
    	catch(Exception ex) {
    		return null;
    	}
    }
    @GetMapping(path="/balance/{id}")
    public Map<String, BigDecimal> getCurrentAccount (@PathVariable("id") long id){
  
    	Optional<Employee> employee = employeeRepository.findById(id);
    	if(employee.isPresent()) {
	    	Map<String, BigDecimal> map = new HashMap();
	    	map.put("currentAccount", employee.get().getCurrentAccount()); 
	    	
	        return map;
    	}// if there is no need to make a json, just return employee.get().getCurrentAccount() with changing the function return too
    	else return null;
    }
}
