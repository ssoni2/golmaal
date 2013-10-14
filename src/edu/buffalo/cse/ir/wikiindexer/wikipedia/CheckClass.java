package edu.buffalo.cse.ir.wikiindexer.wikipedia;

import java.util.ArrayList;
import java.util.List;

public class CheckClass {

	/**
	 * @param args
	 */
	
		public static void main(String[] args) { 
			
			
			int indexForTitle,i=0,j,k=0;
			 
			
			String text = "Elep==section1==tiger runs very fast ===section2=== cat is bad====i am good====hhvhvhvgcgg" ;
			
			while(true)
			{
				int IndexI=0;
				int IndexJ=0;
				int IndexK=0;

			IndexI=text.indexOf("==");
			System.out.println("Index I" + IndexI);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!New Section!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			
			IndexJ= text.indexOf("==",IndexI+3);
			System.out.println("Index J" + IndexJ);

			if(text.indexOf("==",IndexJ+3)>0)
				
				{	
				IndexK=text.indexOf("==",IndexJ+3);
				System.out.println("Index K" + IndexK);			
				System.out.println(text.substring(IndexI, IndexK));
			
				
				System.out.println(text.substring(IndexK));
				text=(text.substring(IndexK));
				IndexI=0;
				IndexJ=0;
				IndexK=0;
				
				}
			else
				{
				System.out.println(text.substring(IndexI));
				text=((text.substring(IndexI)));
				break;
				}
			
			}
			
			
		
		/*System.out.println(text.indexOf("=="));
			
		i=text.indexOf("==");
		
		j=i+2;

		
		System.out.println(text.indexOf("==", j));
+0		
		k=text.indexOf("==", j);
		
		System.out.println(text.substring(i,k));
		
		l=k+2;
		
		System.out.println(text.indexOf("==", l));
		
		m=text.indexOf("==", l);
		
		System.out.println(text.substring(l,m));

		
		*/
		
		
		
		
		}

	}


