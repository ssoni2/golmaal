package edu.buffalo.cse.ir.wikiindexer.wikipedia;

import java.util.ArrayList;
import java.util.List;

public class linkParse {

	/**
	 * @param args
	 */
	
	//String[] links = new ArrayList();
	
	static List <String> links = new ArrayList();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String text = "asbx [[Texas Lone Star State]] [[asdffg]]";
		
		
		
	
		
		//Rule1
		for (int i=0;i<text.length();i++)
		{
			
			if((text.charAt(i)=='[') && text.charAt(i+1)=='[')
			{
				int j=i+1;
				while (!((text.charAt(j)==']') && (text.charAt(j+1)==']')))
				{
					j++;
				}
				
				String localString = text;
				//System.out.println(localString);
				
				int indexOf = localString.indexOf('|');
				int indexOf2= localString.indexOf(':');
				
				if (indexOf== -1 && indexOf2 == -1)
				{
					
					String link= localString.substring(i+2,j);
					//System.out.println(link);
					link=link.replaceAll(" ", "_");
					System.out.println(link);
					StringBuilder copyString = new StringBuilder(text);
					copyString=copyString.delete(i, i+2);
					copyString=copyString.delete(j-2, j);
					text=copyString.toString();
					links.add(text);
					links.add(link);
					
					//System.out.println(text);
					
				}
				
			}
		}
		System.out.println(text);

	}

}
