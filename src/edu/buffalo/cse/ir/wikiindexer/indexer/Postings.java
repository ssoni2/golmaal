package edu.buffalo.cse.ir.wikiindexer.indexer;

import java.util.Map;
import java.util.TreeMap; 

public class Postings {
	public Map<Integer, Integer> posting; // docid,frequency

	public Postings() {
		posting = new TreeMap<Integer, Integer>();
		
	}
	public Postings(int docid, int frequency) {
		posting = new TreeMap<Integer, Integer>();
		posting.put(docid, frequency);
	}

}
