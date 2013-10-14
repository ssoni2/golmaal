/**
 * 
 */
package edu.buffalo.cse.ir.wikiindexer.parsers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import edu.buffalo.cse.ir.wikiindexer.wikipedia.WikipediaDocument;
import edu.buffalo.cse.ir.wikiindexer.wikipedia.WikipediaParser;

/**
 * @author nikhillo
 * 
 */
public class Parser extends DefaultHandler {

	private String FieldExists = "invalid";

	private int idFromXML;

	private String timestampFromXML;

	private String authorFromXML;

	private String titleFromXML;

	private String textFromXML;

	private int counterForId = 0;

	// private static List <WikipediaDocument> WikiDocsList = new
	// ArrayList<WikipediaDocument>();

	private static List<DefaultHandlerExtender> DefaultHandlerObjectList = new ArrayList();

	// check

	static int checkC = 0;

	@Override
	public void characters(char[] Characters, int arg1, int arg2)
			throws SAXException {
		// TODO Auto-generated method stub

		if (FieldExists.equalsIgnoreCase("id"))

		{

			idFromXML = Integer.parseInt(new String(Characters, arg1, arg2));
		}

		else if (FieldExists.equalsIgnoreCase("author"))

		{

			authorFromXML = new String(Characters, arg1, arg2);

		}

		else if (FieldExists.equalsIgnoreCase("title"))

		{

			titleFromXML = new String(Characters, arg1, arg2);
		}

		else if (FieldExists.equalsIgnoreCase("timestamp"))

		{

			timestampFromXML = new String(Characters, arg1, arg2);
		}

		else if (FieldExists.equalsIgnoreCase("text"))

		{

			textFromXML = textFromXML + new String(Characters, arg1, arg2);

		}

	}

	@Override
	public void endElement(String arg0, String arg1, String arg2)
			throws SAXException {

		if (arg2.equalsIgnoreCase("id") || arg2.equalsIgnoreCase("title")
				|| arg2.equalsIgnoreCase("ip")
				|| arg2.equalsIgnoreCase("username")
				|| arg2.equalsIgnoreCase("timestamp")
				|| arg2.equalsIgnoreCase("text")) {
			FieldExists = "invalid";

		}

		if (arg2.equalsIgnoreCase("page")) {

			DefaultHandlerExtender DefaultHandlerExtenderObj = new DefaultHandlerExtender();
			DefaultHandlerExtenderObj.setIdFromXML(idFromXML);
			DefaultHandlerExtenderObj.setTitle(titleFromXML);
			DefaultHandlerExtenderObj.setText(textFromXML);
			DefaultHandlerExtenderObj.setAuthorFromXML(authorFromXML);
			DefaultHandlerExtenderObj.setTimestampFromXML(timestampFromXML);
			DefaultHandlerObjectList.add(DefaultHandlerExtenderObj);

			textFromXML = null;
			counterForId = 0;

		}

	}

	@Override
	public void startElement(String arg0, String arg1, String arg2,
			Attributes arg3) throws SAXException {

		if (arg2.equalsIgnoreCase("id") & counterForId == 0)

		{
			counterForId = counterForId + 1;

			FieldExists = "id";

		}

		else if ((arg2.equalsIgnoreCase("username"))
				|| (arg2.equalsIgnoreCase("ip"))) {

			FieldExists = "author";

		}

		else if (arg2.equalsIgnoreCase("timestamp")) {

			FieldExists = "timestamp";

		}

		else if (arg2.equalsIgnoreCase("title")) {

			FieldExists = "title";

		}

		else if (arg2.equalsIgnoreCase("text")) {

			FieldExists = "text";

		}

	}

	private final Properties props;

	/**
	 * 
	 * @param idxConfig
	 * @param parser
	 */
	public Parser(Properties idxProps) {
		props = idxProps;
	}

	/* TODO: Implement this method */
	/**
	 * 
	 * @param filename
	 * @param docs
	 */

