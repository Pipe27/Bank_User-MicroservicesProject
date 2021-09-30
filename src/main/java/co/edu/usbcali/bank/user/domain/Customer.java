package co.edu.usbcali.bank.user.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */
@Entity
@Table(name = "customer", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cust_id", unique = true, nullable = false)
	@NotNull
	private Integer custId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doty_id")
	@NotNull
	private DocumentType documentType;

	@Column(name = "address", nullable = false)
	@NotNull
	@Size(min = 3, max = 100)
	private String address;

	@Column(name = "email", nullable = false)
	@NotNull
	@Email
	private String email;

	@Column(name = "enable", nullable = false)
	@NotNull
	@Size(min = 1, max = 1)
	private String enable;

	@Column(name = "name", nullable = false)
	@NotNull
	@Size(min = 4, max = 100)
	private String name;

	@Column(name = "phone", nullable = false)
	@NotNull
	@Size(min = 7, max = 15)
	private String phone;
	
	@Column(name = "token")
	@NotNull
	@Size(min = 1, max = 100)
	private String token;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private List<Account> accounts = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private List<RegisteredAccount> registeredAccounts = new ArrayList<>();

}