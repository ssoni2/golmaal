/**
 * 
 */
package edu.buffalo.cse.ir.wikiindexer.wikipedia;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author nikhillo This class implements Wikipedia markup processing. Wikipedia
 *         markup details are presented here:
 *         http://en.wikipedia.org/wiki/Help:Wiki_markup It is expected that all
 *         methods marked "todo" will be implemented by students. All methods
 *         are static as the class is not expected to maintain any state.
 */
public class WikipediaParser {
	/* TODO */
	/**
	 * Method to parse section titles or headings. Refer:
	 * http://en.wikipedia.org/wiki/Help:Wiki_markup#Sections
	 * 
	 * @param titleStr
	 *            : The string to be parsed
	 * @return The parsed string with the markup removed
	 */

	public static String parseSectionTitle(String titleStr) {

		String titleString = titleStr;

		boolean textFlag = false;

		int newLineAt = 0;

		int askiValue = 0;

		int askiValueNext = 0;

		// int charValue=0;

		if (titleString != null) {
			// System.out.print(titleString);
			for (int i = 0; i < titleString.length(); i++)

			{

				try {
					if (titleString.charAt(i) == '='
							&& titleString.charAt(i + 1) == '\n') {

						newLineAt = i;
						textFlag = true;
						break;
					}
				}

				catch (Exception e) {
					// System.out.print("You got me dude" + titleString);
				}
			}

			if (textFlag == true) {
				titleString = titleString.substring(0, newLineAt - 1);

			}

			titleString = titleString.replace("  ", " ");

			// System.out.println(titleString);

			// System.out.println((int)(titleString.charAt(charValue)));

			for (int newLenght = 0; newLenght < titleString.length() - 1; newLenght++)

			// System.out.println((int)(titleString.charAt(1)));
			// System.out.println((int)(titleString.charAt(2)));

			{

				askiValue = (int) (titleString.charAt(newLenght));
				askiValueNext = (int) (titleString.charAt(newLenght + 1));

				if ((askiValue == 32) && (askiValueNext == 61)) {
					// titleString=titleString.replace(" ", "");

					StringBuilder sb = new StringBuilder(titleString);
					sb.deleteCharAt(newLenght);
					titleString = sb.toString();

					// System.out.println(titleString);

				} else if ((askiValue == 61) && (askiValueNext == 32))

				{
					// System.out.println("visit");
					StringBuilder sb = new StringBuilder(titleString);
					sb.deleteCharAt(newLenght + 1);
					titleString = sb.toString();
					// System.out.println(titleString);

				}

			}

			titleString = titleString.replace("=", "");

			titleString = titleString.replace("\n", "");

			// System.out.print(titleString);

			return titleString;
		}

		return null;

	}

	/* TODO */
	/**
	 * Method to parse list items (ordered, unordered and definition lists).
	 * Refer: http://en.wikipedia.org/wiki/Help:Wiki_markup#Lists
	 * 
	 * @param itemText
	 *            : The string to be parsed
	 * @return The parsed string with markup removed
	 */
	public static String parseListItem(String itemText) {

		// String listText= itemText;

		if (itemText == null) {
			return null;
		} else if (itemText == "") {
			return "";
		} else

		{
			StringBuilder listText = new StringBuilder(itemText);

			for (int count = 0; count < listText.length() - 1; count++) {
				if (listText.charAt(count) == '#'
						|| listText.charAt(count) == ';'
						|| listText.charAt(count) == '*'
						|| listText.charAt(count) == ':')

				{

					try {
						String s = listText.substring(0, count) + " "
								+ listText.substring(count);
						listText = new StringBuilder(s);
					} catch (Exception e) {
						System.out.println("Here comes exception");
					}

					// listText.deleteCharAt(count);

					// int countNext=count+1;
					try {
						while ((int) listText.charAt(count) == 32
								|| listText.charAt(count) == '#'
								|| listText.charAt(count) == ';'
								|| listText.charAt(count) == '*'
								|| listText.charAt(count) == ':') {

							listText.deleteCharAt(count);
							// count= count+1;
						}
						// System.out.println(listText);
						// listText=listText.append(" ");
						// System.out.println(listText);
					} catch (IndexOutOfBoundsException e) {
						// System.out.println("Index out of bount expection");
					}

				}

			}

			itemText = listText.toString();
			// System.out.println(itemText);
			return itemText;
		}

	}

