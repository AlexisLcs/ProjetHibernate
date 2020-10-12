package controller.players;

import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

import model.Codec;
import model.Player;

public class PlayerEditCodecViewController extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] head = { "Codecs associés" };
	private Player player;
	private EntityManager em;
	
	/**
	 * Constructor used to initialize the associated codec of the Player Add Codec View.
	 */
	public PlayerEditCodecViewController(EntityManager em, Player player) {
		this.player = player;
		this.em = em;
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
		if(player.getCodecs() != null)
			return player.getCodecs().size();
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return player.getCodecs().get(rowIndex).getName();
			
		default:
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Method used to add a codec to the list of associated codecs.
	 * 
	 * @param codec a Codec object.
	 */
	public void addToCart(Codec codec) {
		player.getCodecs().add(codec);
		em.getTransaction().begin();
		em.persist(player);
		em.getTransaction().commit();
	}
	
	public void removeToCart(Codec codec) {
		player.getCodecs().remove(codec);
		em.getTransaction().begin();
		em.persist(player);
		em.getTransaction().commit();
	}
	
	public Boolean isInCart(Codec codec) {
		Boolean result=false;
		if(player.getCodecs() != null)
			for (Codec c : player.getCodecs()){
				if(c.getId().equals(codec.getId())){
					result=true;
				}
			}
		
		return result;
	}
}
