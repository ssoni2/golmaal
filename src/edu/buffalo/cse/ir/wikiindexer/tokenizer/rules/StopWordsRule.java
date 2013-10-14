package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;

@RuleClass(className = RULENAMES.STOPWORDS)
public class StopWordsRule implements TokenizerRule {
	private static Set<String> stopwords = new HashSet<String>();
	{
		stopwords.add("Default");stopwords.add("External");stopwords.add("Greek");stopwords.add("Internet");stopwords.add("May");stopwords.add("New");stopwords.add("References");stopwords.add("The");stopwords.add("This");stopwords.add("United");stopwords.add("University");stopwords.add("about");stopwords.add("after");stopwords.add("alcohol");stopwords.add("all");stopwords.add("also");stopwords.add("and");stopwords.add("any");stopwords.add("are");stopwords.add("been");stopwords.add("being");stopwords.add("between");stopwords.add("but");stopwords.add("called");stopwords.add("can");stopwords.add("discovered");stopwords.add("during");stopwords.add("example");stopwords.add("film");stopwords.add("first");stopwords.add("for");stopwords.add("from");stopwords.add("game");stopwords.add("had");stopwords.add("has");stopwords.add("have");stopwords.add("her");stopwords.add("him");stopwords.add("his");stopwords.add("into");stopwords.add("its");stopwords.add("known");stopwords.add("like");stopwords.add("links");stopwords.add("made");stopwords.add("many");stopwords.add("may");stopwords.add("more");stopwords.add("most");stopwords.add("name");stopwords.add("named");stopwords.add("new");stopwords.add("not");stopwords.add("often");stopwords.add("one");stopwords.add("only");stopwords.add("other");stopwords.add("out");stopwords.add("over");stopwords.add("people");stopwords.add("ref");stopwords.add("said");stopwords.add("science");stopwords.add("scientific");stopwords.add("series");stopwords.add("show");stopwords.add("some");stopwords.add("song");stopwords.add("such");stopwords.add("than");stopwords.add("that");stopwords.add("the");stopwords.add("their");stopwords.add("them");stopwords.add("then");stopwords.add("theory");stopwords.add("there");stopwords.add("these");stopwords.add("they");stopwords.add("this");stopwords.add("through");stopwords.add("time");stopwords.add("two");stopwords.add("use");stopwords.add("used");stopwords.add("video");stopwords.add("was");stopwords.add("were");stopwords.add("what");stopwords.add("when");stopwords.add("where");stopwords.add("which");stopwords.add("while");stopwords.add("who");stopwords.add("will");stopwords.add("with");stopwords.add("would");stopwords.add("xxxxxxx");stopwords.add("yes");stopwords.add("you");
		stopwords.add("American");stopwords.add("April");stopwords.add("August");stopwords.add("December");stopwords.add("February");stopwords.add("For");stopwords.add("However");stopwords.add("January");stopwords.add("John");stopwords.add("July");stopwords.add("June");stopwords.add("March");stopwords.add("November");stopwords.add("October");stopwords.add("Philosophy");stopwords.add("Press");stopwords.add("Science");stopwords.add("See");stopwords.add("September");stopwords.add("States");stopwords.add("The");stopwords.add("World");stopwords.add("York");stopwords.add("YouTube");stopwords.add("against");stopwords.add("around");stopwords.add("asteroid");stopwords.add("based");stopwords.add("became");stopwords.add("because");stopwords.add("before");stopwords.add("book");stopwords.add("both");stopwords.add("character");stopwords.add("could");stopwords.add("created");stopwords.add("date");stopwords.add("did");stopwords.add("different");stopwords.add("does");stopwords.add("drinking");stopwords.add("each");stopwords.add("early");stopwords.add("episode");stopwords.add("even");stopwords.add("example");stopwords.add("found");stopwords.add("group");stopwords.add("how");stopwords.add("human");stopwords.add("image");stopwords.add("including");stopwords.add("large");stopwords.add("later");stopwords.add("make");stopwords.add("music");stopwords.add("must");stopwords.add("number");stopwords.add("objects");stopwords.add("original");stopwords.add("own");stopwords.add("part");stopwords.add("period");stopwords.add("philosophy");stopwords.add("popular");stopwords.add("ref");stopwords.add("released");stopwords.add("same");stopwords.add("science");stopwords.add("second");stopwords.add("see");stopwords.add("set");stopwords.add("several");stopwords.add("she");stopwords.add("should");stopwords.add("similar");stopwords.add("since");stopwords.add("single");stopwords.add("social");stopwords.add("story");stopwords.add("television");stopwords.add("term");stopwords.add("the");stopwords.add("those");stopwords.add("three");stopwords.add("title");stopwords.add("under");stopwords.add("using");stopwords.add("various");stopwords.add("version");stopwords.add("very");stopwords.add("way");stopwords.add("web");stopwords.add("website");stopwords.add("well");stopwords.add("within");stopwords.add("without");stopwords.add("work");stopwords.add("year");stopwords.add("years");
		//stopwords.add("David");stopwords.add("English");stopwords.add("Gangnam");stopwords.add("History");stopwords.add("However");stopwords.add("ISBN");stopwords.add("Japanese");stopwords.add("Julian");stopwords.add("Music");stopwords.add("One");stopwords.add("Press");stopwords.add("Retrieved");stopwords.add("Some");stopwords.add("South");stopwords.add("The");stopwords.add("There");stopwords.add("They");stopwords.add("United");stopwords.add("among");stopwords.add("and");stopwords.add("another");stopwords.add("appeared");stopwords.add("appears");stopwords.add("ascnode");stopwords.add("back");stopwords.add("become");stopwords.add("began");stopwords.add("caption");stopwords.add("characters");stopwords.add("common");stopwords.add("considered");stopwords.add("culture");stopwords.add("density");stopwords.add("described");stopwords.add("dimensions");stopwords.add("down");stopwords.add("due");stopwords.add("eccentricity");stopwords.add("end");stopwords.add("evidence");stopwords.add("example");stopwords.add("fact");stopwords.add("featured");stopwords.add("form");stopwords.add("games");stopwords.add("general");stopwords.add("given");stopwords.add("history");stopwords.add("idea");stopwords.add("inclination");stopwords.add("include");stopwords.add("just");stopwords.add("knowledge");stopwords.add("language");stopwords.add("last");stopwords.add("law");stopwords.add("legend");stopwords.add("life");stopwords.add("man");stopwords.add("meananomaly");stopwords.add("media");stopwords.add("million");stopwords.add("much");stopwords.add("natural");stopwords.add("nature");stopwords.add("never");stopwords.add("news");stopwords.add("now");stopwords.add("nullInfobox");stopwords.add("off");stopwords.add("order");stopwords.add("our");stopwords.add("perihelion");stopwords.add("person");stopwords.add("planet");stopwords.add("point");stopwords.add("public");stopwords.add("published");stopwords.add("rather");stopwords.add("received");stopwords.add("ref");stopwords.add("reported");stopwords.add("science");stopwords.add("seen");stopwords.add("semimajor");stopwords.add("shows");stopwords.add("site");stopwords.add("sometimes");stopwords.add("still");stopwords.add("study");stopwords.add("system");stopwords.add("the");stopwords.add("theories");stopwords.add("until");stopwords.add("usually");stopwords.add("video");stopwords.add("videos");stopwords.add("word");stopwords.add("world");stopwords.add("written");


	}

	public void apply(TokenStream stream) throws TokenizerException {
		// TODO Auto-generated method stub

		ArrayList<String> localList = new ArrayList<String>();
		while (stream.hasNext()) {
			String localString = stream.next();
			if (localString.length()>2 && !stopwords.contains(localString)) {
				// System.out.println(count);
				localList.add(localString.trim());
			}
		}
		stream.setList(localList);
		
		/*ArrayList<String> localList = new ArrayList<String>();
		ArrayList<String> localList2 = new ArrayList<String>();
		while (stream.hasNext()) {
			localList2.add(stream.next());
			System.out.println(stream.hasNext());

		}
		for (int i = 0; i < localList2.size(); i++) {
			String localString = localList2.get(i);
			//if (!swords.contains(localString)) {
				// System.out.println(count);
				localList.add(localString);
			//}
		}
		stream.setList(localList);*/
	}
}
