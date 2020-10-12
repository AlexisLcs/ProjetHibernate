package view.formats;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.formats.FormatsViewController;
import view.PopUpView;

public class FormatsAddView {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtExtention;

	private FormatsViewController FVC;

	/**
	 * Constructor used to create a Format Add View.
	 * 
	 * @param controllerView the Format view controller.
	 */
	public FormatsAddView(FormatsViewController FVC) {
		this.FVC = FVC;
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
		frame.setBounds(100, 100, 330, 255);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);

		/******************** CONTENT ********************/

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblAddCustomer = new JLabel("Ajouter un format");
		lblAddCustomer.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddCustomer.setBounds(27, 23, 171, 23);
		panel.add(lblAddCustomer);

		JLabel lblFirstname = new JLabel("Nom :");
		lblFirstname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstname.setBounds(27, 60, 80, 14);
		panel.add(lblFirstname);

		JLabel lblLastname = new JLabel("Extention :");
		lblLastname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLastname.setBounds(27, 85, 80, 14);
		panel.add(lblLastname);

		txtName = new JTextField();
		txtName.setBounds(112, 57, 143, 20);
		panel.add(txtName);
		txtName.setColumns(10);

		txtExtention = new JTextField();
		txtExtention.setBounds(112, 83, 143, 20);
		panel.add(txtExtention);

		JButton btnAdd = new JButton("Ajouter");
		btnAdd.setBounds(163, 186, 112, 23);
		panel.add(btnAdd);

		frame.setVisible(true);

		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtName.getText().equals("") && !txtExtention.getText().equals("")) {
					FVC.add(txtName.getText(),txtExtention.getText());
					FVC.fireTableDataChanged();
					frame.dispose();
				} else {
					new PopUpView("Vous devez compléter les champs obligatoires.");
				}
			}
		});
	}
}