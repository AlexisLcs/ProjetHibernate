package controller.players;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.table.AbstractTableModel;

import model.Codec;
import model.Player;

public class PlayerAddCodecViewController extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] head = { "Nom", "Fichier", "Format", "Propriétaire" };
	private ArrayList<Codec> list;
	private EntityManager em;

	/**
	 * Constructor used to initialize the Player Add Codec view controller.
	 */
	public PlayerAddCodecViewController(EntityManager em, Player player) {
		list = new ArrayList<Codec>();
		this.em = em;
		updateCodecs();
	}

	/**
	 * Method used to add all codecs to the table.
	 */
	@SuppressWarnings("unchecked")
	public void updateCodecs() {
		
		list.clear();
		
		String queryString = "from Codec";
		Query query = em.createQuery(queryString);
		List<Codec> results = query.getResultList();
		for (Codec codec :results) {
			list.add(codec);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Codec getCodec(String name) {
		String queryString = "from Codec";
		Query query = em.createQuery(queryString);
		List<Codec> results = query.getResultList();
		for (Codec codec :results) {
			if(codec.getName().equals(name))
				return codec;
		}
		
		return null;
	}

	@Override
	public int getColumnCount() {
		return head.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return head[columnIndex];
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return list.get(rowIndex).getName();
			
		case 1:
			return list.get(rowIndex).getFile();
			
		case 2:
			return list.get(rowIndex).getFormat().getName();
			
		case 3:
			if(list.get(rowIndex).getCompany() != null)
				return list.get(rowIndex).getCompany().getName();
			return "Open source";
			
		default:
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Method used to add a codec object to the table.
	 * 
	 * @param codec a Codec object.
	 */
	public void addValue(Codec codec) {
		list.add(codec);
	}
}
