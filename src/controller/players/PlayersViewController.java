package controller.players;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import model.Codec;
import model.Player;

public class PlayersViewController extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] head = { "Nom", "Taille en mémoire", "Codec utilisables"};
	private ArrayList<Player> list;
	private EntityManager em;

	/**
	 * Constructor used to initialize the Player view controller.
	 * @param em 
	 */
	public PlayersViewController(EntityManager em) {
		list = new ArrayList<Player>();
		this.em=em;
		getPlayers();
	}

	/**
	 * Method used to add all players to the table.
	 */
	@SuppressWarnings("unchecked")
	public void getPlayers() {
		
		list.clear();
		
		String queryString = "from Player";
		Query query = em.createQuery(queryString);
		List<Player> results = query.getResultList();
		for (Player player :results) {
			list.add(player);
		}
	}
	
	public Player getPlayer(String Name) {
		for(Player player : list) {
			if(player.getName().equals(Name)) {
				return player;
			}
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
			return list.get(rowIndex).getSize();
		case 2:
			return findUsableCodec(list.get(rowIndex));
		default:
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Method used to add a player object to the table.
	 * 
	 * @param player a Player object.
	 */
	public void addValue(Player player) {
		list.add(player);
	}

	/**
	 * Method used to update infos of a player in an edit view.
	 * 
	 * @param player  a Player object.
	 * @param txtName JTextField which contains the name.
	 * @param txtSize JTextField which contains the size used by the player.
	 */
	public void updateInfos(Player player, JTextField txtName, JTextField txtSize) {
		txtName.setText(player.getName());
		txtSize.setText(player.getSize().toString());
	}
	

	/**
	 * Method used to modify a player.
	 * 
	 * @param player      a Player object to modify.
	 * @param name        the new name of this player.
	 * @param size        the size of this player.
	 */
	public void modify(Player player, String name, Integer size) {
		player.setName(name);
		player.setSize(size);
		em.getTransaction().begin();
		em.persist(player);
		em.getTransaction().commit();
		getPlayers();
	}

	/**
	 * Method used to create a new player and to add it to the table.
	 * 
	 * @param player_name   the name of the new player.
	 * @param size          the size of the new player.
	 */
	public void add(String player_name, Integer size) {
		Player newPlayer = new Player(player_name, size);
		em.getTransaction().begin();
		em.persist(newPlayer);
		em.getTransaction().commit();
		getPlayers();
	}

	/**
	 * Method used to delete a player from the table.
	 * 
	 * @param player a Player object.
	 */
	public void delete(Player playerToDel) {
		em.getTransaction().begin();
		em.persist(playerToDel);
		em.remove(playerToDel);
		em.getTransaction().commit();
		getPlayers();
	}
	
	@SuppressWarnings("unchecked")
	public String findUsableCodec(Player player) {
		Query query = em.createQuery("SELECT c FROM Player p INNER JOIN p.codecs c WHERE p=:player");
		query.setParameter("player", player);
		List<Codec> results = query.getResultList();
		
		if(results.size() > 0) {
			String codecList = "";
			ArrayList<String> used = new ArrayList<String>();
			for (Codec codec : results) {
				if(!used.contains(codec.getName())) {
					codecList += "[" + codec.getName() + "] ";
					used.add(codec.getName());
				}
			}
			
			return codecList;
		}
		
		return "None";
	}
}