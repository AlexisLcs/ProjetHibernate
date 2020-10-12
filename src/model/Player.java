package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYER")
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "NAME", unique = false, nullable = false)
	private String name;
	
	@Column(name = "SIZE", unique = false, nullable = false)
	private Integer size;
	
	@ManyToMany
	@JoinColumn(name = "codecs_id")
	private List<Codec> codecs = new ArrayList<Codec>();
	
	/**
	 * Default constructor.
	 */
	public Player() {
		// Nothing
	}
	
	/**
	 * Constructor used to create a player with a name and a size.
	 * @param name the name of the player.
	 * @param size the size of the player.
	 */
	public Player(String name, Integer size) {
		this.setName(name);
		this.setSize(size);
	}
	
	/**
	 * Constructor used to create a player with a name, a size and a list of codecs.
	 * @param name      the name of the player.
	 * @param size      the size of the player.
	 * @param codecs    the list of codecs associated to the player.
	 */
	public Player(String name, Integer size, List<Codec> codecs) {
		this.setName(name);
		this.setSize(size);
		this.setCodecs(codecs);
	}
	
	/**
	 * Getter of Id attribute.
	 * @return the id of the player.
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Getter of Name attribute.
	 * @return the name of the player.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter of the size attribute.
	 * @return the size of the player.
	 */
	public Integer getSize() {
		return size;
	}
	
	/**
	 * Getter of the codec list.
	 * @return the codec list associated to the player.
	 */
	public List<Codec> getCodecs() {
		return codecs;
	}
	
	/**
	 * Setter of the name attribute.
	 * @param name the new name of the player.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Setter of the size attribute.
	 * @param size the new size of the player.
	 */
	public void setSize(Integer size) {
		this.size = size;
	}
	
	/**
	 * Setter of the codec list.
	 * @param codecs the codec list associated to the player.
	 */
	public void setCodecs(List<Codec> codecs) {
		this.codecs = codecs;
	}
	
	/**
	 * toString method of the Player Class.
	 */
	public String toString() {
		return name;
	}
}
