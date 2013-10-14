package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;

@RuleClass(className = RULENAMES.HYPHEN)
public class HyphenRule implements TokenizerRule{
	

	public void apply(TokenStream stream) throws TokenizerException
	
	{
	
		ArrayList<String> localList = new ArrayList();
		ArrayList<String> localList2 = new ArrayList();

		while(stream.hasNext())
		{
			localList.add(stream.next());
			
								
		}
		
		for(int i=0 ; i <localList.size();i++)
		{
			
			String localString = localList.get(i);
			
			if(localString.matches(".*[a-zA-Z][\\-][0-9].*"))
			{
				//do nothing , sit quite..
				
			}
			else if(localString.matches(".*[a-zA-Z][\\-][a-zA-Z].*\\d.*"))
			{
				//System.out.println("1" + localString);
				//localString.replaceAll("-","");
			}
			else if(localString.matches(".*[0-9][\\-][a-zA-Z].*"))
			{
				//do nothing sit auite
			}
			
			else if(localString.matches(".*[0-9][\\-][0-9].*"))
			{
				//do nothing
			}
			else if(localString.matches(".*[a-zA-Z][\\-][a-zA-z].*"))
			{
				localString=localString.replaceAll("-"," ");

			}			
			else
			{
				//System.out.println("came here for" + localString);
				localString=localString.replaceAll("-", "");
				//System.out.println(localString);
			}
			
			localString=localString.trim();
			
			if(localString.length()>0)
			{
				localList2.add(localString);
			}
			
			
			
		}
		//System.out.println(localList2);
		stream.setList(localList2);
		
			
		
	}
	

}
