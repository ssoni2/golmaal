/**
 * 
 */
package edu.buffalo.cse.ir.wikiindexer.wikipedia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import edu.buffalo.cse.ir.wikiindexer.indexer.INDEXFIELD;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.Tokenizer;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.wikipedia.WikipediaDocument.Section;

/**
 * A Callable document transformer that converts the given WikipediaDocument
 * object into an IndexableDocument object using the given Tokenizer
 * 
 * @author nikhillo
 * 
 */
public class DocumentTransformer implements Callable<IndexableDocument> {

	//
	// private static final Object TERM = null;
	Map<INDEXFIELD, Tokenizer> localMap;
	WikipediaDocument localObject;
	String documentIdentifier;
	INDEXFIELD localIndexField;
	IndexableDocument localIndexObj = new IndexableDocument();
	Section localSection;
	String localTitle;
	String localSectionText;
	TokenStream localStream;
	String localAuthor;

	/**
	 * Default constructor, DO NOT change
	 * 
	 * @param tknizerMap
	 *            : A map mapping a fully initialized tokenizer to a given field
	 *            type
	 * @param doc
	 *            : The WikipediaDocument to be processed
	 */
	public DocumentTransformer(Map<INDEXFIELD, Tokenizer> tknizerMap,
			WikipediaDocument doc) {

		localMap = tknizerMap;
		localObject = doc;
		// TODO: Implement this method
	}

	/**
	 * Method to trigger the transformation
	 * 
	 * @throws TokenizerException
	 *             Inc ase any tokenization error occurs
	 */

	public IndexableDocument call() throws TokenizerException {
		// TODO Implement this method

		String identifier = localObject.getTitle();
		identifier = identifier.replaceAll(" ", "_");
		localIndexObj.setDocumentIdentifier(identifier);
		// System.out.println("Sob 59 "+identifier);

		for (INDEXFIELD fld : INDEXFIELD.values()) {

			if (fld.equals(INDEXFIELD.TERM)) {

				localStream = new TokenStream("xxxxxxx");

				for (int i = 0; i < localObject.getSections().size(); i++) {
					localSection = localObject.getSections().get(i);
					localTitle = localSection.getTitle();
					localSectionText = localSection.getText();
					// System.out.println(localSectionText);
					localStream.append(localTitle);
					localStream.append(localSectionText);
				}
				Tokenizer termTokenizer = localMap.get(INDEXFIELD.TERM);
				termTokenizer.tokenize(localStream);
				localIndexObj.addField(INDEXFIELD.TERM, localStream);

			}

			else if (fld.equals(INDEXFIELD.AUTHOR)) {

				localAuthor = localObject.getAuthor();
				localStream = new TokenStream(localAuthor);
				// System.out.println("sob 86 DTrans: "+ localAuthor);
				Tokenizer authorTokenizer = localMap.get(INDEXFIELD.AUTHOR);
				authorTokenizer.tokenize(localStream);
				localIndexObj.addField(INDEXFIELD.AUTHOR, localStream);

			} else if (fld.equals(INDEXFIELD.CATEGORY)) {
				List<String> locallist = new ArrayList<String>();
				locallist = localObject.getCategories();
				Tokenizer categoryTokenizr = localMap.get(INDEXFIELD.CATEGORY);
				String fortoken = null;
				if(!locallist.isEmpty()){
					fortoken = locallist.get(0);
				}
				for (int i = 1; i < locallist.size(); i++) {
					fortoken = fortoken + "=" + locallist.get(i);
				}
				//System.out.println("Sob 108 Categore: "+fortoken);
				TokenStream ts = new TokenStream(fortoken);
				categoryTokenizr.tokenize(ts);
				localIndexObj.addField(INDEXFIELD.CATEGORY, ts);

			}
			else if (fld.equals(INDEXFIELD.LINK)) {
				Set<String> locallist = new HashSet<String>();
				locallist = localObject.getLinks();
				Tokenizer categoryTokenizr = localMap.get(INDEXFIELD.LINK);
				Iterator<String > it = locallist.iterator();
				String fortoken = null;
				if(it.hasNext()){
					fortoken = it.next();
				}
				while (it.hasNext()) {
					fortoken = fortoken + "=" + it.next();
				}
				//System.out.println("Sob 129 Link: "+fortoken);
				TokenStream ts = new TokenStream(fortoken);
				categoryTokenizr.tokenize(ts);
				localIndexObj.addField(INDEXFIELD.LINK, ts);

			}

		}

		return localIndexObj;
	}

}
