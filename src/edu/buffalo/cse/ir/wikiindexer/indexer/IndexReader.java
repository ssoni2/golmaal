/**
 * 
 */
package edu.buffalo.cse.ir.wikiindexer.indexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import edu.buffalo.cse.ir.wikiindexer.IndexerConstants;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.EnglishStemmer;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.StopWordsRule;

/**
 * @author nikhillo This class is used to introspect a given index The
 *         expectation is the class should be able to read the index and all
 *         associated dictionaries.
 */
public class IndexReader {
	public SharedDictionary sd; // Document dictionary
	private Integer readindex = 0;
	private Properties props; // s-for reading file
	private INDEXFIELD keyField; // s-term or term id
	private INDEXFIELD valueField; // doc id
	private boolean isForward; // inverted - false , forward - true
	private int partitionNumber; // to identify current partition
	private Map<String, Postings> entries = new HashMap<String, Postings>();
	private Map<Integer, Integer> linkentries = new HashMap<Integer, Integer>();

	/**
	 * Constructor to create an instance
	 * 
	 * @param props
	 *            : The properties file
	 * @param field
	 *            : The index field whose index is to be read
	 */
	public IndexReader(Properties props, INDEXFIELD field) throws Exception {
		// TODO: Implement this method
		this.props = props;
		this.keyField = field;
		sd = new SharedDictionary(props, INDEXFIELD.LINK);
		readDict();
		//System.out.println(sd.entries.containsKey(-1907849355));
		//System.out.println("Sob 54 Loaded Dict" + sd.entries);

	}

	/**
	 * Method to get the total number of terms in the key dictionary
	 * 
	 * @return The total number of terms as above
	 */
	public int getTotalKeyTerms() throws Exception{
		// TODO: Implement this method
		if(keyField==INDEXFIELD.TERM){
		return readTermsize().size();} // term dict size
		else{
			return getsize().size();
		}
	}

	/**
	 * Method to get the total number of terms in the value dictionary
	 * 
	 * @return The total number of terms as above
	 */
	public int getTotalValueTerms() {
		// TODO: Implement this method
		return sd.getTotalTerms(); // doc dic sixe
	}

	/**
	 * Method to retrieve the postings list for a given dictionary term
	 * 
	 * @param key
	 *            : The dictionary term to be queried
	 * @return The postings list with the value term as the key and the number
	 *         of occurrences as value. An ordering is not expected on the map
	 */
	public Map<String, Integer> getPostings(String key) throws Exception {
		// TODO: Implement this method
		Map<String, Integer> result = new HashMap<String, Integer>();
		if(key==null || key==""){
			return result;
		}
		if(keyField==INDEXFIELD.LINK){
			key = key.trim().replaceAll(" ", "_");
		}else{
			TokenStream tstr = new TokenStream(key);
			StopWordsRule stop = new StopWordsRule();
			EnglishStemmer estem = new EnglishStemmer();
			stop.apply(tstr);
			estem.apply(tstr);
			//System.out.println(tstr.next());
			key=tstr.next();
		}
	
		
		
		//if(keyField==INDEXFIELD.LINK){
			//key = sd.
		//}
		
		
		
		File f = new File(props.getProperty(IndexerConstants.TEMP_DIR),
				keyField.name());
		File parentFile = f.getParentFile();// confirming dir exist
		if (!parentFile.exists()) {
			throw new Exception("Invalid Path 1");
		}
		if (!f.exists()) {
			throw new Exception("Invalid Path 2");
		}
		int temppartitionNumber = 0;
		if(keyField.equals(INDEXFIELD.TERM)){
			temppartitionNumber = Partitioner.getPartitionNumber(key);}
		File f2 = new File(f, ((Integer) temppartitionNumber).toString());
		if (!f2.exists()) {
			throw new Exception("Invalid Path 3");
		}
		// file name
		// - check
		File tempPathFile = new File(f2, readindex.toString()); // file
																// creation

		// readindex++;
		//System.out.println(tempPathFile.getAbsolutePath());
		// index
		// file name
		// - check

		// creation
		try {

			BufferedReader br = new BufferedReader(new FileReader(tempPathFile));
			String line = null;
			if (!keyField.name().equals(INDEXFIELD.LINK.name())) {// Check Ignore Case
				while ((line = br.readLine()) != null) {
					//System.out.println("Sob 126 IndRead Line: "+line);
					int index = line.indexOf('{');
					if(index==-1){
						continue;
					}
					String term = line.substring(0, index);
					if (!term.equals(key)) { // Check Ignore Case
						continue;
					}
					int endIndex = line.indexOf('}');
					String postS = line.substring(index + 1, endIndex);
					postS = postS.replace(',', ' ');
					String[] postA = postS.split("\\s*\\s");
					Postings postings = new Postings();
					for (int x = 0; x < postA.length; x++) {
						String[] arr = postA[x].split("=");
						// int docid = Integer.parseInt(arr[0]);
						int frq = Integer.parseInt(arr[1]);
						// postings.posting.put(docid, frq);
						result.put(arr[0], frq);
					}
				}
			} else {
				int doc = sd.lookup(key);
				while ((line = br.readLine()) != null) {

					int index = line.indexOf('[');
					Integer term = Integer.parseInt(line.substring(0, index));
					if (!(term.intValue() == doc)) {
						continue;
					}
					//System.out.println("sob 149 term: " + term);
					int endIndex = line.indexOf(']');
					String postS = line.substring(index + 1, endIndex);
					postS = postS.replace(',', ' ');
					String[] postA = postS.split("\\s*\\s");
					List<Integer> postings = new ArrayList<Integer>();
					//System.out.println("sob 155 post len: " + postA.length);
					for (int x = 0; x < postA.length; x++) {
						// String[] arr = postA[x].split("=");
						// int docid = Integer.parseInt(arr[0]);
						// int frq = Integer.parseInt(arr[1]);
						// postings.posting.put(docid, frq);
						result.put(postA[x], 1);
						// sd.entries.
					}
				}

			}
			return result;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Sourabh - read error");
			e.printStackTrace();
			throw e;
		}
		// return result;

	}

