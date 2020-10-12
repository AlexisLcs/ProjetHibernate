package controller.codecs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import model.Codec;
import model.Company;
import model.Format;

public class CodecsViewController extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] head = { "Nom", "Fichier", "Format", "Entreprise propriétaire"};
	private ArrayList<Codec> list;
	private EntityManager em;

	/**
	 * Constructor used to initialize the Codecs view controller.
	 * @param em 
	 */
	public CodecsViewController(EntityManager em) {
		list = new ArrayList<Codec>();
		this.em=em;
		getCodecs();
	}

	/**
	 * Method used to add all codecs to the table.
	 */
	@SuppressWarnings("unchecked")
	public void getCodecs() {
		
		list.clear();
		
		String queryString = "from Codec";
		Query query = em.createQuery(queryString);
		List<Codec> results = query.getResultList();
		for (Codec codec :results) {
			list.add(codec);
		}
	}
	public Codec getCodec(String Name) {
		for(Codec codec : list) {
			if(codec.getName().equals(Name)) {
				return codec;
			}
		}
		return null;
	}
	
	
	/**
	 * get the number of Columns
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
	 * fill the table at row and column index
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return list.get(rowIndex).getName();
		case 1:
			return list.get(rowIndex).getFile();
		case 2:
			return list.get(rowIndex).getFormat();
		case 3:
			if(list.get(rowIndex).getCompany()!=null) {
				return list.get(rowIndex).getCompany().getName();
			}
			return "Open source";
		default:
			throw new IllegalArgumentException();
		}
	}

	
	/**
	 * Method used to add a Codec object to the table.
	 * 
	 * @param codec a Codec object.
	 */
	public void addValue(Codec codec) {
		list.add(codec);
	}

	
	/**
	 * Method used to update infos of a codec in an edit view.
	 * 
	 * @param codec
	 * @param txtName
	 * @param txtFile
	 */
	public void updateInfos(Codec codec, JTextField txtName, JTextField txtFile) {
		txtName.setText(codec.getName());
		txtFile.setText(codec.getFile());
	}
	
	
	/**
	 * Update the format list in view
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
	 * Update the company list in View
	 * @param cBCompany
	 */
	@SuppressWarnings("unchecked")
	public void updateCompanyList(JComboBox<Company> cBCompany) {
		String queryString = "from Company";
		Query query = em.createQuery(queryString);
		List<Company> results = query.getResultList();
		for(Company format : results) {
			cBCompany.addItem(format);
		}
	}

	
	/**
	 * Method used to modify a codec.
	 * 
	 * @param codec
	 * @param name
	 * @param format
	 * @param company
	 */
	public void modify(Codec codec, String name, Format format, Company company) {
		codec.setName(name);
		codec.setFormat(format);
		codec.setCompany(company);
		em.getTransaction().begin();
		em.persist(codec);
		em.getTransaction().commit();
		getCodecs();
	}

	
	/**
	 * Method used to create a new product and to add it to the table.
	 * 
	 * @param codec_name
	 * @param file
	 * @param format
	 * @param company
	 */
	public void add(String codec_name, String file, Format format, Company company) {
		Codec newCodec = new Codec(codec_name, file, format, company);
		em.getTransaction().begin();
		em.persist(newCodec);
		em.getTransaction().commit();
		getCodecs();
	}


	/**
	 * Method used to delete a codec from the table.
	 * 
	 * @param codec
	 */
	public void delete(Codec codec) {
		em.getTransaction().begin();
		em.persist(codec);
		em.remove(codec);
		em.getTransaction().commit();
		getCodecs();
	}
	

	/**
	 * add a company to database
	 * 
	 * @param name
	 * @param address
	 * @param cmbCompanies
	 */
	public void addCompany(String name, String address, JComboBox<Company> cmbCompanies) {
		Company newCompany = new Company(name, address);
		cmbCompanies.addItem(newCompany);
		em.getTransaction().begin();
		em.persist(newCompany);
		em.getTransaction().commit();
	}
	
	
	/**
	 * delete a company from the database
	 * 
	 * @param company
	 * @param cmbCompanies
	 */
	public void delCompany(Company company, JComboBox<Company> cmbCompanies) {
		cmbCompanies.removeItem(company);
		em.getTransaction().begin();
		em.persist(company);
		em.remove(company);
		em.getTransaction().commit();
	}


}