	/* TODO */
	/**
	 * Method to parse text formatting: bold and italics. Refer:
	 * http://en.wikipedia.org/wiki/Help:Wiki_markup#Text_formatting first point
	 * 
	 * @param text
	 *            : The text to be parsed
	 * @return The parsed text with the markup removed
	 */
	public static String parseTextFormatting(String text) {

		if (text != null) {
			text = text.replace("'", "");

			return text;

		}
		return null;
	}

	/* TODO */
	/**
	 * Method to parse *any* HTML style tags like: <xyz ...> </xyz> For most
	 * cases, simply removing the tags should work.
	 * 
	 * @param text
	 *            : The text to be parsed
	 * @return The parsed text with the markup removed.
	 */
	public static String parseTagFormatting(String text) {

		try {
			if (text != null) {
				String testing = text;

				testing = testing.replaceAll(" </", "<");
				testing = testing.replaceAll("> ", ">");

				// int i=0;

				for (int i = 0; i < testing.length(); i++)

				{

					if (testing.charAt(i) == '<') {
						// System.out.println("here");

						int j = i + 1;

						try {
							while ((j < testing.length())
									&& (testing.charAt(j) != '>')) {
								j++;
							}
							// System.out.println(j);
							// System.out.println(testing.charAt(j));
							testing = testing.substring(0, i)
									+ testing
											.substring(j + 1, testing.length());
							// System.out.println();
						} catch (IndexOutOfBoundsException e) {
							// System.out.println(i);
							// System.out.println(j);
						}
					}

				}

				while (testing.indexOf("&lt;") != -1) {
					int l = testing.indexOf("&lt;");
					int m = testing.indexOf("&gt;");

					testing = testing.replaceAll(testing.substring(l, m + 4),
							"");

					// System.out.println(testing);

				}

				// System.out.println(testing);
				return testing;

			}
			return null;
		} catch (Exception e) {
			if (text != null)
				return text;
			else
				return null;
		}
	}

	/*
	 * if(text!=null){ StringBuilder texttemp=new StringBuilder(text);
	 * while(texttemp.indexOf("<")!=-1){ int a=texttemp.indexOf("<"); int
	 * b=texttemp.indexOf(">"); if(a>b){ try{ texttemp=texttemp.delete(b, b+1);
	 * } catch(Exception e) { System.out.println("ignore it"); } } else{
	 * texttemp=texttemp.delete(a, b+2); } }
	 * 
	 * return texttemp.toString().trim(); } else return null; }
	 */

	/* TODO */
	/**
	 * Method to parse wikipedia templates. These are *any* {{xyz}} tags For
	 * most cases, simply removing the tags should work.
	 * 
	 * @param text
	 *            : The text to be parsed
	 * @return The parsed text with the markup removed
	 */
	public static String parseTemplates(String text) {

		String testing = text;

		if (testing != null) {
			StringBuilder newString = new StringBuilder(testing);

			while (newString.indexOf("{{") != -1) {
				int l = newString.indexOf("{{");
				int m = newString.indexOf("}}", l);
				if (l == -1 || m == -1) {
					break;
				}
				if (m > l) {
					newString = newString.delete(l, m + 2);
				}

			}

			testing = newString.toString();
			// System.out.println(testing);
			return testing;
		}

		return null;
	}

