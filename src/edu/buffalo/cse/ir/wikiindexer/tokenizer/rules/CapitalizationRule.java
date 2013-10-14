package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;

@RuleClass(className = RULENAMES.CAPITALIZATION)
public class CapitalizationRule implements TokenizerRule {

	public void apply(TokenStream stream) throws TokenizerException {
		// TODO Auto-generated method stub

		ArrayList<String> localList = new ArrayList();
		ArrayList<String> localList2 = new ArrayList();
		boolean flag = true;
		try
	{
		while (stream.hasNext()) {
			localList.add(stream.next());

		}

		// WHITE SPACE RULE WILL BE CALLED FIRST METHODS WILL WORK PERFECTLY.

		// System.out.println(localList);
		
		if(localList.size()>0)
		{	
		for (int i = 0; i < localList.size(); i++) {
			String localString = localList.get(i);
		
			if(localString!=null && localString.length()>0)
			{
			
			if (i - 1 >= 0) 
			{ // System.out.println("in for" + localString);
				flag = true;
				String localString2 = localList.get(i - 1);
				int nextStrLength = localString2.length();
				// System.out.println();

				if ((localString2.charAt(nextStrLength - 1)) == '.') // checking
																		// for
																		// full
																		// stop
																		// in
																		// previous
																		// token
				{
					for (int check1 = 1; check1 < localString.length(); check1++) {
						if (Character.isUpperCase(localString.charAt(check1))) // checking
																				// for
																				// any
																				// other
																				// uppercase
																				// character
																				// if
																				// found
																				// do
																				// not
																				// convert
																				// to
																				// lower
																				// case.
						{
							flag = false;
							localList2.add(localString); // add it as it is
							break;
						}

					}

					if (flag == true) {
						localString = localString.toLowerCase(); // else convert
																	// to lower
																	// string
						localList2.add(localString);
					}

				} else {
					localList2.add(localString); // else '.' not found set as it
													// is..
				}

			} else {
				localString = localString.toLowerCase(); // for zeroth element
															// muxt convert.
				localList2.add(localString);
			}
			
		}

		}
	}

		// for merge of two words

		for (int l = 0; l < localList2.size(); l++)
		{
			if (l < localList2.size() - 1) {
				String localString3 = localList2.get(l);
				String localString4 = localList2.get(l + 1);
				int crntLength = localString3.length();

				// check if next word's first character is Capital.

				if ((Character.isUpperCase(localString3.charAt(0)))
						&& (Character.isUpperCase(localString4.charAt(0)))
						&& (localString3.charAt(crntLength - 1) != '.')) {
					localList2.set(l, localString3 + " " + localString4);
					localList2.remove(l + 1);
				}
			}

		}

		// System.out.println(localList2);
		//System.out.println(localList2);
		stream.setList(localList2);
	}
		catch(Exception e)
		{
			//move forward;
		}
	}

}
