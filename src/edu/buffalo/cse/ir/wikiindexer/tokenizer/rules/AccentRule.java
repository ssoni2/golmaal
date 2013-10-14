package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.EnglishStemmer.Stemmer;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;
@RuleClass(className = RULENAMES.ACCENTS)
public class AccentRule implements TokenizerRule

	{
	public void apply(TokenStream stream) throws TokenizerException
	{
		

		ArrayList<String> localList = new ArrayList();
		ArrayList<String> localList2 = new ArrayList();

		if (stream != null) {
			String token;
			Stemmer s;
			while (stream.hasNext()) 
			{ 
				localList.add(stream.next());
			}
				
			for (int i=0  ; i <localList.size();i++)
			{
				String localString = localList.get(i);
				
				
				if (localString!=null && localString.length()>0)
					{
					localString = Normalizer.normalize(localString, Form.NFD);
				    // \removing diacritics
					//System.out.println(localString);
				    localString = localString.replaceAll("[^\\p{ASCII}]+", "");
				    //System.out.println(localString);
				    if(localString.length()>0)
				    {
				    localString=localString.substring(0,1)+localString.substring(1, localString.length()).toLowerCase();
				    //System.out.println(localString);
				    localList2.add(localString);
				    }
					}
				
			}
			
			stream.setList(localList2);
			
		
		}
			stream.reset();
		}
	}