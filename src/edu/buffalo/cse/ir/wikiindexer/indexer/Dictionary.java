/**
 * 
 */
package edu.buffalo.cse.ir.wikiindexer.indexer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import edu.buffalo.cse.ir.wikiindexer.IndexerConstants;

/**
 * @author nikhillo An abstract class that represents a dictionary object for a
 *         given index
 */
public abstract class Dictionary implements Writeable {
	public Properties props;// created
	public INDEXFIELD field;// created
	public Map<Integer, String> entries = new HashMap<Integer, String>();// created entries

	public Dictionary(Properties props, INDEXFIELD field) {
		// TODO Implement this method
		this.props = props;
		this.field = field;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.buffalo.cse.ir.wikiindexer.indexer.Writeable#writeToDisk()
	 */

	public void writeToDisk() throws IndexerException {
		// TODO Implement this method
		File f = new File(props.getProperty(IndexerConstants.TEMP_DIR),
				"Dictionary");
		File parentFile = f.getParentFile();// confirming dir exist
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		if (!f.exists()) {
			f.mkdirs();
		}
		File tempPathFile = new File(f, field.name()); 
		//System.out.println(tempPathFile.getAbsolutePath());
		try {
			FileOutputStream fos = new FileOutputStream(tempPathFile);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");

			//Map sortedEntries = new TreeMap();
			// sorting dictionary as per term id before storing
			//sortedEntries.putAll(entries);
			StringBuffer entriesString = new StringBuffer();

			for (Integer t : entries.keySet()) {
				entriesString.append(t.toString()+"\n"
						+ entries.get(t) + "\n");
			}

			osw.write(entriesString.toString());
			osw.close();
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
		entries.clear();
	}

	/**
	 * Method to check if the given value exists in the dictionary or not Unlike
	 * the subclassed lookup methods, it only checks if the value exists and
	 * does not change the underlying data structure
	 * 
	 * @param value
	 *            : The value to be looked up
	 * @return true if found, false otherwise
	 */
	public boolean exists(String value) {
		// TODO Implement this method
		return entries.containsValue(value); // check test case for lower case

	}

	/**
	 * MEthod to lookup a given string from the dictionary. The query string can
	 * be an exact match or have wild cards (* and ?) Must be implemented ONLY
	 * AS A BONUS
	 * 
	 * @param queryStr
	 *            : The query string to be searched
	 * @return A collection of ordered strings enumerating all matches if found
	 *         null if no match is found
	 */
	public Collection<String> query(String queryStr) {
		// TODO: Implement this method (FOR A BONUS)
		Collection<String> result = new TreeSet<String>();
		if (entries.containsValue(queryStr)) {
			result.add(queryStr);
		} else if (queryStr.contains("*") || queryStr.contains("?")) {
			//System.out.println("sob");
			queryStr = queryStr.replace('?', '.');
			queryStr = queryStr.replace("*", ".*");
			//System.out.println(entries);
			//System.out.println(entries.keySet());

			Collection<String> key = entries.values();
			Iterator<String> it = key.iterator();
			while (it.hasNext()) {
				String check = it.next();
				//System.out.println(check);
				if (check.matches(queryStr)) {
					result.add(check);
				}
			}

		}
		if (result.isEmpty()) {
			result = null;
		}
		//System.out.println(result);
		return result;
	}

	/**
	 * Method to get the total number of terms in the dictionary
	 * 
	 * @return The size of the dictionary
	 */
	public int getTotalTerms() {
		// TODO: Implement this method
		return entries.size();
	}
}
