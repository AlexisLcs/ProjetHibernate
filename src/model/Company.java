package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "NAME", unique = false, nullable = false)
	private String name;
	
	@Column(name = "ADDRESS", unique = false, nullable = false)
	private String address;
	
	/**
	 * Default constructor.
	 */
	public Company() {
		// Nothing
	}
	
	/**
	 * Constructor used to create a company by using a name and an address.
	 * @param name     the name of the company.
	 * @param address  the address of the company.
	 */
	public Company(String name, String address) {
		this.setName(name);
		this.setAddress(address);
	}
	
	/**
	 * Getter of the Name attribute.
	 * @return the name of the company.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter of the Address attribute.
	 * @return the address of the company.
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Setter of the Name attribute.
	 * @param name the new name of the company.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Setter of the Address attribute.
	 * @param address the new address of the company.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * toString method of the Company Class.
	 */
	public String toString() {
		return name;
	}
}