	/**
	 * Method to get the top k key terms from the given index The top here
	 * refers to the largest size of postings.
	 * 
	 * @param k
	 *            : The number of postings list requested
	 * @return An ordered collection of dictionary terms that satisfy the
	 *         requirement If k is more than the total size of the index, return
	 *         the full index and don't pad the collection. Return null in case
	 *         of an error or invalid inputs
	 */
	public Collection<String> getTopK(int k) throws Exception {
		// TODO: Implement this method
		Map<Integer, String> filter = new TreeMap<Integer, String>();
		Map<String, Integer> termsize = null;
		if(keyField==INDEXFIELD.TERM){
		 termsize = readTermsize();}
		else{
			termsize = getsize();
		}
		//System.out.println(termsize.toString());
		Set<String> s = termsize.keySet();
		Iterator<String> it = s.iterator();
		Sort sort = new Sort();
		sort.nElems = termsize.size();
		sort.theArray = new Integer[sort.nElems];
		sort.theArray2 = new String[sort.nElems];
		int ptr = 0;
		while (it.hasNext()) {
			String key = it.next();
			//System.out.print(termsize.get(key) + "-" + key + " ");
			sort.theArray[ptr] = termsize.get(key);
			sort.theArray2[ptr] = key;
			ptr++;
		}

		sort.quickSort();
		Collection<String> result = new HashSet<String>();
		// System.out.println(sort.theArray2.length+" "+sort.nElems);
		for (int i = 1; i < sort.nElems + 1 && i < k + 1; i++) {
			//System.out.println("Sob 185" + (sort.nElems - i) + " "
					//+ sort.theArray2[sort.nElems - i]);
			result.add(sort.theArray2[sort.nElems - i]);
		}
		return result;
	}

