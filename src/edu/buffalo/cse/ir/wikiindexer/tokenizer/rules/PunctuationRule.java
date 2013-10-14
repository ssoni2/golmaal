package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;



@RuleClass(className = RULENAMES.PUNCTUATION)

public class PunctuationRule implements TokenizerRule {

	
	public void apply(TokenStream stream) throws TokenizerException
	
	{
		
		ArrayList<String> localList = new ArrayList();
		ArrayList<String> localList2 = new ArrayList();

		while(stream.hasNext())
		{
			localList.add(stream.next());
			
								
		}
		//System.out.println(localList);
		
		for (int i=0;i<localList.size();i++)
		{
		
		String localString = localList.get(i);
		
			
		if(localString!=null && localString.length()>0)
		{	
		
		if(!(localString.matches(".*\\d[\\.]\\d.*")))
				{	
					localString=localString.replaceAll("\\.", "");
					
					//System.out.println(localString);
				}
			if(localString!=null&&localString.length()>0)
		{
			if((!(localString.matches(".*[a-zA-Z][\\!][a-zA-Z].*")))&& (localString.charAt(0)!='!'))
			{
				localString=localString.replaceAll("\\!", "");
				
			}
		}	
			if(!(localString.matches(".*[a-zA-Z][\\?][a-zA-Z].*")))
			{
				localString=localString.replaceAll("\\?", "");
			}
		localList2.add(localString);
		}
		
		}
		
		//System.out.println(localList2);
		stream.setList(localList2);

		
		//stream.reset();
	}
	
	

}
