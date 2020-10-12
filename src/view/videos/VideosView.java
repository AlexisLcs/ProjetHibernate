package view.videos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controller.videos.VideosViewController;
import model.Video;
import view.ConfirmView;
import view.PopUpView;


public class VideosView {
	//TODO VideoView Class
	private JPanel panel;
	private EntityManager em;
	/**
	 * Constructor used to create a Video View.
	 * 
	 * @param panel
	 */
	public VideosView(JPanel panel,EntityManager em) {
		this.panel = panel;
		this.em=em;
		initialize();
	}

	/**
	 * Method used to initialize the view.
	 */
	private void initialize() {		
		VideosViewController VVC = new VideosViewController(em);
		panel.setLayout(null);
		
		JTable tableVideos = new JTable(VVC);
		tableVideos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableVideos.setAutoCreateRowSorter(true);
		tableVideos.setBounds(10, 139, 651, 213);
		JScrollPane scrollPane_1 = new JScrollPane(tableVideos);
		scrollPane_1.setBounds(10, 45, 859, 377);
		panel.add(scrollPane_1);

		JButton btnAdd = new JButton("Ajouter une vidéo");
		btnAdd.setBounds(10, 11, 170, 23);
		panel.add(btnAdd);
		
		JButton btnEdit = new JButton("Consulter/Modifier une vidéo");
		btnEdit.setBounds(185, 11, 230, 23);
		panel.add(btnEdit);

		JButton btnDel = new JButton("\u2718");
		btnDel.setForeground(Color.RED);
		btnDel.setBounds(420, 11, 48, 23);
		panel.add(btnDel);
		
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VideosAddView(VVC);
			}
		});
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableVideos.getSelectedRow() != -1) {
					Video videoToEdit = VVC.getVideo(tableVideos.getValueAt(tableVideos.getSelectedRow(), 0).toString());
					new VideosEditView(videoToEdit,VVC);
				} else {
					new PopUpView("Vous devez sélectionner une vidéo.");
				}
			}
		});
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableVideos.getSelectedRow() != -1) {
					Video videoToDel = VVC.getVideo(tableVideos.getValueAt(tableVideos.getSelectedRow(), 0).toString());
					new ConfirmView(VVC, videoToDel);
				} else {
					new PopUpView("Vous devez sélectionner une vidéo.");
				}
			}
		});
	}
	
}