	/**
	 * Method to execute a boolean AND query on the index
	 * 
	 * @param terms
	 *            The terms to be queried on
	 * @return An ordered map containing the results of the query The key is the
	 *         value field of the dictionary and the value is the sum of
	 *         occurrences across the different postings. The value with the
	 *         highest cumulative count should be the first entry in the map.
	 */
	public Map<String, Integer> query(String... terms) throws Exception {
		// TODO: Implement this method (FOR A BONUS)
		
		Map<String, Integer> result = new TreeMap<String, Integer>();
		if(terms==null || terms.length==0){
			return result;
		}
		ArrayList<String> sterms = new ArrayList<String>();
		for(int i=0; i<terms.length;i++){
			TokenStream tstr = new TokenStream(terms[i]);
			StopWordsRule stop = new StopWordsRule();
			EnglishStemmer estem = new EnglishStemmer();
			stop.apply(tstr);
			estem.apply(tstr);
			//System.out.println(tstr.next());
			String term = tstr.next();
			if(term!=null&&term!=""){
				sterms.add(term);
			}
			//terms[i]=tstr.next();
			
		}
		
		
		if(sterms.size()==0){
			return result;
		}
		Map[] post = new Map[sterms.size()];
		for(int i=0; i<sterms.size();i++){
			post[i] = getPostings(sterms.get(i));
		}
		Iterator<String> it = post[0].keySet().iterator();
		while(it.hasNext()){
			Integer term = Integer.parseInt(it.next());
			//System.out.println(term);
			//term = ((Integer)sd.lookup(term)).toString();
			//System.out.println("sob 244 "+term);
			int size = (Integer)post[0].get(term.toString());
			int flag=1;
			for(int check=1; check < sterms.size(); check++){
				//System.out.println(post[check].toString());
				if(!post[check].containsKey(term.toString())){
					flag=0;
					break;
				}else{
					size = size + (Integer)post[check].get(term.toString());
					flag=1;
				}
			}
			if(flag==1){
				//System.out.println("Sob 269: "+sd.entries.containsKey(term)+term);
				result.put(sd.entries.get(term), size);
			}
		}
		Set<String> s = result.keySet();
		it = s.iterator();
		Sort sort = new Sort();
		sort.nElems = result.size();
		sort.theArray = new Integer[sort.nElems];
		sort.theArray2 = new String[sort.nElems];
		int ptr = 0;
		while (it.hasNext()) {
			String key = it.next();
			//System.out.print(termsize.get(key) + "-" + key + " ");
			sort.theArray[ptr] = result.get(key);
			sort.theArray2[ptr] = key;
			ptr++;
		}

		sort.quickSort();
		//Collection<String> result2 = new TreeSet<String>();
		// System.out.println(sort.theArray2.length+" "+sort.nElems);
		Map<String, Integer> result2 = new HashMap<String, Integer>();
		for (int i = 1; i < sort.nElems + 1 ; i++) {
			//System.out.println("Sob 185" + (sort.nElems - i) + " "
					//+ sort.theArray2[sort.nElems - i]);
			result2.put(sort.theArray2[sort.nElems - i],sort.theArray[sort.nElems - i]);
		}
		return result2; 
	}

	// my methods ssoni2@buffalo.edu

	public Map<String, Postings> read(String file, int size) throws Exception {
		File f = new File(props.getProperty(IndexerConstants.TEMP_DIR),
				keyField.name());
		File parentFile = f.getParentFile();// confirming dir exist
		if (!parentFile.exists()) {
			throw new Exception("Invalid Path 1");
		}
		if (!f.exists()) {
			throw new Exception("Invalid Path 2");
		}
		File f2 = new File(f, ((Integer) partitionNumber).toString());
		if (!f2.exists()) {
			throw new Exception("Invalid Path 3");
		}
		// file name
		// - check
		File tempPathFile = new File(f2, readindex.toString()); // file
																// creation

		// readindex++;
		//System.out.println(tempPathFile.getAbsolutePath());
		// index
		// file name
		// - check

		// creation
		Map<String, Postings> result = new TreeMap<String, Postings>();
		try {
			for (int i = 0; i < size; i++) {
				BufferedReader br = new BufferedReader(new FileReader(
						tempPathFile));
				String line = null;
				try {
					line = br.readLine();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("End of file" + file);
					return result;
				}
				int index = line.indexOf('{');
				String term = line.substring(0, index);
				int endIndex = line.indexOf('}');
				String postS = line.substring(index + 1, endIndex);
				postS = postS.replace(',', ' ');
				String[] postA = postS.split("\\s*\\s");
				Postings postings = new Postings();
				for (int x = 0; x < postA.length; x++) {
					String[] arr = postA[x].split("=");
					int docid = Integer.parseInt(arr[0]);
					int frq = Integer.parseInt(arr[1]);
					postings.posting.put(docid, frq);
				}

				result.put(term, postings);
			}
			return result;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Sourabh - read error");
			e.printStackTrace();
		}
		return result;
	}

