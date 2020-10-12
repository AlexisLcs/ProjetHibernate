package view.codecs;

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
import model.Codec;
import model.Company;
import model.Format;
import view.PopUpView;
import view.companies.CompaniesAddView;

public class CodecsEditView {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtFile;
	private JComboBox<Format> cmbFormats;
	private JComboBox<Company> cmbCompanies;
	private CodecsViewController CVC;
	private Codec codec = null;

	/**
	 * Constructor used to create a Codec Edit View.
	 * 
	 * @param codec           a Codec object.
	 * @param controllerView  the  view controller.
	 */
	public CodecsEditView(Codec codec, CodecsViewController CVC) {
		this.codec = codec;
		this.CVC = CVC;
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
		frame.setBounds(100, 100, 480, 230);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);

		/******************** CONTENT ********************/

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblEditCustomer = new JLabel("Modifier un codec");
		lblEditCustomer.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEditCustomer.setBounds(27, 23, 171, 23);
		panel.add(lblEditCustomer);

		JLabel lblName = new JLabel("Nom :");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setBounds(27, 60, 80, 14);
		panel.add(lblName);

		JLabel lblFile = new JLabel("Fichier :");
		lblFile.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFile.setBounds(27, 80, 80, 14);
		panel.add(lblFile);
		
		JLabel lblFormat = new JLabel("Format :");
		lblFormat.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFormat.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFormat.setBounds(27, 106, 80, 14);
		panel.add(lblFormat);
		
		JLabel lblCompany = new JLabel("Entreprise :");
		lblCompany.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCompany.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCompany.setBounds(27, 138, 80, 14);
		panel.add(lblCompany);

		txtName = new JTextField();
		txtName.setBounds(112, 57, 143, 20);
		panel.add(txtName);
		txtName.setColumns(10);
		
		txtFile = new JTextField();
		txtFile.setBounds(112, 83, 143, 20);
		panel.add(txtFile);
		txtFile.setColumns(10);

		cmbFormats = new JComboBox<Format>();
		cmbFormats.setBounds(112, 109, 143, 20);
		panel.add(cmbFormats);
		
		cmbCompanies = new JComboBox<Company>();
		cmbCompanies.setBounds(112, 135, 143, 20);
		panel.add(cmbCompanies);

		JButton btnEdit = new JButton("Modifier");
		btnEdit.setBounds(163, 164, 112, 23);
		panel.add(btnEdit);
		
		JButton btnAddCompany = new JButton("Ajouter");
		btnAddCompany.setBounds(265, 135, 80, 20);
		panel.add(btnAddCompany);
		
		JButton btnDelCompany = new JButton("Supprimer");
		btnDelCompany.setBounds(355, 135, 100, 20);
		panel.add(btnDelCompany);

		CVC.updateFormatList(cmbFormats);
		CVC.updateCompanyList(cmbCompanies);
		CVC.updateInfos(codec, txtName,txtFile);
		cmbFormats.setSelectedItem(codec.getFormat());
		cmbCompanies.setSelectedItem(codec.getCompany());
		cmbCompanies.insertItemAt(null, 0);
		frame.setVisible(true);

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtName.getText().equals("") && !txtFile.getText().equals("")) {
					CVC.modify(codec, txtName.getText(),(Format)cmbFormats.getSelectedItem(),(Company)cmbCompanies.getSelectedItem());
					CVC.fireTableDataChanged();
					frame.dispose();
				} else {
					new PopUpView("Vous devez compléter les champs obligatoires.");
				}
			}
		});
		
		btnAddCompany.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new CompaniesAddView(CVC,cmbCompanies);
			}
		});
		
		btnDelCompany.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(cmbCompanies.getSelectedItem()!=null) {
					CVC.delCompany((Company)cmbCompanies.getSelectedItem(),cmbCompanies);
				}
			}
		});
	}
}


