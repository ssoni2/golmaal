import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.EnglishStemmer;


public class Test {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		String top = "American, May, November, October, The, ani, anim, appear, ar, asteroid, base, be, becaus, book, call, caus, charact, claim, creat, critic, date, describ, develop, differ, discov, drink, dure, episod, exampl, featur, form, found, game, gener, group, ha, hi, him, human, imag, includ, it, known, later, link, mai, mani, mean, music, name, natur, number, object, observ, often, on, onli, origin, part, peopl, perform, period, person, plai, publish, ref, refer, releas, report, result, sai, said, same, scienc, scientif, seri, sever, she, show, singl, social, state, stori, term, thei, them, theori, these, through, titl, us, version, video, view, wa, what, while, ye, year, you";
		String stopWordsRemoval2[] = top.split(",");
		System.out.println(stopWordsRemoval2[0]);
		String stopWordsRemoval[] = { "a", "about", "above", "across", "after",
				"again", "against", "all", "almost", "alone", "along",
				"already", "also", "although", "always", "among", "an", "and",
				"another", "any", "anybody", "anyone", "anything", "anywhere",
				"are", "area", "areas", "around", "as", "ask", "asked",
				"asking", "asks", "at", "away", "back", "backed", "backing",
				"backs", "be", "became", "because", "become", "becomes",
				"been", "before", "began", "behind", "being", "beings", "best",
				"better", "between", "big", "both", "but", "by", "came", "can",
				"cannot", "case", "cases", "certain", "certainly", "clear",
				"clearly", "come", "could", "did", "differ", "different",
				"differently", "do", "does", "done", "down", "down", "downed",
				"downing", "downs", "during", "each", "early", "either", "end",
				"ended", "ending", "ends", "enough", "even", "evenly", "ever",
				"every", "everybody", "everyone", "everything", "everywhere",
				"face", "faces", "fact", "facts", "far", "felt", "few", "find",
				"finds", "first", "for", "four", "from", "full", "fully",
				"further", "furthered", "furthering", "furthers", "gave",
				"general", "generally", "get", "gets", "give", "given",
				"gives", "go", "going", "good", "goods", "got", "great",
				"greater", "greatest", "group", "grouped", "grouping",
				"groups", "had", "has", "have", "having", "he", "her", "here",
				"herself", "high", "higher", "highest", "him", "himself",
				"his", "how", "however", "i", "if", "important", "in",
				"interest", "interested", "interesting", "interests", "into",
				"is", "it", "its", "itself", "just", "keep", "keeps", "kind",
				"knew", "know", "known", "knows", "large", "largely", "last",
				"later", "latest", "least", "less", "let", "lets", "like",
				"likely", "long", "longer", "longest", "made", "make",
				"making", "man", "many", "may", "me", "member", "members",
				"men", "might", "more", "most", "mostly", "mr", "mrs", "much",
				"must", "my", "myself", "necessary", "need", "needed",
				"needing", "needs", "never", "new", "new", "newer", "newest",
				"next", "no", "nobody", "non", "noone", "not", "nothing",
				"now", "nowhere", "number", "numbers", "of", "off", "often",
				"old", "older", "oldest", "on", "once", "one", "only", "open",
				"opened", "opening", "opens", "or", "order", "ordered",
				"ordering", "orders", "other", "others", "our", "out", "over",
				"part", "parted", "parting", "parts", "per", "perhaps",
				"place", "places", "point", "pointed", "pointing", "points",
				"possible", "present", "presented", "presenting", "presents",
				"problem", "problems", "put", "puts", "quite", "rather",
				"really", "right", "right", "room", "rooms", "said", "same",
				"saw", "say", "says", "second", "seconds", "see", "seem",
				"seemed", "seeming", "seems", "sees", "several", "shall",
				"she", "should", "show", "showed", "showing", "shows", "side",
				"sides", "since", "small", "smaller", "smallest", "so", "some",
				"somebody", "someone", "something", "somewhere", "state",
				"states", "still", "still", "such", "sure", "take", "taken",
				"than", "that", "the", "their", "them", "then", "there",
				"therefore", "these", "they", "thing", "things", "think",
				"thinks", "this", "those", "though", "thought", "thoughts",
				"three", "through", "thus", "to", "today", "together", "too",
				"took", "toward", "turn", "turned", "turning", "turns", "two",
				"under", "until", "up", "upon", "us", "use", "used", "uses",
				"very", "want", "wanted", "wanting", "wants", "was", "way",
				"ways", "we", "well", "wells", "went", "were", "what", "when",
				"where", "whether", "which", "while", "who", "whole", "whose",
				"why", "will", "with", "within", "without", "work", "worked",
				"working", "works", "would", "xxxxxxx", "year", "years", "yet",
				"you", "young", "younger", "youngest", "your", "yours" };
		String stop = "stopwords.add(\"american\");";
		System.out.println("\"");
		System.out.println(stop);
		for(int i=1; i<stopWordsRemoval2.length;i++){
			stop = stop +  "stopwords.add(\""+stopWordsRemoval2[i].toLowerCase().trim()+"\");";
		}
		System.out.println(stop);
		
		String test = "Sourabh";
		TokenStream tstr = new TokenStream(test);
		EnglishStemmer estem = new EnglishStemmer();
		estem.apply(tstr);
		
		test=(String)tstr.getAllTokens().iterator().next();
		System.out.println(test);
		
		String s = "Sourabh";
		String s2 = new String("Sourabh");
		System.out.println(s.hashCode());
		System.out.println(s2.hashCode());
		Map map = new HashMap();
		map.put("sourabh", "sourabh".hashCode());
		map.put("soni", "soni".hashCode());
		map.put("hi", "hi".hashCode());
		System.out.println(map.get("sourabh"));
		System.out.println("sourabh".hashCode());
		
	}

}