	/* TODO */
	/**
	 * Method to parse links and URLs. Refer:
	 * http://en.wikipedia.org/wiki/Help:Wiki_markup#Links_and_URLs
	 * 
	 * @param text
	 *            : The text to be parsed
	 * @return An array containing two elements as follows - The 0th element is
	 *         the parsed text as visible to the user on the page The 1st
	 *         element is the link url
	 */
	public static String[] parseLinks(String text) {

		String textCopy = "";
		String[] failed = new String[2];
		failed[0] = "";
		failed[1] = "";

		if (text != null && text.length() > 0 && text.indexOf('[') != -1
				&& text.indexOf(']') != -1 && text.indexOf('{') == -1) {

			try {

				List<String> linsklist = new ArrayList<String>();
				// String[] links;
				String localString = text;
				String localText = "";
				String localLink = "";
				String localString0;

				int flag = 0;
				int flag2 = 0;
				int flag3 = 0;
				int flag4 = 0;
				int flag5 = 0;
				int flag6 = 0;

				localString = localString.replaceAll("<.*>", "");
				localString = localString.replaceAll("\\[\\[", "[");
				localString = localString.replaceAll("\\]\\]", "]");
				int indexOfStr = localString.indexOf('[');
				String tempStart = localString.substring(0, indexOfStr);
				// System.out.println(tempStart);
				int indexOfStop = localString.indexOf(']');
				String tempStop = localString.substring(indexOfStop + 1,
						localString.length());
				localString = localString
						.substring(indexOfStr + 1, indexOfStop);

				int lastIndex = localString.lastIndexOf('|');

				if (lastIndex != -1) {
					localString0 = localString.substring(0, lastIndex);
					// System.out.println(localString0);
					if (lastIndex < localString.length() - 1) {
						localText = localString.substring(lastIndex + 1,
								localString.length());
						// System.out.println(localText);
						int toLink = localString.indexOf(':');
						if (toLink == -1) {
							localLink = localString0.replaceAll(" ", "_");
						}
						flag = 1;
						// localString0=localString.substring(0,lastIndex-1);
						// linsklist.add(localText);

					}

					int lastIndexC = localString0.lastIndexOf('(');

					if (lastIndexC != -1) {
						String localStringC0 = localString0.substring(0,
								lastIndexC - 1);
						String localStringC1 = localString0.substring(
								lastIndexC, localString0.length());
						localLink = localStringC0 + "_" + localStringC1.trim();
						localText = localStringC0.trim();
						flag3 = 1;
					}

					int IndexCln = localString0.indexOf(':');
					String locaStringCln0 = "";
					String localStringCln1 = "";
					if (IndexCln != -1) {
						locaStringCln0 = localString0.substring(0, IndexCln);
						localStringCln1 = localString0.substring(IndexCln + 1,
								localString0.length());

						int IndexClnC = localStringCln1.indexOf('(');
						int IndexClnCln = localStringCln1.indexOf(':');

						String localStringClnC1 = "";
						if (IndexClnC != -1) {
							String localStringClnC0 = localStringCln1
									.substring(0, IndexClnC);
							localStringClnC1 = localStringCln1.substring(
									IndexClnC, localStringCln1.length());
							localText = localStringClnC0.trim();
							localLink = "";
							flag4 = 1;

						}
						if (IndexClnCln != -1) {
							// System.out.println(localStringCln1);
							localText = localStringCln1;
							localLink = "";
							flag = 1;

						}

						if (flag4 == 0 && flag == 0
								&& localString.indexOf('#') != -1) {
							localText = localString0;
						}

						else if (flag4 == 0 && flag == 0) {
							localText = localStringCln1;
						}
						flag = 1;

						// System.out.println(localText);

					} else if (flag == 0 && flag3 == 0) {
						localText = localStringCln1;
					}
					int IndexComa = localString0.indexOf(',');

					if (IndexComa != -1) {
						String localStringComa0 = localString0.substring(0,
								IndexComa);
						String localStringComa1 = localString0.substring(
								IndexComa, localString0.length());
						localText = localStringComa0;
						localLink = (localStringComa0 + localStringComa1)
								.replaceAll(" ", "_");
						flag5 = 1;
						// System.out.println(localLink);
					}

					int Indexpipe2 = localString0.indexOf('|');

					if (Indexpipe2 != -1) {
						// do nothing
					}

					else {
						// localLink=localString0.replaceAll(" ", "_");
					}

				}

				int IndexMCln = localString.indexOf(':');

				if (IndexMCln != -1 && flag == 0) {
					String localStringMCln0 = " ";
					if (IndexMCln > 0) {
						flag6 = 1;
						// System.out.println(localString);
						localStringMCln0 = localString.substring(0, IndexMCln);
					}
					String localStringMCln1 = localString.substring(
							IndexMCln + 1, localString.length());
					// System.out.println(localStringMCln1);

					int IndexClnDot = localStringMCln1.indexOf('.');

					if (IndexClnDot != -1) {
						localText = "";
						flag2 = 1;
						// do nothing
					}

					int IndexClnCtrgy = localString.indexOf("Category");

					if (IndexClnCtrgy != -1) {
						// System.out.println("visit");

						localText = localString.substring(IndexClnCtrgy,
								localString.length());
						flag2 = 1;
					}

					IndexClnCtrgy = localStringMCln0.indexOf("Category");

					if (IndexClnCtrgy != -1 && flag6 == 1) {
						// System.out.println("visit");
						// System.out.println(localText);
						localText = localStringMCln1;
						flag2 = 1;
						// System.out.println(localStringMCln0);
						// localText=localStringMCln0.substring(IndexClnCtrgy,
						// localStringMCln0.length());
					}
					// System.out.println();
					if (localStringMCln0.matches("es")
							|| localStringMCln0.matches("es")) {
						localText = localString;
					} else if (flag2 == 0) {
						localText = localString;
					}
				}

				int IndexComa = localString.indexOf(',');

				if (IndexComa != -1 && flag5 == 0) {
					String localStringComa0 = localString.substring(0,
							IndexComa);
					String localStringComa1 = localString.substring(IndexComa,
							localString.length());
					localText = localStringComa0;
					localLink = (localStringComa0 + localStringComa1)
							.replaceAll(" ", "_");
				}

				int IndexofHypn = localString.indexOf('-');

				if (IndexofHypn != -1) {
					localLink = localString;
					localText = localString;
				}

				int IndexofHttp = localString.indexOf("http:");

				if (IndexofHttp != -1) {
					int IndexofHttpSpc = localString.indexOf(' ');

					if (IndexofHttpSpc != -1) {
						localText = localString.substring(IndexofHttpSpc,
								localString.length());
					}

				}

				else {
					// localLink=localString.replaceAll(" ","_");
					// localText=localString;
					// do nothing;
				}

				int checkSimple = localString.indexOf(':');
				// int checkSimple2= localString.indexOf('|');
				int checkSimple3 = localString.indexOf('(');
				int checkSimple4 = localString.indexOf(',');

				if (checkSimple == -1 && checkSimple3 == -1
						&& checkSimple4 == -1 & flag == 0) { // System.out.println("visit");
					localText = localString;
					localLink = localString.replaceAll(" ", "_");
					localLink = localLink.replaceAll("\\|", "");
				}

				// localLink=localLink.replace(localLink.charAt(0),Character.toUpperCase(localLink.charAt(0)));

				if (localLink != null && localLink.length() > 1) {
					localLink = localLink.substring(0, 1).toUpperCase()
							+ localLink.substring(1);
					localLink = localLink.replaceAll(" ", "_");
				}
				linsklist.add((tempStart + localText + tempStop).trim());
				linsklist.add(localLink);
				// System.out.println(linsklist);

				// System.out.println(myArray[1]);

				//
				// System.out.println(Character.isDigit(i-1));

				String[] links = new String[linsklist.size()];
				// System.out.println(localText);
				for (int il = 0; il < linsklist.size(); il++) {
					links[il] = linsklist.get(il);

				}
				linsklist.clear();
				linsklist = null;
				return links;

			} catch (Exception e) {
				return failed;
			}

		}
		return failed;
	}

}
