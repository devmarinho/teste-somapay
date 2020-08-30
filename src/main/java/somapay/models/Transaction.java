package somapay.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private BigDecimal valueTransaction;
	@ManyToOne
	@JoinColumn(name="enteprise", referencedColumnName = "id")
	private Enterprise enterprise;
	@ManyToOne
	@JoinColumn(name="employee", referencedColumnName = "id")
	private Employee employee;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date;
	public Transaction() {}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public BigDecimal getValueTransaction() {
		return valueTransaction;
	}
	public void setValueTransaction(BigDecimal valueTransaction) {
		this.valueTransaction = valueTransaction;
	}
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean calculateTransaction(Employee employee,Enterprise enterprise, BigDecimal value ) {
		BigDecimal enterpriseCurrentAccount = enterprise.getCurrentAccount();
		Date dateTransaction = new Date();
		if(enterpriseCurrentAccount.compareTo(value) >= 0 && value.doubleValue() > 0) {
			// Aqui verifica se o saldo da empresa está disponível para transferencia total
			enterprise.setCurrentAccount(enterprise.getCurrentAccount().subtract(value));
			employee.setCurrentAccount(employee.getCurrentAccount().add(value));
			this.setEmployee(employee);
			this.setEnterprise(enterprise);
			this.setValueTransaction(value);
			this.setDate(dateTransaction);
			return true;
		}
		return false;
	}
	
}
