package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CODEC")
public class Codec {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "NAME", unique = false, nullable = false)
	private String name;
	
	@Column(name = "FILE", unique = false, nullable = false)
	private String file;
	
	@ManyToOne
	@JoinColumn(name = "format_id")
	private Format format;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	/**
	 * Default constructor.
	 */
	public Codec() {
		// Nothing
	}
	
	/**
	 * Constructor used to create a codec with a name and a file path.
	 * @param name the name of the codec.
	 * @param file the path file of the codec.
	 */
	public Codec(String name, String file) {
		this.setName(name);
		this.setFile(file);
	}
	
	/**
	 * Constructor used to create a codec with a name, a path file, a format and a company.
	 * @param name      the name of the codec.
	 * @param file      the path file of the codec.
	 * @param format    the format object of the codec.
	 * @param company   the company object which own the codec.
	 */
	public Codec(String name, String file, Format format, Company company) {
		this.setName(name);
		this.setFile(file);
		this.setFormat(format);
		this.setCompany(company);
	}
	
	/**
	 * Getter of Id attribute.
	 * @return the ID of the codec.
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Getter of Name attribute.
	 * @return the name of the codec.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter of File attribute.
	 * @return the path file of the codec.
	 */
	public String getFile() {
		return file;
	}
	
	/**
	 * Getter of Format attribute.
	 * @return the format of the codec.
	 */
	public Format getFormat() {
		return format;
	}
	
	/**
	 * Getter of Company attribute.
	 * @return the company which own the codec. This attribute is null if there is no owner.
	 */
	public Company getCompany() {
		return company;
	}
	
	/**
	 * Setter used to set the name.
	 * @param name the name of the codec.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Setter used to set the path file.
	 * @param file the path file.
	 */
	public void setFile(String file) {
		this.file = file;
	}
	
	/**
	 * Setter uused to set the format of a codec.
	 * @param format the format of the codec.
	 */
	public void setFormat(Format format) {
		this.format = format;
	}
	
	/**
	 * Setter used to set the company which own the codec.
	 * @param company the company of the codec.
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	
	/**
	 * toString method of the Codec Class.
	 */
	public String toString() {
		return name;
	}
}
