package view.players;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controller.players.PlayersViewController;
import model.Player;
import view.ConfirmView;
import view.PopUpView;

public class PlayersView {
	//TODO VideoView Class
	private JPanel panel;
	private EntityManager em;
	/**
	 * Constructor used to create a Video View.
	 * 
	 * @param panel
	 * @param em the Entity Manager.
	 */
	public PlayersView(JPanel panel,EntityManager em) {
		this.panel = panel;
		this.em=em;
		initialize();
	}

	/**
	 * Method used to initialize the view.
	 */
	private void initialize() {		
		PlayersViewController PVC = new PlayersViewController(em);
		panel.setLayout(null);
		
		JTable tableFormat = new JTable(PVC);
		tableFormat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableFormat.setAutoCreateRowSorter(true);
		tableFormat.setBounds(10, 139, 651, 213);
		JScrollPane scrollPane_1 = new JScrollPane(tableFormat);
		scrollPane_1.setBounds(10, 45, 859, 377);
		panel.add(scrollPane_1);

		JButton btnAdd = new JButton("Ajouter un lecteur");
		btnAdd.setBounds(10, 11, 170, 23);
		panel.add(btnAdd);
		
		JButton btnEdit = new JButton("Consulter/Modifier un lecteur");
		btnEdit.setBounds(185, 11, 230, 23);
		panel.add(btnEdit);

		JButton btnDel = new JButton("\u2718");
		btnDel.setForeground(Color.RED);
		btnDel.setBounds(420, 11, 48, 23);
		panel.add(btnDel);
		
		JButton btnCodec = new JButton("Ajouter / Enlever des codecs");
		btnCodec.setBounds(475, 11, 230, 23);
		panel.add(btnCodec);
		
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PlayersAddView(PVC);
			}
		});
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableFormat.getSelectedRow() != -1) {
					Player playerToEdit = PVC.getPlayer(tableFormat.getValueAt(tableFormat.getSelectedRow(), 0).toString());
					new PlayersEditView(playerToEdit,PVC);
				} else {
					new PopUpView("Vous devez sélectionner un lecteur.");
				}
			}
		});
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableFormat.getSelectedRow() != -1) {
					Player playerToDel = PVC.getPlayer(tableFormat.getValueAt(tableFormat.getSelectedRow(), 0).toString());
					new ConfirmView(PVC, playerToDel);
				} else {
					new PopUpView("Vous devez sélectionner un lecteur.");
				}
			}
		});
		btnCodec.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableFormat.getSelectedRow() != -1) {
					Player playerToEdit = PVC.getPlayer(tableFormat.getValueAt(tableFormat.getSelectedRow(), 0).toString());
					new PlayersAddCodecView(em, playerToEdit, PVC);
				} else {
					new PopUpView("Vous devez sélectionner un lecteur.");
				}
			}
		});
	}
}