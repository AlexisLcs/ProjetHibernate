package view.companies;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.codecs.CodecsViewController;
import model.Company;
import view.PopUpView;

public class CompaniesAddView {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtAddress;
	private JComboBox<Company> cmbCompanies;
	private CodecsViewController CVC;


	/**
	 * Constructor used to create a Companies Add View.
	 * 
	 * @param CVC          the Company view controller.
	 * @param cmbCompanies the Combobox which contain companies (list).
	 */
	public CompaniesAddView(CodecsViewController CVC, JComboBox<Company> cmbCompanies) {
		this.CVC = CVC;
		this.cmbCompanies=cmbCompanies;
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
		frame.setBounds(100, 100, 330, 200);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);

		/******************** CONTENT ********************/

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblAddCustomer = new JLabel("Ajouter une entreprise");
		lblAddCustomer.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddCustomer.setBounds(27, 23, 220, 23);
		panel.add(lblAddCustomer);

		JLabel lblName = new JLabel("Nom :");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setBounds(27, 60, 80, 14);
		panel.add(lblName);

		JLabel lblAddress = new JLabel("Adresse :");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddress.setBounds(27, 85, 80, 14);
		panel.add(lblAddress);
		

		txtName = new JTextField();
		txtName.setBounds(112, 57, 143, 20);
		panel.add(txtName);
		txtName.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(112, 83, 143, 20);
		panel.add(txtAddress);
		txtAddress.setColumns(10);

		JButton btnAdd = new JButton("Ajouter");
		btnAdd.setBounds(163, 146, 112, 23);
		panel.add(btnAdd);

		frame.setVisible(true);

		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtName.getText().equals("") && !txtAddress.getText().equals("")) {
					CVC.addCompany(txtName.getText(),txtAddress.getText(),cmbCompanies);
					CVC.fireTableDataChanged();
					frame.dispose();
				} else {
					new PopUpView("Vous devez compléter les champs obligatoires.");
				}
			}
		});
	}
}
