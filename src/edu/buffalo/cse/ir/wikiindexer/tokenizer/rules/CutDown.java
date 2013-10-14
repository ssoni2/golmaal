package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import java.util.ArrayList;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;

@RuleClass(className = RULENAMES.STOPWORDS)
public class CutDown implements TokenizerRule {

	public void apply(TokenStream stream) throws TokenizerException {
		// TODO Auto-generated method stub

		ArrayList<String> localList = new ArrayList<String>();
		while (stream.hasNext()) {
			String localString = stream.next();
			if (localString!=null && localString.length() > 2) {
				// System.out.println(count);
				localList.add(localString.trim());
			}
		}
		stream.setList(localList);

		/*
		 * ArrayList<String> localList = new ArrayList<String>();
		 * ArrayList<String> localList2 = new ArrayList<String>(); while
		 * (stream.hasNext()) { localList2.add(stream.next());
		 * System.out.println(stream.hasNext());
		 * 
		 * } for (int i = 0; i < localList2.size(); i++) { String localString =
		 * localList2.get(i); //if (!swords.contains(localString)) { //
		 * System.out.println(count); localList.add(localString); //} }
		 * stream.setList(localList);
		 */
	}
}
