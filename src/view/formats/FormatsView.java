package view.formats;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controller.formats.FormatsViewController;
import model.Format;
import view.ConfirmView;
import view.PopUpView;

public class FormatsView {
	//TODO VideoView Class
	private JPanel panel;
	private EntityManager em;
	/**
	 * Constructor used to create a format View.
	 * 
	 * @param panel
	 * @param em the Entity manager.
	 */
	public FormatsView(JPanel panel,EntityManager em) {
		this.panel = panel;
		this.em=em;
		initialize();
	}

	/**
	 * Method used to initialize the view.
	 */
	private void initialize() {		
		FormatsViewController FVC = new FormatsViewController(em);
		panel.setLayout(null);
		
		JTable tableFormat = new JTable(FVC);
		tableFormat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableFormat.setAutoCreateRowSorter(true);
		tableFormat.setBounds(10, 139, 651, 213);
		JScrollPane scrollPane_1 = new JScrollPane(tableFormat);
		scrollPane_1.setBounds(10, 45, 859, 377);
		panel.add(scrollPane_1);

		JButton btnAdd = new JButton("Ajouter un format");
		btnAdd.setBounds(10, 11, 170, 23);
		panel.add(btnAdd);
		
		JButton btnEdit = new JButton("Consulter/Modifier un format");
		btnEdit.setBounds(185, 11, 230, 23);
		panel.add(btnEdit);

		JButton btnDel = new JButton("\u2718");
		btnDel.setForeground(Color.RED);
		btnDel.setBounds(420, 11, 48, 23);
		panel.add(btnDel);
		
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new FormatsAddView(FVC);
			}
		});
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableFormat.getSelectedRow() != -1) {
					Format formatToEdit = FVC.getFormat(tableFormat.getValueAt(tableFormat.getSelectedRow(), 0).toString());
					new FormatsEditView(formatToEdit,FVC);
				} else {
					new PopUpView("Vous devez sélectionner un format.");
				}
			}
		});
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableFormat.getSelectedRow() != -1) {
					Format formatToDel = FVC.getFormat(tableFormat.getValueAt(tableFormat.getSelectedRow(), 0).toString());
					new ConfirmView(FVC, formatToDel);
				} else {
					new PopUpView("Vous devez sélectionner un format.");
				}
			}
		});
	}
}