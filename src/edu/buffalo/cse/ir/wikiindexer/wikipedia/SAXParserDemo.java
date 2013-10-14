
package edu.buffalo.cse.ir.wikiindexer.wikipedia;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.html.HTMLDocument.Iterator;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

import edu.buffalo.cse.ir.wikiindexer.parsers.Parser;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.ApostropheRule;

public class SAXParserDemo {

	private static Properties props;
	/*public static void main(String[] args) {
		Parser handler = new Parser(props);
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse("five_entries.xml", handler);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/
	
	
	private static List <WikipediaDocument> WikiDocsList = new ArrayList<WikipediaDocument>();
	
	public static void main(String[] args) throws ParseException 
	{
		
		
		Parser myObj1= new Parser(props);
	
		myObj1.parse("no use",WikiDocsList );
		
	}
		/*String toCheck = "new";
		System.out.println(toCheck.substring(0, 1));
		
		
		
		ArrayList<String> localStringList = new ArrayList();
		
		ListIterator<String> localStringListIter = localStringList.listIterator();
		
		localStringList.add("HI");
		localStringList.add("WCE");
		localStringList.add("miss");
		localStringList.add("you");
		
		localStringListIter = localStringList.listIterator();

		
		while((localStringListIter.hasNext()))
		{	
			if(localStringListIter.next()=="WCE")
			{
				//localStringListIter.add("fafa");
		
				localStringListIter.remove();
				System.out.println(localStringListIter.next());
				localStringListIter.remove();
				break;
				
				localStringListIter.remove();
				localStringListIter.add("timepass");
				System.out.println(localStringListIter.next());
				//	localStringListIter.remove();
				
				
				System.out.println(localStringListIter.next());
				localStringListIter.previous();
				System.out.println(localStringListIter.previous());
				localStringListIter.next();
				
				
				
				//localStringListIter.remove();
				
				//System.out.println(localStringListIter.next());
				//System.out.println(localStringListIter.next());
				//localStringListIter.next();
				//localStringListIter.next();
				//System.out.println(catched);


			}
			//System.out.println(localStringListIter.next());
			
		}
		
		System.out.println(localStringList);
		System.out.println();
		
		
		//StringTokenizer tokens = new StringTokenizer("Infy Rocks");
		
		//ArrayList<String> localStringList = new ArrayList();
		//String localString;
		StringTokenizer tokenStream =new StringTokenizer("This is my Infy this is your infy");
		
		while (tokenStream.hasMoreTokens())
		{
			String check = tokenStream.nextToken();
			//System.out.println(check);
			 //localStringList.add(tokenStream.nextToken());
			
		}
		
		
		SAXParserDemo demoObj = new SAXParserDemo();	
		TokenStream obj = new TokenStream((String)null);
		obj.append("tie","max","gone");
		
		ArrayList<String> localStringList2 = new ArrayList();
		localStringList2.add("HI");
		localStringList2.add("WCE");
		
		ListIterator<String> toitit = localStringList2.listIterator();
		
		System.out.println(toitit.next());
		System.out.println(toitit.next());
		System.out.println(toitit.next());
		
		
		String localString = "" ;
		localString = localString.replaceAll("'d"," would");
		System.out.println(localString);
		
				
		
		

		//localStringList.addAll(arg0)
		
		String tryString = "7777-7777";
		
		tryString=tryString.replaceAll("[-+]?[.]?\\s?[0-9].*","");
		
//		[-+]?([0-9]*\.[0-9]+|[0-9]+
		
		//int result=Integer.parseInt(tryString);
		
		
		
		System.out.println(tryString);
				
		
		
		    // we will report on each separate character, to show you how this works
		   // String text = token.substring(i, i + 1);
		    // normalizing
		    
			
		System.out.println(localString.length());
		for(int i=0;i<localString.length();i++)
		{
		
		String decomposed = Normalizer.normalize(localString, Form.NFD);
		    // removing diacritics
			
			System.out.println(decomposed);
		    String removed = decomposed.replaceAll("[^\\p{ASCII}]+", "");
		    
		    removed=removed.toLowerCase();
		    
		    System.out.println(removed);
		}
		String name="B-786 6-712 B-ABC";
		
		
		//name=name.replaceAll("[a-zA-Z]-[a-zA-Z]"," " );
		
		
		
		Pattern hyphenRule  = Pattern.compile("(.*[a-zA-z])-([a-zA-Z].*)");
		
		Pattern hyphenRule2 =  Pattern.compile("(.*[0-9])-([0-9].*)");
		
		Matcher hyphenMatch = hyphenRule.matcher(name);
		
		Matcher hyphenMatch2 = hyphenRule2.matcher(name);
		
		if(hyphenMatch.find()==true)
		{
			//System.out.println("visit1");
			name=(hyphenMatch.group(1)+" "+hyphenMatch.group(2));
			
		}
		
		else if(hyphenMatch2.find()==true)
		{
			System.out.println("visit");
			name=(hyphenMatch2.group(1)+" "+hyphenMatch2.group(2));
			
		}
		
		
		
	}
		
		
	
	
		
		//Pattern hypenRule = Pattern.compile(arg0)
		
	//	System.out.println(name);
		
		//Iterator checkIt = name.iterator();
		
		
		String check = "my test funda";
		
		System.out.println(check.length());
		
		System.out.println(check.charAt(12));
				
				
	//	WikipediaParser.parseTemplates("{{UK-singer-stub}}njbj{{sgsgsg}}avavav");		
		//WikipediaParser.parseTagFormatting("<tags>ankur<bags>joshi");
		
		
		
		String testing = "Watch the <tag/> disappear <tags> ankur Joshi </bags> &lt;painful attr1='yes' attr2='no'<tag1> &gt;Did you get me right?&lt;/pain&gt;";
		
		testing=testing.replaceAll(" <", "<");
		testing=testing.replaceAll("> ",">");
		
		//int i=0;
		
	for (int i=0;i <testing.length();i++)	
		
		
	{
		
		if (testing.charAt(i)=='<')
		{
			//System.out.println("here");
			
			int j=i+1;
			
			while((j<testing.length())&&(testing.charAt(j)!='>'))
			{
				//System.out.println(i);
				j++;
			}
				//System.out.println(j);
				//System.out.println(testing.charAt(j));
			testing = testing.substring(0, i)+testing.substring(j+1,testing.length());
			
			//System.out.println();
		}
		
		
		else 
			
		{
		
				//System.out.println(testing);
		
				
		
			
			while((i<testing.length())&&(testing.charAt(i)!='<'))
						{
							//i++;
						
							//System.out.println("visited");
								int j = i+1;
								
								try{
								while((j<testing.length())&&(testing.charAt(j)!='>'))
								{
									j++;
								}
								}
								catch(Exception e )
										{
									System.out.println("here i am");
										}
								System.out.println(j);
								System.out.println(i);
								//testing = testing.substring(0,i)+testing.substring(j+1, testing.length());

							i++;	
								
						}
						
					
					
					
		
					System.out.println(testing);
		
		
						}
		int i = testing.indexOf("<");
		int j = testing.indexOf(">");
		
		testing = String.
		
		System.out.println(testing.indexOf(">"));

		
	}
	
	
	

	int l = testing.indexOf("&lt;");
	int m =testing.indexOf("&gt;");
	
	System.out.println(testing.indexOf("&lt;"));
	System.out.println(testing.indexOf("&gt;"));
	System.out.println(testing.charAt(45));
	System.out.println(testing.substring(l, m+4));
	
	testing = testing.replaceAll(testing.substring(l, m+4),"");
	
	System.out.println(testing);

	
	
	while(testing.indexOf("&lt;")!=-1)
	{
		int l = testing.indexOf("&lt;");
		int m =testing.indexOf("&gt;");
		
		testing = testing.replaceAll(testing.substring(l, m+4),"");
		
		System.out.println(testing);

	}
	
	System.out.println(testing);

	
	
	for (int i=0;i<testing.length();i++)
	{
		
		String newText;
		Pattern newPattern = Pattern.compile("&lt");
		Matcher match= newPattern.matcher(testing);		
			
		
		if(match.matches()==true)
		
		{
			System.out.println("visit");
			int l = testing.indexOf("&lt");
			int m =testing.lastIndexOf("&gt");
			
			testing= testing.substring(m+1, testing.length());
			
			
		}
		
		
		if(testing.indexOf("&lt;")>-1)
		{
			
			int l = testing.lastIndexOf("&lt;");
			int m= testing.lastIndexOf("&gt;");
				
				if(l==0)
				{
					newText = testing.substring(m+1,testing.length());

				}
				
				else if (m==(testing.length()-1))
				{
					;
					
				}
				else
				{
					newText = testing.substring(0, l-1) + testing.substring(m+1, testing.length());
				}

		}
		
		
		//System.out.println(testing.indexOf("&gt;"));
		
		
		
	}
	
	System.out.println(testing);

	*/
	
	//*/
	

	
	
	
}
