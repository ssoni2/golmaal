package edu.buffalo.cse.ir.wikiindexer.indexer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import edu.buffalo.cse.ir.wikiindexer.FileUtil;
import edu.buffalo.cse.ir.wikiindexer.IndexerConstants;
import edu.buffalo.cse.ir.wikiindexer.IndexerConstants.RequiredConstant;
import edu.buffalo.cse.ir.wikiindexer.indexer.INDEXFIELD;
import edu.buffalo.cse.ir.wikiindexer.indexer.IndexWriter;

class MyThread extends Thread {
	public int partitionno;
	public Properties properties;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {IndexWriter writer=null;
			for (int s = 1; s < 100000; s++) {

				writer = new IndexWriter(properties,
						INDEXFIELD.TERM, INDEXFIELD.LINK, false);
				writer.setPartitionNumber(partitionno);
				char c = (char) (96 + partitionno);
				for (int k = 1; k < 26; k++) {
					writer.addToIndex(c + "a" + (char) (96 + k), 1, s%6);
					writer.addToIndex(c + "b" + (char) (96 + k), 2, s%4);
				}
				if (c == 's') {
					writer.addToIndex(c + "ourabh", s%4, 7);
					writer.addToIndex(c + "oni", s%2, 5);
				}

				
					}
			writer.writeToDisk();
			System.out.println("End"+partitionno+partitionno+partitionno+partitionno+partitionno+partitionno+partitionno+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					
		} catch (IndexerException e) {
			// TODO Auto-generated catch block
			System.out.println("Line 46 Sourabh OMG");
			e.printStackTrace();
		}
	}
}

public class Starter {
	private long[] theArray; // ref to array theArray
	private int nElems;

	public void quickSort() {
		recQuickSort(0, nElems - 1);
	}

	public void recQuickSort(int left, int right) {
		if (right - left <= 0) // if size <= 1,
			return; // already sorted
		else // size is 2 or larger
		{
			long pivot = theArray[right]; // rightmost item // partition range
			int partition = partitionIt(left, right, pivot);
			recQuickSort(left, partition - 1); // sort left side
			recQuickSort(partition + 1, right); // sort right side
		}
	}

	public int partitionIt(int left, int right, long pivot) {
		int leftPtr = left - 1; // left (after ++)
		int rightPtr = right; // right-1 (after --)
		while (true) { // find bigger item
			while (theArray[++leftPtr] < pivot)
				; // (nop) // find smaller item
			while (rightPtr > 0 && theArray[--rightPtr] > pivot)
				; // (nop)

			if (leftPtr >= rightPtr) // if pointers cross,
				break; // partition done
			else
				// not crossed, so
				swap(leftPtr, rightPtr); // swap elements
		} // end while(true)
		swap(leftPtr, right); // restore pivot
		return leftPtr; // return pivot location
	}

	public void swap(int dex1, int dex2) // swap two elements
	{
		long temp = theArray[dex1]; // A into temp
		theArray[dex1] = theArray[dex2]; // B into A
		theArray[dex2] = temp; // temp into B

	}

	public static void test(int... var) {
		for (int i = 0; i < var.length; i++) {
			System.out.println(var[i]);
		}
	}

	public static void write(Properties properties) throws Exception {

		for (int i = 1; i < Partitioner.getNumPartitions(); i++) {
			MyThread mt = new MyThread();
			mt.partitionno = i;
			mt.properties = properties;
			mt.start();
		}
	}

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		Properties properties = loadProperties("properties.config");
		if (properties == null) {
			System.err
					.println("Error while loading the Properties file. Please check the messages above and try again");
			System.exit(2);
		}/*
		System.out.println("Start******************************************************");
		//write(properties);
System.out.println("End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		SharedDictionary sd = new SharedDictionary(properties, INDEXFIELD.LINK);

		for (int i = 1; i < 10; i++) {
			sd.lookup("Doc " + i);
		}*/
		//sd.writeToDisk();
		IndexReader ir = new IndexReader(properties, INDEXFIELD.TERM);
		//System.out
				//.println("-----------------------------------------------------"+ir.sd.entries.size());
		//System.out.println("sob 103 " + ir.getPostings("waterfront"));
		//System.out.println("sob 103 " + ir.getPostings("residential"));
		//System.out.println("Sob 104 " + ir.getTopK(2).toString());
		System.out.println("Sob 110 " + ir.query("waterfront", "residential","the").toString());
		//System.out.println("Sob 140 Term " + ir.getTotalKeyTerms());
		//System.out.println("Sob 140 Doc " + ir.getTotalValueTerms());
		// writer.addToIndex(sd.lookup("Doc "+1), 2, 0);
		// writer.addToIndex(sd.lookup("Doc "+5), 2, 0);

