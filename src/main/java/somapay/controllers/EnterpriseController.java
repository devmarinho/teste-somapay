package somapay.controllers;

import somapay.repositories.EmployeeRepository;
import somapay.repositories.EnterpriseRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import somapay.models.Enterprise;
import somapay.models.Employee;
@RestController
@RequestMapping(path="/enterprises", produces = "application/json")
public class EnterpriseController {
    @Autowired
    private EnterpriseRepository enterpriseRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping(path="/register")
    public Enterprise registerEnterprise(@RequestParam String name,@RequestParam BigDecimal currentAccount,@RequestParam String cnpj,@RequestParam String address){
			
        try {
			Enterprise enterprise = new Enterprise();
			enterprise.setCurrentAccount(currentAccount);
			enterprise.setName(name);
			enterprise.setAddress(address);
			enterprise.setCnpj(cnpj);
			return enterpriseRepository.save(enterprise); // 
			
		} catch (Exception e) {
			return null;
		}
		
    }
    @GetMapping(path="/balance/{id}")
    public Map<String, BigDecimal> getCurrentAccount (@PathVariable("id") long id){
  
    	Optional<Enterprise> enterprise = enterpriseRepository.findById(id);
    	if(enterprise.isPresent()) {
	    	Map<String, BigDecimal> map = new HashMap();
	    	map.put("currentAccount", enterprise.get().getCurrentAccount());
	        return map;
    	}
    	else return null;
    }
    @PostMapping(path="/register/{id}/employee/{id2}")
    public Employee registerEmployee(@PathVariable("id") long id,@PathVariable("id2") long id2) {
    	Optional<Enterprise> enterprise = enterpriseRepository.findById(id);
    	if(enterprise.isPresent()) {
    		Optional<Employee> employee = employeeRepository.findById(id2);
    		if(employee.isPresent()) {
    			List<Employee> employeesList =  enterprise.get().getEmployees();
    			employeesList.add(employee.get());
    			employee.get().setEnterprise(enterprise.get());
    			return employee.get();
    		}
    		//if there is no need to make a json, just return employee.get().getCurrentAccount() with changing the function return too
    	}
    		return null;
    	
    	
    }
    
}