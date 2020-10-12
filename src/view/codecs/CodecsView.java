package view.codecs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controller.codecs.CodecsViewController;
import model.Codec;
import view.ConfirmView;
import view.PopUpView;

public class CodecsView {
	private JPanel panel;
	private EntityManager em;
	/**
	 * Constructor used to create a Codec View.
	 * 
	 * @param panel
	 */
	public CodecsView(JPanel panel,EntityManager em) {
		this.panel = panel;
		this.em=em;
		initialize();
	}

	/**
	 * Method used to initialize the view.
	 */
	private void initialize() {		
		CodecsViewController CVC = new CodecsViewController(em);
		panel.setLayout(null);
		
		JTable tableCodecs = new JTable(CVC);
		tableCodecs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCodecs.setAutoCreateRowSorter(true);
		tableCodecs.setBounds(10, 139, 651, 213);
		JScrollPane scrollPane_1 = new JScrollPane(tableCodecs);
		scrollPane_1.setBounds(10, 45, 859, 377);
		panel.add(scrollPane_1);

		JButton btnAdd = new JButton("Ajouter un codec");
		btnAdd.setBounds(10, 11, 170, 23);
		panel.add(btnAdd);
		
		JButton btnEdit = new JButton("Consulter/Modifier un codec");
		btnEdit.setBounds(185, 11, 230, 23);
		panel.add(btnEdit);

		JButton btnDel = new JButton("\u2718");
		btnDel.setForeground(Color.RED);
		btnDel.setBounds(420, 11, 48, 23);
		panel.add(btnDel);
		
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new CodecsAddView(CVC);
			}
		});
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableCodecs.getSelectedRow() != -1) {
					Codec codecToEdit = CVC.getCodec(tableCodecs.getValueAt(tableCodecs.getSelectedRow(), 0).toString());
					new CodecsEditView(codecToEdit,CVC);
				} else {
					new PopUpView("Vous devez sélectionner un codec.");
				}
			}
		});
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableCodecs.getSelectedRow() != -1) {
					Codec codecToDel = CVC.getCodec(tableCodecs.getValueAt(tableCodecs.getSelectedRow(), 0).toString());
					new ConfirmView(CVC, codecToDel);
				} else {
					new PopUpView("Vous devez sélectionner un codec.");
				}
			}
		});
	}
}
