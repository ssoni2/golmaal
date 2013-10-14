package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import java.util.ArrayList;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;

@RuleClass(className = RULENAMES.SPECIALCHARS)
public class SpecialCharRule implements TokenizerRule {

	public void apply(TokenStream stream) throws TokenizerException {
		// TODO Auto-generated method stub

		if (stream != null) {
			String localString;
			ArrayList<String> localList = new ArrayList();
			ArrayList<String> localList2 = new ArrayList();

			while (stream.hasNext()) {
				localList.add(stream.next());

			}

			// String temp="";
			for (int count = 0; count < localList.size(); count++)

			{
				localString = localList.get(count);

				if (localString != null) {

					StringBuilder newString = new StringBuilder(localString);
					// System.out.println(newString);
					// System.out.println(newString.charAt((newString.length()-1)));
					for (int i = 0; i < newString.length(); i++) {
						if ((int) newString.charAt(i) > 64
								&& (int) newString.charAt(i) < 91) {
							// newString.deleteCharAt(i);
						} else if ((int) newString.charAt(i) > 96
								&& (int) newString.charAt(i) < 123) {
							// newString.deleteCharAt(i);

						} else if ((int) newString.charAt(i) > 47
								&& (int) newString.charAt(i) < 58) {
							// newString.deleteCharAt(i);

						} else if (((int) newString.charAt(i) == 45
								&& ((i + 1) < newString.length()) && Character
									.isDigit(newString.charAt(i + 1)))
								|| (int) newString.charAt(i) == 46
								|| (int) newString.charAt(i) == 63
								|| (int) newString.charAt(i) == 33
								|| (int) newString.charAt(i) == 45) {
							// newString.deleteCharAt(i);

						} else {
							if (newString.charAt(i) == '@'
									|| newString.charAt(i) == '*'
									|| newString.charAt(i) == '^') {
								newString.setCharAt(i, ' ');
							} else {
								//System.out.println("else");
								newString.deleteCharAt(i);
								i = i - 1;
							}

						}

					}
					// System.out.println(localString);

					localString = newString.toString();

					String[] newList = localString.split(" ");
					int length = newList.length;

					// System.out.println(newList);

					for (int j = 0; j < length; j++) {
						// System.out.println(newList[j]);
						if (newList[j].length() > 0) {
							//System.out.print(newList[j]);
							localList2.add(newList[j]);
						}
						// System.out.println(localString);

					}

				}
				stream.setList(localList2);
			}
			// System.out.println(localList2);

			// stream.reset();

		}

	}

}
