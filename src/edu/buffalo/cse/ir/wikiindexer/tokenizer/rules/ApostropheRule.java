package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import java.util.ArrayList;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;

@RuleClass(className = RULENAMES.APOSTROPHE)
public class ApostropheRule implements TokenizerRule {

	public void apply(TokenStream stream) throws TokenizerException

	{

		ArrayList<String> localList = new ArrayList();
		ArrayList<String> localList2 = new ArrayList();

		// Array<String> newList = new Array<String>();
		String[] newList;

		int newStringFlag = 0;

		while (stream.hasNext()) {

			// System.out.println("hiiiii");
			// localList.add(stream.next());

			localList.add(stream.next());
			// System.out.println(localList);
			// System.out.println(localList);
		}
		// System.out.println(localList);

		for (int i = 0; i < localList.size(); i++) {

			newList = null;
			String localString = localList.get(i);

			localString = localString.replaceAll("won't", "will not");
			localString = localString.replaceAll("shan't", "shall not");
			localString = localString.replaceAll("let's", "let us");
			localString = localString.replaceAll("'em", "them");

			localString = localString.replaceAll("n't", " not");
			localString = localString.replaceAll("'d", " would");
			localString = localString.replaceAll("'ll", " will");
			// localString=localString.replaceAll("s'","s");
			localString = localString.replaceAll("'m", " am");
			localString = localString.replaceAll("'ve", " have");
			localString = localString.replaceAll("'s", "");
			localString = localString.replaceAll("'re", " are");
			localString = localString.replaceAll("'", "");

			/*
			 * if ((localString == "*s'") && localList.get(i+1)!=null) {
			 * localList.set(i, localList.get(i)+" "+localList.get(i+1));
			 * localList.remove(i+1); System.out.println(localList.get(i));
			 * //localList.remove(index) }
			 */
			/*
			 * else {
			 */
			newList = localString.split(" ");
			int length = newList.length;

			// System.out.println(newList);

			for (int j = 0; j < length; j++) {
				// System.out.println(newList[j]);
				localList2.add(newList[j]);
			}

			/*
			 * }
			 */

		}
		stream.setList(localList2);
		// System.out.println(localList2);

	}
}
