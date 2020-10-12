package controller.formats;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import model.Format;
import model.Player;

public class FormatsViewController extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] head = { "Nom", "Extention", "Lecteurs compatibles"};
	private ArrayList<Format> list;
	private EntityManager em;

	/**
	 * Constructor used to initialize the formats view controller.
	 * @param em 
	 */
	public FormatsViewController(EntityManager em) {
		list = new ArrayList<Format>();
		this.em=em;
		getFormats();
	}

	
	/**
	 * Method used to add all formats to the table.
	 */
	@SuppressWarnings("unchecked")
	public void getFormats() {
		
		list.clear();
		
		String queryString = "from Format";
		Query query = em.createQuery(queryString);
		List<Format> results = query.getResultList();
		for (Format format :results) {
			list.add(format);
		}
	}
	
	
	/**
	 * return a format by his name
	 * @param Name
	 * @return
	 */
	public Format getFormat(String Name) {
		for(Format format : list) {
			if(format.getName().equals(Name)) {
				return format;
			}
		}
		return null;
	}

	
	/**
	 * get the number of column
	 */
	@Override
	public int getColumnCount() {
		return head.length;
	}

	
	/**
	 * get the column name
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return head[columnIndex];
	}

	
	/**
	 * get the number of rows
	 */
	@Override
	public int getRowCount() {
		return list.size();
	}

	
	/**
	 * fill the table at row and column index
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return list.get(rowIndex).getName();
		case 1:
			return list.get(rowIndex).getExtension();
		case 2:
			return findCompatiblePlayers(list.get(rowIndex));
		default:
			throw new IllegalArgumentException();
		}
	}

	
	/**
	 * Method used to add a Format object to the table.
	 * 
	 * @param format a Format object.
	 */
	public void addValue(Format format) {
		list.add(format);
	}

	
	/**
	 * Method used to update infos of a format in an edit view.
	 * @param format
	 * @param txtName
	 * @param txtExtension
	 */
	public void updateInfos(Format format, JTextField txtName, JTextField txtExtension) {
		txtName.setText(format.getName());
		txtExtension.setText(format.getExtension());
	}
	

	/**
	 * Method used to modify a format.
	 * 
	 * @param format
	 * @param name
	 * @param extension
	 */
	public void modify(Format format, String name, String extension) {
		format.setName(name);
		format.setExtension(extension);
		em.getTransaction().begin();
		em.persist(format);
		em.getTransaction().commit();
		getFormats();
	}

	
	/**
	 * Method used to create a new format and to add it to the table.
	 * 
	 * @param format_name
	 * @param extension
	 */
	public void add(String format_name, String extension) {
		Format newFormat = new Format(format_name, extension);
		em.getTransaction().begin();
		em.persist(newFormat);
		em.getTransaction().commit();
		getFormats();
	}

	/**
	 * Method used to delete a format from the table.
	 * 
	 * @param format a Format object.
	 */
	public void delete(Format formatToDel) {
		em.getTransaction().begin();
		em.persist(formatToDel);
		em.remove(formatToDel);
		em.getTransaction().commit();
		getFormats();
	}
	
	
	/**
	 * find compatible players
	 * @param format
	 * @return string player list string
	 */
	@SuppressWarnings("unchecked")
	public String findCompatiblePlayers(Format format) {
		Query query = em.createQuery("SELECT p FROM Player p INNER JOIN p.codecs codec WHERE codec.format = :format");
		query.setParameter("format", format);
		List<Player> results = query.getResultList();
		
		if(results.size() > 0) {
			String playerList = "";
			for (Player player : results) {
				playerList += "[" + player.getName() + "] ";
			}
			
			return playerList;
		}
		
		return "None";
	}
}