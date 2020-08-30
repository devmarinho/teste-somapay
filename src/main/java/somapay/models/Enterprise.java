package somapay.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Enterprise {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String address;
	@Column(name="cnpj", nullable=false, length = 19)
	private String cnpj;
	private BigDecimal currentAccount;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy="enterprise")
	private List<Employee> employees = new ArrayList<Employee>();
	@JsonIgnore
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy="enterprise")
	private List<Transaction> transactions = new ArrayList<Transaction>();
	
	
	public Enterprise() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public BigDecimal getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(BigDecimal currentAccount) {
		this.currentAccount = currentAccount;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
}
