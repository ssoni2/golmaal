/**
 * 
 */
package edu.buffalo.cse.ir.wikiindexer.indexer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import edu.buffalo.cse.ir.wikiindexer.IndexerConstants;

/**
 * @author nikhillo & ssoni2@buffalo.edu This class is used to write an index to
 *         the disk
 * 
 */
public class IndexWriter implements Writeable {
	private Integer writeindex = 0;
	private Properties props; // s-for reading file
	private INDEXFIELD keyField; // s-term or term id
	private INDEXFIELD valueField; // doc id
	private boolean isForward; // inverted - false , forward - true
	private int partitionNumber; // to identify current partition
	private Map<String, Postings> entries = new HashMap<String, Postings>();
	private Map<Integer, List<Integer>> linkentries = new HashMap<Integer, List<Integer>>();

	/**
	 * Constructor that assumes the underlying index is inverted Every index
	 * (inverted or forward), has a key field and the value field The key field
	 * is the field on which the postings are aggregated The value field is the
	 * field whose postings we are accumulating For term index for example: Key:
	 * Term (or term id) - referenced by TERM INDEXFIELD Value: Document (or
	 * document id) - referenced by LINK INDEXFIELD
	 * 
	 * @param props
	 *            : The Properties file
	 * @param keyField
	 *            : The index field that is the key for this index
	 * @param valueField
	 *            : The index field that is the value for this index
	 */
	public IndexWriter(Properties props, INDEXFIELD keyField,
			INDEXFIELD valueField) {
		this(props, keyField, valueField, false);
	}

	/**
	 * Overloaded constructor that allows specifying the index type as inverted
	 * or forward Every index (inverted or forward), has a key field and the
	 * value field The key field is the field on which the postings are
	 * aggregated The value field is the field whose postings we are
	 * accumulating For term index for example: Key: Term (or term id) -
	 * referenced by TERM INDEXFIELD Value: Document (or document id) -
	 * referenced by LINK INDEXFIELD
	 * 
	 * @param props
	 *            : The Properties file
	 * @param keyField
	 *            : The index field that is the key for this index
	 * @param valueField
	 *            : The index field that is the value for this index
	 * @param isForward
	 *            : true if the index is a forward index, false if inverted
	 */
	public IndexWriter(Properties props, INDEXFIELD keyField,
			INDEXFIELD valueField, boolean isForward) {
		// TODO: Implement this method
		this.props = props;
		this.keyField = keyField;
		this.valueField = valueField;
		this.isForward = isForward;
	}

	/**
	 * Method to make the writer self aware of the current partition it is
	 * handling Applicable only for distributed indexes.
	 * 
	 * @param pnum
	 *            : The partition number
	 */
	public void setPartitionNumber(int pnum) {
		// TODO: Optionally implement this method
		this.partitionNumber = pnum;
	}

	/**
	 * Method to add a given key - value mapping to the index
	 * 
	 * @param keyId
	 *            : The id for the key field, pre-converted
	 * @param valueId
	 *            : The id for the value field, pre-converted
	 * @param numOccurances
	 *            : Number of times the value field is referenced by the key
	 *            field. Ignore if a forward index
	 * @throws IndexerException
	 *             : If any exception occurs while indexing
	 */
	public void addToIndex(int keyId, int valueId, int numOccurances)
			throws IndexerException {
		// TODO: Implement this method
		if (isForward) {
			if (linkentries.containsKey(keyId)) {
				List pl = linkentries.get(keyId);
				if (pl != null) {
					if (!pl.contains(valueId)) {
						pl.add(valueId);
					}
				} else {
					pl = new ArrayList<Integer>();
					pl.add(valueId);
					linkentries.put(keyId, pl);
				}
			} else {
				List pl = new ArrayList<Integer>();
				pl.add(valueId);
				linkentries.put(keyId, pl);
			}
		} else {
			System.out.println("Sourabh - Only for Links");
			addToIndex(((Integer) keyId).toString(), valueId, numOccurances);
		}
	}

	/**
	 * Method to add a given key - value mapping to the index
	 * 
	 * @param keyId
	 *            : The id for the key field, pre-converted
	 * @param value
	 *            : The value for the value field
	 * @param numOccurances
	 *            : Number of times the value field is referenced by the key
	 *            field. Ignore if a forward index
	 * @throws IndexerException
	 *             : If any exception occurs while indexing
	 */
	public void addToIndex(int keyId, String value, int numOccurances)
			throws IndexerException {
		// TODO: Implement this method
		System.out.println("Sourabh - Not used ");
		int val = 0;
		try {
			val = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			throw new IndexerException("Value cant be string");
		}
		addToIndex(((Integer) keyId).toString(), val, numOccurances);
	}