	//
	/**
	 * @author Sourabh Soni ssoni2@buffalo.edu This method loads the dictionary
	 *         from disc into memory
	 */
	public void readDict() throws Exception {
		File f = new File(props.getProperty(IndexerConstants.TEMP_DIR),
				"Dictionary");
		File parentFile = f.getParentFile();// confirming dir exist
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		if (!f.exists()) {
			f.mkdirs();
		}
		File tempPathFile = new File(f, INDEXFIELD.LINK.name());

		//System.out.println(tempPathFile.getAbsolutePath());

		try {
			BufferedReader br = new BufferedReader(new FileReader(tempPathFile));
			String line = null;
			String line2 = null;
			while ((line = br.readLine())!=null && (line2 = br.readLine())!=null) {
				//System.out.println("no: "+line);
				//System.out.println("string: "+line2);
				//System.out.println();
				//String[] postA = line.split("=");
				// for (int x = 0; x < postA.length; x++) {
				// String[] arr = postA[x].split("=");
				sd.entries.put(Integer.parseInt(line), line2);
				// int docid = Integer.parseInt(arr[0]);
				// int frq = Integer.parseInt(arr[1]);
				// postings.posting.put(docid, frq);
				// }

				// result.put(term, postings);
				//line = br.readLine();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Sourabh - read error");
			e.printStackTrace();
		}

	}

	/**
	 * @author Sourabh Soni ssoni2@buffalo.edu Extra Method : This method read
	 *         and gets term and posting size list
	 */
	public Map<String, Integer> readTermsize() throws Exception {
		File f = new File(props.getProperty(IndexerConstants.TEMP_DIR),
				"termsize");
		File parentFile = f.getParentFile();// confirming dir exist
		if (!parentFile.exists()) {
			throw new Exception("Invalid Path 1");
		}
		if (!f.exists()) {
			throw new Exception("Invalid Path 2");
		}
		File f2 = new File(f, keyField.name());
		if (!f2.exists()) {
			throw new Exception("Invalid Path 3");
		}
		Map<String, Integer> result = new TreeMap<String, Integer>();
		for (int i = 0; i < Partitioner.getNumPartitions(); i++) {

			File tempPathFile = new File(f2, ((Integer) i).toString()); // file
			//System.out.println(tempPathFile.getAbsolutePath());
			if (!tempPathFile.exists()) {
				continue;
			}
			try {
				 
				// if (file.)
				BufferedReader br = new BufferedReader(new FileReader(tempPathFile));
				String line;
				while ((line = br.readLine()) != null) {
					String[] postA = line.split(";");
					for (int x = 0; x < postA.length; x++) {
						String[] arr = postA[x].split(":");
						result.put(arr[0], Integer.parseInt(arr[1]));
					}
				}
				if(keyField.equals(INDEXFIELD.LINK)){
					break;
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Sourabh - read error");
				throw e;
			}
		}
		return result;

	}
	
	public Map<String, Integer> getsize() throws Exception {
		// TODO: Implement this method
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		File f = new File(props.getProperty(IndexerConstants.TEMP_DIR),
				keyField.name());
		File parentFile = f.getParentFile();// confirming dir exist
		if (!parentFile.exists()) {
			throw new Exception("Invalid Path 1");
		}
		if (!f.exists()) {
			throw new Exception("Invalid Path 2");
		}
		int temppartitionNumber = 0;
		
		File f2 = new File(f, ((Integer) temppartitionNumber).toString());
		if (!f2.exists()) {
			throw new Exception("Invalid Path 3");
		}
		// file name
		// - check
		File tempPathFile = new File(f2, readindex.toString()); // file
																// creation

		// readindex++;
		//System.out.println(tempPathFile.getAbsolutePath());
		// index
		// file name
		// - check

		// creation
		try {

			BufferedReader br = new BufferedReader(new FileReader(tempPathFile));
			String line = null;
			if (!keyField.name().equals(INDEXFIELD.LINK.name())) {// Check Ignore Case
				while ((line = br.readLine()) != null) {
					//System.out.println("Sob 126 IndRead Line: "+line);
					int index = line.indexOf('{');
					if(index==-1){
						continue;
					}
					String term = line.substring(0, index);
					int endIndex = line.indexOf('}');
					String postS = line.substring(index + 1, endIndex);
					postS = postS.replace(',', ' ');
					String[] postA = postS.split("\\s*\\s");
					Postings postings = new Postings();
					int size =0;
					for (int x = 0; x < postA.length; x++) {
						String[] arr = postA[x].split("=");
						// int docid = Integer.parseInt(arr[0]);
						size = size + Integer.parseInt(arr[1]);
						// postings.posting.put(docid, frq);
						//result.put(arr[0], frq);
					}
					result.put(term, size);
				}
			} else {
				
				while ((line = br.readLine()) != null) {

					int index = line.indexOf('[');
					Integer term = Integer.parseInt(line.substring(0, index));
					
					//System.out.println("sob 149 term: " + term);
					int endIndex = line.indexOf(']');
					String postS = line.substring(index + 1, endIndex);
					postS = postS.replace(',', ' ');
					String[] postA = postS.split("\\s*\\s");
					List<Integer> postings = new ArrayList<Integer>();
					//System.out.println("sob 155 post len: " + postA.length);
					int size = 0;
					for (int x = 0; x < postA.length; x++) {
						// String[] arr = postA[x].split("=");
						// int docid = Integer.parseInt(arr[0]);
						// int frq = Integer.parseInt(arr[1]);
						// postings.posting.put(docid, frq);
						size = size+1;
						// sd.entries.
					}
					result.put(sd.entries.get(term), size);
				}

			}
			return result;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Sourabh - read error");
			e.printStackTrace();
			throw e;
		}
		// return result;

	}
}
