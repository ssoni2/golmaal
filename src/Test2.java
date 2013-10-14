import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.EnglishStemmer;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.SpecialCharRule;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.StopWordsRule;


public class Test2 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		TokenStream stream = new TokenStream("residential");
		SpecialCharRule rule = new SpecialCharRule();
		EnglishStemmer stem = new EnglishStemmer();
		StopWordsRule stop = new StopWordsRule();
		stop.apply(stream);
		stem.apply(stream);
		System.out.println(stream.next());
		System.out.println(stream.next());
	}

}