		/*IndexWriter writer = new IndexWriter(properties, INDEXFIELD.TERM,
				INDEXFIELD.LINK, false);
		writer.setPartitionNumber(Partitioner.getPartitionNumber("Sourabh"));
		writer.addToIndex("Hi", 1, 1);
		writer.addToIndex("Sourabh", 1, 1);
		writer.addToIndex("Soni", 2, 1);
		writer.addToIndex("Soni", 2, 1);
		writer.addToIndex("Bye", 2, 1);
		writer.addToIndex("Sourabh", 2, 1);
		writer.addToIndex("Sourabh", 2, 1);
		// writer.writeToDisk();
		// System.out.println("Added ");

		// Map<String, Postings> read = ir.read("", 1);
		// System.out.println(read.keySet().toString());

		test(2, 3, 4);

		Starter s = new Starter();
		s.nElems = 4;
		s.theArray = new long[s.nElems];
		s.theArray[0] = 3;
		s.theArray[1] = 2;
		s.theArray[2] = 4;
		s.theArray[3] = 1;
		System.out.println(s.theArray[3]);
		s.quickSort();
		System.out.println(s.theArray[3]);
		// TODO Auto-generated method stub
		// Properties properties = loadProperties("properties.config");
		Map<String, Integer> filter = new LinkedHashMap<String, Integer>();
		filter.put("Sob", 20);
		filter.put("soni", 10);

		System.out.println(filter);

		String pattern = "*oni";
		pattern = pattern.replace('?', '.');
		pattern = pattern.replace("*", ".*");
		System.out.println(pattern);
		String text = "soni";
		System.out.println(text.matches(pattern));

		String term = "&bc";
		term = term.toLowerCase();
		int i = term.charAt(0) - 96;
		if (!(i > 0 && i < 27))
			i = 27;
		System.out.println(i);*/
		System.out.println("Time: "+(System.currentTimeMillis()-start));

	}

	private static Properties loadProperties(String filename) {

		try {
			Properties props = FileUtil.loadProperties(filename);

			if (validateProps(props)) {
				return props;
			} else {
				System.err
						.println("Some properties were either not loaded or recognized. Please refer to the manual for more details");
				return null;
			}
		} catch (FileNotFoundException e) {
			System.err.println("Unable to open or load the specified file: "
					+ filename);
		} catch (IOException e) {
			System.err
					.println("Error while reading properties from the specified file: "
							+ filename);
		}

		return null;
	}

	private static boolean validateProps(Properties props) {
		/* Validate size */
		if (props != null
				&& props.entrySet().size() == IndexerConstants.NUM_PROPERTIES) {
			/* Get all required properties and ensure they have been set */
			Field[] flds = IndexerConstants.class.getDeclaredFields();
			boolean valid = true;
			Object key;

			for (Field f : flds) {
				if (f.isAnnotationPresent(RequiredConstant.class)) {
					try {
						key = f.get(null);
						if (!props.containsKey(key) || props.get(key) == null) {
							System.err.println("The required property "
									+ f.getName() + " is not set");
							valid = false;
						}
					} catch (IllegalArgumentException e) {

					} catch (IllegalAccessException e) {

					}
				}
			}

			return valid;
		}

		return false;
	}

}
