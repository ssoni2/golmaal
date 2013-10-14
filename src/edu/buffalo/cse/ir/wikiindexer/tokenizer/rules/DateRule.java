package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import java.util.ArrayList;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;


@RuleClass(className = RULENAMES.DATES)

public class DateRule implements TokenizerRule{
	public void apply(TokenStream stream){
		
		ArrayList<String> months =new ArrayList<String>();
		months.add("months");
		months.add("January");months.add("Februvary");months.add("March");
		months.add("April");months.add("May");
		months.add("June");	months.add("July");
		months.add("August");months.add("September");
		months.add("Octomber");	months.add("November");
		months.add("December");
	
		
		while(stream.hasNext())
		{
			String current = stream.next();
			if(months.contains(current))
			{	
				Integer i=months.indexOf(current);
				System.out.println(i);
				stream.previous();
				stream.set("0"+i.toString());
				
									
				if(stream.hasPrevious())
				{
					String previous = stream.previous();
					System.out.println(previous);
					if(previous=="\\d")
					{
						System.out.println("Hi");
					stream.mergeWithPrevious();
					}
					if(stream.hasNext())
					{
						if(stream.next()=="\\d\\d\\d\\d")
							stream.mergeWithNext();
						System.out.println(stream.getAllTokens());
					}
				}
			}
			
		}
		
	}
}
