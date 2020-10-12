package controller.videos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import model.Format;
import model.Video;


public class VideosViewController extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] head = { "Nom", "Format"};
	private ArrayList<Video> list;
	private EntityManager em;

	/**
	 * Constructor used to initialize the Video view controller.
	 * @param em 
	 */
	public VideosViewController(EntityManager em) {
		list = new ArrayList<Video>();
		this.em=em;
		getVideos();
	}

	/**
	 * Method used to add all video to the table.
	 */
	@SuppressWarnings("unchecked")
	public void getVideos() {//TODO getProducts
		
		list.clear();
		
		String queryString = "from Video";
		Query query = em.createQuery(queryString);
		List<Video> results = query.getResultList();
		for (Video video :results) {
			list.add(video);
		}
	}
	
	
	/**
	 * get a video from his name
	 * @param Name
	 * @return
	 */
	public Video getVideo(String Name) {
		for(Video video : list) {
			if(video.getName().equals(Name)) {
				return video;
			}
		}
		return null;
	}

	/**
	 * get the number of columns
	 */
	@Override
	public int getColumnCount() {
		return head.length;
	}

	
	/**
	 * get the column name
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return head[columnIndex];
	}
	
	
	/**
	 * get the number of rows
	 */
	@Override
	public int getRowCount() {
		return list.size();
	}

	
	/**
	 * fill the table at row and column Index
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return list.get(rowIndex).getName();
		case 1:
			return list.get(rowIndex).getFormat().getName();
		default:
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Method used to add a Video object to the table.
	 * 
	 * @param video a Video object.
	 */
	public void addValue(Video video) {
		list.add(video);
	}

	
	/**
	 * Method used to update infos of a video in an edit view.
	 * @param video
	 * @param txtName
	 */
	public void updateInfos(Video video, JTextField txtName) {
		txtName.setText(video.getName());
	}
	
	/**
	 * update the FormatList of add and edit Views
	 * @param cBFormat
	 */
	@SuppressWarnings("unchecked")
	public void updateFormatList(JComboBox<Format> cBFormat) {
		String queryString = "from Format";
		Query query = em.createQuery(queryString);
		List<Format> results = query.getResultList();
		for(Format format : results) {
			cBFormat.addItem(format);
		}
	}
	

	/**
	 * Method used to modify a video.
	 * 
	 * @param video
	 * @param name
	 * @param format
	 */
	public void modify(Video video, String name, Format format) {
		video.setName(name);
		video.setFormat(format);
		em.getTransaction().begin();
		em.persist(video);
		em.getTransaction().commit();
		getVideos();
	}


	/**
	 * Method used to create a new video and to add it to the table.
	 * 
	 * @param video_name
	 * @param format
	 */
	public void add(String video_name, Format format) {
		Video newVideo = new Video(video_name, format);
		em.getTransaction().begin();
		em.persist(newVideo);
		em.getTransaction().commit();
		getVideos();
	}

	/**
	 * Method used to delete a video from the table.
	 * 
	 * @param video a Video object.
	 */
	public void delete(Video video) {
		em.getTransaction().begin();
		em.persist(video);
		em.remove(video);
		em.getTransaction().commit();
		getVideos();
	}
}
