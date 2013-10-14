package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import java.util.ArrayList;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;

@RuleClass(className = RULENAMES.NUMBERS)
public class NumberRule implements TokenizerRule {

	ArrayList<String> localList = new ArrayList();
	ArrayList<String> localList2 = new ArrayList();

	public void apply(TokenStream stream) throws TokenizerException {
		// TODO Auto-generated method stub

		if (stream != null) {
			String parseNumber;
			String localString2;

			while (stream.hasNext()) {
				localList.add(stream.next());
			}
			// System.out.println(localList);

			// System.out.println(localList);

			for (int i = 0; i < localList.size(); i++) {
				parseNumber = localList.get(i);

				String[] newList = parseNumber.split(" ");

				if (parseNumber.matches(".*[0-9].*[0-9].*"))

				{
					// System.out.println("Passed for" + localList.get(i));
					// System.out.println("passed");
					String localString;
					String localStringM = localList.get(i);
					if (i < localList.size() - 1) {
						// System.out.println(localList.get(i));
						localString = localList.get(i + 1);
						if (!(localString.equalsIgnoreCase("January")
								|| localString.equalsIgnoreCase("February")
								|| localString.equalsIgnoreCase("March")
								|| localString.equalsIgnoreCase("April")
								|| localString.equalsIgnoreCase("May")
								|| localString.equalsIgnoreCase("June")
								|| localString.equalsIgnoreCase("July")
								|| localString.equalsIgnoreCase("August")
								|| localString.equalsIgnoreCase("September")
								|| localString.equalsIgnoreCase("October")
								|| localString.equalsIgnoreCase("November")
								|| localString.equalsIgnoreCase("December")
								|| localString.equalsIgnoreCase("AD")
								|| localString.equalsIgnoreCase("BC") || localString
									.equalsIgnoreCase("ABD"))) {
							// System.out.println(localList.get(i));
							StringBuilder builder = new StringBuilder(
									localStringM);
							for (int r = 0; r < builder.length(); r++) {
								if ((((int) builder.charAt(r) >= 48) && ((int) builder
										.charAt(r) < 58))
										|| ((int) builder.charAt(r) == 44)
										|| ((int) builder.charAt(r) == 46)) {
									builder.deleteCharAt(r);
									r = r - 1;
								}
							}

							localStringM = builder.toString();
							if (localStringM != null
									&& localStringM.length() > 0
									&& localStringM != " ") {
								localList2.add(localStringM);
							}

							// i=i-1;
						} else {
							// do nothing need data for date rule
						}
					} else {
						StringBuilder builder = new StringBuilder(localStringM);
						for (int r = 0; r < builder.length(); r++) {
							if ((((int) builder.charAt(r) >= 48) && ((int) builder
									.charAt(r) < 58))
									|| ((int) builder.charAt(r) == 44)
									|| ((int) builder.charAt(r) == 46)) {
								builder.deleteCharAt(r);
								r = r - 1;
							}
						}

						localStringM = builder.toString();
						if (localStringM != null && localStringM.length() > 0
								&& localStringM != " ") {
							// System.out.println();
							localList2.add(localStringM);
						}
					}

				}

				else {
					// System.out.println(localList.get(i));
					localList2.add(localList.get(i));
				}

			}
			// System.out.println(localList2);
			stream.setList(localList2);
			localList.clear();
			localList2.clear();

			// System.out.println("SHould stop here");
		}
		stream.reset();
	}
}
