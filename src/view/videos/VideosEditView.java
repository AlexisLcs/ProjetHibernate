package view.videos;

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

import controller.videos.VideosViewController;
import model.Format;
import model.Video;
import view.PopUpView;

public class VideosEditView {

	private JFrame frame;
	private JTextField txtName;
	private JComboBox<Format> cmbFormat;
	private VideosViewController VVC;
	private Video video = null;

	/**
	 * Constructor used to create a Videos Edit View.
	 * 
	 * @param video         a Video object.
	 * @param controllerView  the Video view controller.
	 */
	public VideosEditView(Video video, VideosViewController VVC) {
		this.video = video;
		this.VVC = VVC;
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

		JLabel lblEditCustomer = new JLabel("Modifier une vidéo");
		lblEditCustomer.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEditCustomer.setBounds(27, 23, 171, 23);
		panel.add(lblEditCustomer);

		JLabel lblFirstname = new JLabel("Nom :");
		lblFirstname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstname.setBounds(27, 60, 80, 14);
		panel.add(lblFirstname);

		JLabel lblLastname = new JLabel("Format :");
		lblLastname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLastname.setBounds(27, 85, 80, 14);
		panel.add(lblLastname);

		txtName = new JTextField();
		txtName.setBounds(112, 57, 143, 20);
		panel.add(txtName);
		txtName.setColumns(10);

		cmbFormat = new JComboBox<Format>();
		cmbFormat.setBounds(112, 83, 143, 20);
		panel.add(cmbFormat);

		JButton btnEdit = new JButton("Modifier");
		btnEdit.setBounds(163, 120, 112, 23);
		panel.add(btnEdit);

		VVC.updateFormatList(cmbFormat);
		VVC.updateInfos(video, txtName);
		cmbFormat.setSelectedItem(video.getFormat());

		frame.setVisible(true);

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtName.getText().equals("")) {
					VVC.modify(video, txtName.getText(),(Format)cmbFormat.getSelectedItem());
					VVC.fireTableDataChanged();
					frame.dispose();
				} else {
					new PopUpView("Vous devez compléter les champs obligatoires.");
				}
			}
		});
	}
}

