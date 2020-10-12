package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FORMAT")
public class Format {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "NAME", unique = false, nullable = false)
	private String name;
	
	@Column(name = "EXTENSION", unique = false, nullable = false)
	private String extension;
	
	/**
	 * Default constructor.
	 */
	public Format() {
		// Nothing
	}
	
	/**
	 * Constructor used to create a format by using a name and an extension.
	 * @param name       the name of the format.
	 * @param extension  the extension of the format.
	 */
	public Format(String name, String extension) {
		this.setName(name);
		this.setExtension(extension);
	}
	
	/**
	 * Getter of the Id attribute.
	 * @return the id of the format.
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Getter of the Name attribute.
	 * @return the name of the format.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter of the extension attribute.
	 * @return the extension of the format.
	 */
	public String getExtension() {
		return extension;
	}
	
	/**
	 * Setter of the Name attribute.
	 * @param name the new name of the format.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Setter of the Extension attribute.
	 * @param extension the new extension of the format.
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	/**
	 * toString method of the Format Class.
	 */
	public String toString() {
		return name;
	}
}
