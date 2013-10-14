package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import java.util.ArrayList;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;

@RuleClass(className = RULENAMES.DELIM)
public class DelimRule implements TokenizerRule {

	public void apply(TokenStream stream) throws TokenizerException {
		// TODO Auto-generated method stub

		ArrayList<String> localList = new ArrayList<String>();
		ArrayList<String> localList2 = new ArrayList<String>();

		while (stream.hasNext()) {
			localList.add(stream.next());

		}
		// System.out.println(localList);
		// System.out.println(localList.size());
		for (int i = 0; i < localList.size(); i++) {
			String localString = localList.get(i);
			// System.out.println(localString);
			if (localString != null) {

				String[] localArrayString = localString.split("=");

				for (int j = 0; j < localArrayString.length; j++) {
					String localString2 = localArrayString[j];
					localString2 = localString2.trim();
					if (localString2.length() > 0) {
						localList2.add(localString2);
					}
				}
			}

		}
		stream.setList(localList2);

	}

}
