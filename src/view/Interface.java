package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import view.codecs.CodecsView;
import view.formats.FormatsView;
import view.players.PlayersView;
import view.videos.VideosView;

public class Interface {
	private JFrame frame;
	private EntityManager em;
	/**
	 * Create the application.
	 */
	public Interface() {
		EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("kodek");
		em=entityManagerFactory.createEntityManager();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		/******************** WINDOW ********************/
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setTitle("Kodek");
		
		/******************** CONTENT ********************/
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 884, 461);
		frame.getContentPane().add(tabbedPane);
		
		/*** VIDEOS PAGE ***/
		JPanel videosPanel = new JPanel();
		new VideosView(videosPanel,em);
		tabbedPane.addTab("Videos", null, videosPanel, null);
		
		/*** CODECS PAGE ***/
		JPanel codecsPanel = new JPanel();
		new CodecsView(codecsPanel,em);
		tabbedPane.addTab("Codecs", null, codecsPanel, null);
		
		/*** FORMATS PAGE ***/
		JPanel formatsPanel = new JPanel();
		new FormatsView(formatsPanel,em);
		tabbedPane.addTab("Formats", null, formatsPanel, null);
		
		/*** PLAYERS PAGE ***/
		JPanel playersPanel = new JPanel();
		new PlayersView(playersPanel,em);
		tabbedPane.addTab("Players", null, playersPanel, null);
		
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				new ConfirmView();
			}
		});
	}

}
