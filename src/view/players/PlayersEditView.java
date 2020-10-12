package view.players;

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

import controller.players.PlayersViewController;
import model.Player;

public class PlayersEditView {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtSize;
	private PlayersViewController PVC;
	private Player player = null;

	/**
	 * Constructor used to create a Player Edit View.
	 * 
	 * @param player         a Player object.
	 * @param controllerView  the player view controller.
	 */
	public PlayersEditView(Player player, PlayersViewController PVC) {
		this.player = player;
		this.PVC = PVC;
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

		JLabel lblEditCustomer = new JLabel("Modifier un lecteur");
		lblEditCustomer.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEditCustomer.setBounds(27, 23, 171, 23);
		panel.add(lblEditCustomer);

		JLabel lblFirstname = new JLabel("Nom :");
		lblFirstname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstname.setBounds(27, 60, 80, 14);
		panel.add(lblFirstname);

		JLabel lblLastname = new JLabel("Taille :");
		lblLastname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLastname.setBounds(27, 85, 80, 14);
		panel.add(lblLastname);

		txtName = new JTextField();
		txtName.setBounds(112, 57, 143, 20);
		panel.add(txtName);
		txtName.setColumns(10);

		txtSize = new JTextField();
		txtSize.setBounds(112, 83, 143, 20);
		panel.add(txtSize);

		JButton btnEdit = new JButton("Modifier");
		btnEdit.setBounds(163, 120, 112, 23);
		panel.add(btnEdit);

		PVC.updateInfos(player, txtName,txtSize);

		frame.setVisible(true);

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtName.getText().equals("") && !txtSize.getText().equals("") && txtSize.getText().matches("[0-9]*")) {
					PVC.modify(player, txtName.getText(),Integer.valueOf(txtSize.getText()));
					PVC.fireTableDataChanged();
					frame.dispose();
				} else {
					new view.PopUpView("Vous devez compléter les champs obligatoires.");
				}
			}
		});
	}
}

