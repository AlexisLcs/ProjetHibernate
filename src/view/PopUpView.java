package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PopUpView {

	private JFrame frame;
	private String message;

	/**
	 * Constructor used to create a PopUp View.
	 * 
	 * @param message the message to display.
	 */
	public PopUpView(String message) {
		this.message = message;
		initialize();
	}

	/**
	 * Method used to initialize the view.
	 */
	private void initialize() {

		/******************** WINDOW ********************/

		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setType(Type.UTILITY);
		frame.setTitle("Advert.");
		frame.setResizable(false);
		frame.setBounds(100, 100, 300, 100);
		frame.getContentPane().setLayout(null);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);

		/******************** CONTENT ********************/

		JLabel lblMessage = new JLabel(message);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
		lblMessage.setBounds(10, 11, 274, 23);
		frame.getContentPane().add(lblMessage);

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(195, 37, 89, 23);
		frame.getContentPane().add(btnOk);

		frame.setVisible(true);

		/* Events on click */
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}

}
