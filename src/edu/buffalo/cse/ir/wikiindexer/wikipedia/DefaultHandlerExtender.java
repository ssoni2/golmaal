package edu.buffalo.cse.ir.wikiindexer.wikipedia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class DefaultHandlerExtender extends DefaultHandler {

	
	//private String FieldExists="invalid";
	
	
	private int idFromXML=0;
	
	private String timestampFromXML="NULL";
	
	private String authorFromXML="NULL";
	
	private String title="NULL";
	
	private String text="NULL";

	public int getIdFromXML() {
		return idFromXML;
	}

	public void setIdFromXML(int idFromXML) {
		this.idFromXML = idFromXML;
	}

	public String getTimestampFromXML() {
		return timestampFromXML;
	}

	public void setTimestampFromXML(String timestampFromXML) {
		this.timestampFromXML = timestampFromXML;
	}

	public String getAuthorFromXML() {
		return authorFromXML;
	}

	public void setAuthorFromXML(String authorFromXML) {
		this.authorFromXML = authorFromXML;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//private int counterForId=0;
	
	/*
	private List <DefaultHandlerExtender> DefaultHandlerObjectList = new ArrayList ();
	
	//private String Value;
	
	@Override
	public void characters(char[] Characters, int arg1, int arg2) throws SAXException {
		// TODO Auto-generated method stub
		
		
		if(FieldExists.equalsIgnoreCase("id"))
			
		{
			
			//System.out.println(new String(Characters, arg1, arg2));
			
			idFromXML= Integer.parseInt(new String(Characters, arg1, arg2));
		} 
		
		else if(FieldExists.equalsIgnoreCase("author"))
			
		{
			//System.out.println(new String(Characters, arg1, arg2));
			
			authorFromXML = new String(Characters, arg1, arg2);
		
		}
		
		else if(FieldExists.equalsIgnoreCase("title"))
			
		{
			//System.out.println(new String(Characters, arg1, arg2));

			title= new String(Characters, arg1, arg2);
		}
		
		else if (FieldExists.equalsIgnoreCase("timestamp"))
			
		{
			//System.out.println(new String(Characters, arg1, arg2));

			timestampFromXML= new String(Characters, arg1, arg2);
		}
		
	else if (FieldExists.equalsIgnoreCase("text"))
			
		{
			//System.out.println(new String(Characters, arg1, arg2));

			text= new String(Characters, arg1, arg2);
		}
		 
	}

	@Override
	public void endElement(String arg0, String arg1, String arg2)
			throws SAXException {
		
		if (arg2.equalsIgnoreCase("id") || arg2.equalsIgnoreCase("title")|| arg2.equalsIgnoreCase("ip") || 
				arg2.equalsIgnoreCase("username") || arg2.equalsIgnoreCase("timestamp") || arg2.equalsIgnoreCase("text") )
		{
			FieldExists = "invalid";

		}
		
		
		if (arg2.equalsIgnoreCase("page"))
		{
			
			
			DefaultHandlerExtender DefaultHandlerExtenderObj = new DefaultHandlerExtender();
			DefaultHandlerExtenderObj.setIdFromXML(idFromXML);
			DefaultHandlerExtenderObj.setTitle(title);
			DefaultHandlerExtenderObj.setText(text);
			DefaultHandlerExtenderObj.setAuthorFromXML(authorFromXML);
			DefaultHandlerExtenderObj.setTimestampFromXML(timestampFromXML);
			
			DefaultHandlerObjectList.add(DefaultHandlerExtenderObj);
			
			
			System.out.println(DefaultHandlerObjectList);
		}
		
		
		
	}

	@Override
	public void startElement(String arg0, String arg1, String arg2,
			Attributes arg3) throws SAXException {
		
			
		
		
		
		
		if (arg2.equalsIgnoreCase("id") & counterForId ==0 )
			
		{
			counterForId=counterForId+1;
							
			FieldExists = "id";
			
		} 
		
		else if ((arg2.equalsIgnoreCase("username")) || (arg2.equalsIgnoreCase("ip")))
		{
			//		System.out.println("Inside Start");

			FieldExists = "author";
			
		}
		
		else if (arg2.equalsIgnoreCase("timestamp"))
		{
			//		System.out.println("Inside Start");

			FieldExists = "timestamp";
			
		}
		
		else if (arg2.equalsIgnoreCase("title"))
		{
			//		System.out.println("Inside Start");

			FieldExists = "title";
			
		}
		
		else if (arg2.equalsIgnoreCase("text"))
		{
			//		System.out.println("Inside Start");

			FieldExists = "text";
			
		}
		// TODO Auto-generated method stub
		
	}

	public void setIdFromXML(int idFromXML) {
		this.idFromXML = idFromXML;
	}

	public void setTimestampFromXML(String timestampFromXML) {
		this.timestampFromXML = timestampFromXML;
	}

	public void setAuthorFromXML(String authorFromXML) {
		this.authorFromXML = authorFromXML;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setText(String text) {
		this.text = text;
	}
*/

	
}
