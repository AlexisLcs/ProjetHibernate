package view.players;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import controller.players.PlayerAddCodecViewController;
import controller.players.PlayerEditCodecViewController;
import controller.players.PlayersViewController;
import model.Codec;
import model.Player;
import view.PopUpView;

import javax.swing.ListSelectionModel;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayersAddCodecView {

	private JFrame frame;
	private PlayersViewController controllerView;
	private Player player = null;
	private JLabel lblAddProvision;
	private JTable tableSelect;
	private JTable tableCart;
	private PlayerAddCodecViewController SAVC;
	private PlayerEditCodecViewController SCEVC;
	private String state;
	
	
	/**
	 * Constructor used to create a Player Add View.
	 * 
	 * @param controllerView the Player view controller.
	 * @param player a Player object.
	 * @param em the Entity manager.
	 */
	public PlayersAddCodecView(EntityManager em, Player player, PlayersViewController controllerView) {
		SAVC = new PlayerAddCodecViewController(em, player);
		this.controllerView = controllerView;
		this.player = player;
		state = "edit";
		lblAddProvision = new JLabel("Ajouter / Enlever des codecs");
		SCEVC = new PlayerEditCodecViewController(em, player);
		initialize();
	}

	/**
	 * Method used to initialize the view.
	 */
	private void initialize() {
		
		/******************** WINDOW ********************/

		frame = new JFrame();
		frame.setType(Type.UTILITY);
		frame.setTitle("Kodek");
		frame.setResizable(false);
		frame.setBounds(100, 100, 660, 390);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);

		/******************** CONTENT ********************/

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		lblAddProvision.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddProvision.setBounds(27, 12, 280, 23);
		panel.add(lblAddProvision);

		JLabel lblProvider = new JLabel("Lecteur : " + player.getName());
		lblProvider.setHorizontalAlignment(SwingConstants.LEFT);
		lblProvider.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProvider.setBounds(27, 39, 200, 23);
		panel.add(lblProvider);
		
		JLabel lblInfos = new JLabel("Sélectionnez un codec à ajouter ou à enlever.");
		lblInfos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInfos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInfos.setBounds(27, 314, 400, 23);
		panel.add(lblInfos);
		
		tableSelect = new JTable(SAVC);
		tableSelect.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSelect.setAutoCreateRowSorter(true);
		tableSelect.setBounds(10, 139, 400, 213);
		JScrollPane scrollPane_1 = new JScrollPane(tableSelect);
		scrollPane_1.setBounds(27, 79, 400, 200);
		panel.add(scrollPane_1);
		
		tableCart = new JTable(SCEVC);
		tableCart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCart.setAutoCreateRowSorter(true);
		tableCart.setBounds(10, 128, 400, 213);
		JScrollPane scrollPane_2 = new JScrollPane(tableCart);
		scrollPane_2.setBounds(430, 79, 200, 200);
		panel.add(scrollPane_2);

		JButton btnSave = new JButton("Fermer");
		btnSave.setBounds(515, 314, 115, 23);
		panel.add(btnSave);
		
		JButton btnAdd = new JButton("Ajouter");
		btnAdd.setBounds(27, 284, 400, 23);
		panel.add(btnAdd);
		
		JButton btnRemove = new JButton("Enlever");
		btnRemove.setBounds(430, 284, 200, 23);
		panel.add(btnRemove);
		
		frame.setVisible(true);

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					controllerView.getPlayers();
					controllerView.fireTableDataChanged();
					frame.dispose();
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableSelect.getSelectedRow() != -1) {
					if(state.equals("edit") && !SCEVC.isInCart(SAVC.getCodec(tableSelect.getValueAt(tableSelect.getSelectedRow(), 0).toString()))) {
						Codec codecToAdd = SAVC.getCodec(tableSelect.getValueAt(tableSelect.getSelectedRow(), 0).toString());
						SCEVC.addToCart(codecToAdd);
						SCEVC.fireTableDataChanged();
					}
					else
						new PopUpView("Ce codec a déjà été associé.");
				} else {
					new PopUpView("Vous devez sélectionner un codec.");
				}
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableCart.getSelectedRow() != -1) {
					Codec codecToRemove = SAVC.getCodec(tableCart.getValueAt(tableCart.getSelectedRow(), 0).toString());
					SCEVC.removeToCart(codecToRemove);
					SCEVC.fireTableDataChanged();
				} else {
					new PopUpView("Vous devez sélectionner un codec.");
				}
			}
		});
	}
}
