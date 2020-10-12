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
@Table(name = "VIDEO")
public class Video {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "NAME", unique = false, nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "format_id")
	private Format format;
	
	/**
	 * Default constructor.
	 */
	public Video() {
		// Nothing
	}
	
	/**
	 * Constructor used to create a video with a name.
	 * @param name the name of the video.
	 */
	public Video(String name) {
		this.setName(name);
	}
	
	/**
	 * Constructor used to create a video with a name and a format.
	 * @param name     the name of the video.
	 * @param format   the format of the video.
	 */
	public Video(String name, Format format) {
		this.setName(name);
		this.setFormat(format);
	}
	
	/**
	 * Getter of the Name attribute.
	 * @return the name of the video.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter of the Format attribute.
	 * @return the format of the video.
	 */
	public Format getFormat() {
		return format;
	}
	
	/**
	 * Setter of the Name attribute.
	 * @param name the new name of the video.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Setter of the Format attribute.
	 * @param format the new format of the video.
	 */
	public void setFormat(Format format) {
		this.format = format;
	}
	
	/**
	 * toString method of the Video Class.
	 */
	public String toString() {
		return name;
	}
	
}