	/**
	 * Method to add a given key - value mapping to the index
	 * 
	 * @param key
	 *            : The key for the key field
	 * @param valueId
	 *            : The id for the value field, pre-converted
	 * @param numOccurances
	 *            : Number of times the value field is referenced by the key
	 *            field. Ignore if a forward index
	 * @throws IndexerException
	 *             : If any exception occurs while indexing
	 */
	public void addToIndex(String key, int valueId, int numOccurances)
			throws IndexerException {
		// TODO: Implement this method
		if (entries.containsKey(key)) {
			Postings pl = entries.get(key);
			if (pl.posting.containsKey(valueId)) {
				pl.posting.put(((Integer) valueId), pl.posting.get(valueId)
						+ numOccurances);
			} else {
				pl.posting.put(((Integer) valueId), numOccurances);
			}
		} else {
			Postings pl = new Postings(((Integer) valueId),
					(Integer) numOccurances);
			entries.put(key, pl);

		}
		// termdoc.put(key, entries.get(key).posting.size());
	}

	/**
	 * Method to add a given key - value mapping to the index
	 * 
	 * @param key
	 *            : The key for the key field
	 * @param value
	 *            : The value for the value field
	 * @param numOccurances
	 *            : Number of times the value field is referenced by the key
	 *            field. Ignore if a forward index
	 * @throws IndexerException
	 *             : If any exception occurs while indexing
	 */
	public void addToIndex(String key, String value, int numOccurances)
			throws IndexerException {
		// TODO: Implement this method
		System.out.println("Sourabh - Not used 2 ");
		System.out.println("Sourabh - Not used ");
		int val = 0;
		try {
			val = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			throw new IndexerException("Value cant be string");
		}
		addToIndex(key, val, numOccurances);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.buffalo.cse.ir.wikiindexer.indexer.Writeable#writeToDisk()
	 */
	public void writeToDisk() throws IndexerException {
		// TODO Implement this method
		File f = new File(props.getProperty(IndexerConstants.TEMP_DIR),
				keyField.name());
		File parentFile = f.getParentFile();// confirming dir exist
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		if (!f.exists()) {
			f.mkdirs();
		}
		File f2 = new File(f, ((Integer) partitionNumber).toString());
		if (!f2.exists()) {
			f2.mkdirs();
		}
		// file name
		// - check
		File tempPathFile = new File(f2, writeindex.toString()); // file
																	// creation

		// writeindex++;
		//System.out.println("sob 213" + tempPathFile.getAbsolutePath());

		try {
			FileOutputStream fos = new FileOutputStream(tempPathFile);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			StringBuffer entriesString = new StringBuffer();
			if (isForward) {
				Map<Integer, List<Integer>> sortedEntries = new TreeMap<Integer, List<Integer>>();
				// sorting dictionary as per term id before storing
				sortedEntries.putAll(linkentries);

				for (Integer t : sortedEntries.keySet()) {
					entriesString.append(t.toString()
							+ sortedEntries.get(t).toString() + "\n");
				}
			} else {

				Map<String, Postings> sortedEntries = new TreeMap<String, Postings>();
				// sorting dictionary as per term id before storing
				sortedEntries.putAll(entries);

				for (String t : sortedEntries.keySet()) {
					entriesString.append(t
							+ sortedEntries.get(t).posting.toString() + "\n");
				}
			}

			osw.write(entriesString.toString());
			osw.close();
			writeToDiskTerm();

		} catch (Exception e) {
			e.printStackTrace();
			throw new IndexerException(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.buffalo.cse.ir.wikiindexer.indexer.Writeable#cleanUp()
	 */
	public void cleanUp() {
		// TODO Implement this method
		entries.clear(); // s-this will empty the list
		linkentries.clear();
	}

	/**
	 * @author Sourabh Soni ssoni2@buffalo.edu 
	 * Extra Method : This method store term and posting size
	 */
	public void writeToDiskTerm() throws IndexerException {
		// TODO Implement this method
		File f = new File(props.getProperty(IndexerConstants.TEMP_DIR),
				"termsize");
		File parentFile = f.getParentFile();// confirming dir exist
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		if (!f.exists()) {
			f.mkdirs();
		}
		File f2 = new File(f, keyField.name());
		if (!f2.exists()) {
			f2.mkdirs();
		}
		// file name
		// - check
		File tempPathFile = new File(f2, ((Integer) partitionNumber).toString()); // file
																	// creation

		// writeindex++;
		//System.out.println(tempPathFile.getAbsolutePath());

		try {
			FileOutputStream fos = new FileOutputStream(tempPathFile);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			StringBuffer entriesString = new StringBuffer();
			if (!isForward) {

				Map<String, Postings> sortedEntries = new TreeMap<String, Postings>();
				// sorting dictionary as per term id before storing
				sortedEntries.putAll(entries);

				for (String t : sortedEntries.keySet()) {
					int size = 0;
					for (Integer p : sortedEntries.get(t).posting.keySet()) {
						size = size + sortedEntries.get(t).posting.get(p);

					}
					entriesString.append(t + ":" + size + ";");
				}
			} else {
				Map<Integer, List<Integer>> sortedEntries = new TreeMap<Integer, List<Integer>>();
				// sorting dictionary as per term id before storing
				sortedEntries.putAll(linkentries);

				for (Integer t : sortedEntries.keySet()) {
					int size = sortedEntries.get(t).size();
					entriesString.append(t + ":" + size + ";");
				}
			}

			osw.write(entriesString.toString());
			osw.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new IndexerException(e.getMessage());
		}

	}

}