	/*
	 * public void BasicXMLParser() { Parser handler = new Parser(props);
	 * SAXParserFactory factory = SAXParserFactory.newInstance(); try {
	 * SAXParser parser = factory.newSAXParser();
	 * parser.parse("WikiDump_1600.xml", handler); } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

	public void parse(String filename, Collection<WikipediaDocument> docs)

	{
		System.out.println("Execution starts here");

		if (filename != null && filename != "")

		{
			// BasicXMLParser();

			Parser handler = new Parser(props);
			SAXParserFactory factory = SAXParserFactory.newInstance();

			try {
				SAXParser parser = factory.newSAXParser();
				parser.parse(filename, handler);
			} catch (Exception e) {
				System.out.println("File Not found"+filename);;
				//e.printStackTrace();
			}

			String sectionTitle = null;

			String sectionText = null;

			List<String> localCategories = new ArrayList();

			// System.out.println(DefaultHandlerObjectList.size());
			try {

				for (int i = 0; i < DefaultHandlerObjectList.size(); i++) {

					int IdFromXMLCopy = DefaultHandlerObjectList.get(i)
							.getIdFromXML();
					String AuthorFromXMLCopy = DefaultHandlerObjectList.get(i)
							.getAuthorFromXML();
					String TitleCopy = DefaultHandlerObjectList.get(i)
							.getTitle();
					String TimestampFromXMLCopy = DefaultHandlerObjectList.get(
							i).getTimestampFromXML();
					String textCopy = DefaultHandlerObjectList.get(i).getText();

					// System.out.println("Only 5 time"+i);

					// System.out.println("text");
					// System.out.println(textCopy);
					textCopy = textCopy.replace("\n\n", "\n");

					WikipediaDocument obj = new WikipediaDocument(
							IdFromXMLCopy, TimestampFromXMLCopy,
							AuthorFromXMLCopy, TitleCopy);

					
					// for categories Sob 250
					// ****************************************************************
					int indexOf = 0;
					int indexOfI = 0;
					int indexofE = 0;
					String localLink = null;
					String forCategories = textCopy;

					// List<String> localCategories;
					// for (int m = 0; m < textCopy.length(); m++)
					while (indexofE != -1) {

						indexOfI = forCategories.indexOf("[[Category:",
								indexofE);
						if (indexOfI == -1) {
							break;
						}
						indexofE = forCategories.indexOf("]]", indexOfI);
						if (indexofE == -1) {
							indexofE = forCategories.indexOf("]", indexOfI);
						}
						if (indexofE == -1) {
							indexofE = forCategories.length() - 1;
						}
						// /System.out.println("Sob 272: "+indexOfI+" "+indexofE);
						localLink = forCategories.substring(indexOfI, indexofE);
						localLink = localLink.replaceAll("\\[\\[.*\\:", "");
						localLink = localLink.replaceAll("]", "");
						//System.out.println("Sob 277: Cat: "+localLink);
						localCategories.add(localLink);
						// forCategories = forCategories
						// .substring(indexofE);
						// System.out.println(forCategories);
						// System.out.println(forCategories);

					}

					obj.addCategories(localCategories);

					// System.out.println(textCopy.substring(indexOf,
					// textCopy.length()-1));
					// System.out.println("END");
					localCategories.clear();
					// /Category ends
					// ***************************************************************************
					
					// Linkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk
					// System.out.println(textCopy);
					localLink="";
					Set<String> mySet = new HashSet<String>();
					for (int cnt = 0; cnt < textCopy.length()-1; cnt++) {
						int cnt2 = 0;
						if (textCopy.charAt(cnt) == '[' && textCopy.charAt(cnt+1) == '[') { // System.out.println(cnt);
							String localText = null;
							for (cnt2 = cnt; cnt2 < textCopy.length()-1; cnt2++) {
								
								if(textCopy.charAt(cnt2)=='[' && textCopy.charAt(cnt2+1) == '['){
									cnt = cnt2-1;
									continue;
								}
								
								if (textCopy.charAt(cnt2) == ']') {
									//System.out.println("Sob 257"+textCopy.substring(cnt,
													//cnt2 + 1));
									String[] localarray = WikipediaParser
											.parseLinks(textCopy.substring(cnt,
													cnt2 + 1));
									localText = localarray[0];
									localText.replaceAll("\\]", "")
									.replaceAll("\\[", "");
									 localLink = localarray[1]; /////note del 2 ini
									textCopy = textCopy
											.replace(
													textCopy.substring(cnt,
															cnt2 + 1),
													localText)
											;
									// System.out.println(textCopy);

									// mySet.add(localText);
									if (localLink != null && localLink != "") {
										mySet.add(localLink);
									}
									obj.addLink(localLink);
									break;
								}
							}

							// System.out.println(mySet);

							cnt = cnt + localText.length() - 1;
						}
					}
					
					//System.out.println("Sob 284 title: " + TitleCopy
							//+ " link : " + mySet);
					// obj.
					// //////// Linkkkkkkkkkkkkkenddddddddddd

					// System.out.println(textCopy.length());

					// System.out.println(textCopy);

					
					// System.out.println(localCategories);
					// System.out.println(forCategories);

					boolean flag = false;
					// For default section
					String DefaultText = "";
					
					for (int m = 0; m < textCopy.length(); m++)

					{
						flag = false;

						if (((textCopy.charAt(m) == '\n') && (textCopy
								.charAt(m + 1) == '='))) {
							DefaultText = textCopy.substring(0, m - 1);
							obj.addSection("Default", DefaultText);
							flag = true;
							break;
						}

					}

					if (flag == false) {

						DefaultText = WikipediaParser
								.parseTextFormatting(DefaultText);
						DefaultText = WikipediaParser
								.parseTemplates(DefaultText);
						DefaultText = WikipediaParser
								.parseListItem(DefaultText);
						DefaultText = WikipediaParser
								.parseTagFormatting(DefaultText);

						obj.addSection("Default", textCopy.substring(0));

					}

					for (int m = 0; m < textCopy.length(); m++)

					{

						if (((textCopy.charAt(m) == '\n') && (textCopy
								.charAt(m + 1) == '='))
								&& (m < textCopy.length()))

						{

							int n = m + 1;

							try {
								while ((n < textCopy.length())
										&& !((textCopy.charAt(n) == '\n') && (textCopy
												.charAt(n + 1) == '='))) {
									n = n + 1;

								}
							}

							catch (Exception e) {
								System.out
										.println("here is the exception Parser.Java while1 first loop");

							}

							// System.out.println(textCopy.substring(n-5, n+1));

							sectionTitle = WikipediaParser
									.parseSectionTitle(textCopy.substring(m, n));

							// System.out.println(sectionTitle);
							// System.out.println("break point 0");

							sectionText = (textCopy.substring(m, n));

							// sectionText=WikipediaParser.parseTemplates(sectionText);

							// System.out.println(sectionText);

							// System.out.println(textCopy.substring(n-5, n+1));

							// System.out.println(textCopy.charAt(n-5)+textCopy.charAt(n-4)+textCopy.charAt(n-3)+textCopy.charAt(n-2)+textCopy.charAt(n-1)+textCopy.charAt(n)+textCopy.charAt(n+1));

							int startText = 0;

							int textLength = sectionText.length();

							try {

								while (!(sectionText.charAt(startText) == '=' && sectionText
										.charAt(startText + 1) == '\n'))

								{

									startText = startText + 1;

								}

								sectionText = sectionText.substring(
										startText + 1, textLength);

							}

							catch (Exception e)

							{

								sectionText = null;
								// text is null..
							}

							// System.out.println(sectionText);

							sectionText = WikipediaParser
									.parseTextFormatting(sectionText);
							sectionText = WikipediaParser
									.parseTemplates(sectionText);
							sectionText = WikipediaParser
									.parseListItem(sectionText);
							sectionText = WikipediaParser
									.parseTagFormatting(sectionText);
							// System.out.println(sectionTitle);
							// System.out.println(sectionText);
							// System.out.println("Start 1");
							// System.out.println(sectionText);
							obj.addSection(sectionTitle, sectionText);

							// textCopy.substring(m,n));

							// sectionTitle=WikipediaParser.parseSectionTitle(textCopy);

							// System.out.println("Visited for class at position");

							// System.out.print(sectionTitle);

							// System.out.println(DefaultHandlerObjectList.get(i));

							// textCopy=textCopy.substring(n,
							// textCopy.length());

						}

						else {
							;
						}

						// System.out.println("Here starts the second one");

					}
					// System.out.println("break point 1");
					add(obj, docs);
					// System.out.println(obj);
					// System.out.println(WikiDocsList);
					// System.out.println("here ends object    "+i);

				}
			} catch (Exception e) {
				// System.out.println("File Not found exception");
				e.printStackTrace();
			}
		} else {
			;
		}
	}

	/**
	 * Method to add the given document to the collection. PLEASE USE THIS
	 * METHOD TO POPULATE THE COLLECTION AS YOU PARSE DOCUMENTS For better
	 * performance, add the document to the collection only after you have
	 * completely populated it, i.e., parsing is complete for that document.
	 * 
	 * @param doc
	 *            : The WikipediaDocument to be added
	 * @param documents
	 *            : The collection of WikipediaDocuments to be added to
	 */
	private synchronized void add(WikipediaDocument doc,
			Collection<WikipediaDocument> documents) {
		// System.out.println(documents);

		checkC = checkC + 1;
		// System.out.println(checkC);
		// System.out.println(doc);
		// System.out.println("start");
		// System.out.println(doc.getSections().);
		documents.add(doc);

		// System.out.println(documents);
	}
}
