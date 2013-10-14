/**
 * 
 */
package edu.buffalo.cse.ir.wikiindexer.tokenizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents a stream of tokens as the name suggests. It wraps the
 * token stream and provides utility methods to manipulate it
 * 
 * @author nikhillo
 * 
 */
public class TokenStream implements Iterator<String> {
	// List<String> localList = new ArrayList(<String>);

	LinkedList<String> localList = new LinkedList<String>();
	double localPointer = -0.5;
	List<String> tempList = new ArrayList<String>();

	/**
	 * Default constructor
	 * 
	 * @param bldr
	 *            : THe stringbuilder to seed the stream
	 */
	public TokenStream(StringBuilder bldr) {
		// TODO: Implement this method
	}

	/**
	 * Overloaded constructor
	 * 
	 * @param bldr
	 *            : THe stringbuilder to seed the stream
	 */
	public TokenStream(String string) {
		// TODO: Implement this method
		localList = new LinkedList<String>();
		localPointer = -0.5;
		tempList = new ArrayList<String>();
		
		if (string != null && string.length() > 0) {
			localList.add(string);
		}

		else {
			// do nothing
		}
	}

	/**
	 * Method to append tokens to the stream
	 * 
	 * @param tokens
	 *            : The tokens to be appended
	 */
	public void append(String... tokens) {
		// TODO: Implement this method

		if (tokens != null) {

			for (String str : tokens) {

				if (str != null && str.length() > 0)

				{
					localList.add(str);
				}

			}
		}
	}

	/**
	 * Method to retrieve a map of token to count mapping This map should
	 * contain the unique set of tokens as keys The values should be the number
	 * of occurrences of the token in the given stream
	 * 
	 * @return The map as described above, no restrictions on ordering
	 *         applicable
	 */
	public Map<String, Integer> getTokenMap() {
		// TODO: Implement this method
		Map<String, Integer> localMap = new HashMap<String, Integer>();
		for (int count = 0; count < localList.size(); count++)

		{
			String str = localList.get(count);
			if (localMap.containsKey(str)) {
				localMap.put(str, localMap.get(str) + 1);
			} else {
				localMap.put(str, 1);
			}

		}

		if (!localMap.isEmpty())
			return localMap;
		else
			return null;

	}

	/**
	 * Method to get the underlying token stream as a collection of tokens
	 * 
	 * @return A collection containing the ordered tokens as wrapped by this
	 *         stream Each token must be a separate element within the
	 *         collection. Operations on the returned collection should NOT
	 *         affect the token stream
	 */
	public Collection<String> getAllTokens() {
		// TODO: Implement this method

		/*
		 * Map<String,Integer> localMap2 = new HashMap<String,Integer>();
		 * localMap2=getTokenMap();
		 * 
		 * Map<String,Integer> map = new TreeMap<String,Integer>();
		 * map.putAll(localMap2);
		 */

		// localList.
		// System.out.println(localList);

		if (localList.isEmpty()) {
			return null;
		}
		return localList;

	}

	/**
	 * Method to query for the given token within the stream
	 * 
	 * @param token
	 *            : The token to be queried
	 * @return: THe number of times it occurs within the stream, 0 if not found
	 */
	public int query(String token) {
		// TODO: Implement this method
		Map<String, Integer> localMap2 = new HashMap<String, Integer>();
		for (int count = 0; count < localList.size(); count++)

		{
			String str = localList.get(count);
			if (localMap2.containsKey(str)) {
				localMap2.put(str, localMap2.get(str) + 1);
			} else {
				localMap2.put(str, 1);
			}

		}

		if (localMap2.containsKey(token)) {
			return localMap2.get(token);
		}

		else
			return 0;

	}

	/**
	 * Iterator method: Method to check if the stream has any more tokens
	 * 
	 * @return true if a token exists to iterate over, false otherwise
	 */
	public boolean hasNext() {
		// TODO: Implement this method
		if (localList != null) {
			if (localPointer >= -0.5 && localPointer <= localList.size() - 1.5) {
				return true;
			} else
				return false;
		}
		return false;
	}

	/**
	 * Iterator method: Method to check if the stream has any more tokens
	 * 
	 * @return true if a token exists to iterate over, false otherwise
	 */
	public boolean hasPrevious() {
		// TODO: Implement this method
		if (localList != null) {
			if (localPointer >= 0.5 && localPointer <= localList.size() - 0.5) {
				return true;
			} else
				return false;
		}
		return false;
	}

	/**
	 * Iterator method: Method to get the next token from the stream Callers
	 * must call the set method to modify the token, changing the value of the
	 * token returned by this method must not alter the stream
	 * 
	 * @return The next token from the stream, null if at the end
	 */
	public String next() {

		try {
			localPointer = localPointer + 1;
			// System.out.println("Sob 217 : "+localPointer);
			// System.out.println("Sob 217 : "+(int)(localPointer-0.5));
			return localList.get((int) (localPointer - 0.5));

		} catch (IndexOutOfBoundsException e) {
			return null;
		}
		// TODO: Implement this method

	}

	/**
	 * Iterator method: Method to get the previous token from the stream Callers
	 * must call the set method to modify the token, changing the value of the
	 * token returned by this method must not alter the stream
	 * 
	 * @return The next token from the stream, null if at the end
	 */
	public String previous() {
		// TODO: Implement this method

		if ((localPointer >= 0.5) && (localPointer <= localList.size() - 0.5)) {
			localPointer = localPointer - 1;
			return localList.get((int) (localPointer + 0.5));

		} else {
			return null;
		}

	}

	/**
	 * Iterator method: Method to remove the current token from the stream
	 */
	public void remove() {

		if (localList != null) {
			if (localPointer >= -0.5 && localPointer < (localList.size() - 0.5)) {
				// localPointer=(localPointer+1);
				// System.out.println(localPointer);
				// int X = (int)(localPointer+0.5);
				// System.out.println(localList.get((int)(localPointer-0.5)));
				localList.remove((int) (localPointer + 0.5));
				// System.out.println(localList.get(0));

				// localPointer=X;
			}

		}

		// TODO: Implement this method

	}

	/**
	 * Method to merge the current token with the previous token, assumes
	 * whitespace separator between tokens when merged. The token iterator
	 * should now point to the newly merged token (i.e. the previous one)
	 * 
	 * @return true if the merge succeeded, false otherwise
	 */
	public boolean mergeWithPrevious() {
		// TODO: Implement this method

		if (!(localList.isEmpty()))

		{
			if (localPointer >= 0.5 && localPointer <= localList.size() - 1.5) {
				String previous = localList.get((int) (localPointer - 0.5));
				// System.out.println(previous);
				String next = localList.get((int) (localPointer + 0.5));
				// System.out.println(next);
				// System.out.println(localList.get((int)(localPointer+0.5)));
				localList.remove((int) (localPointer + 0.5));
				localPointer = localPointer - 1;
				// System.out.println(localPointer);
				localList
						.set((int) (localPointer + 0.5), previous + " " + next);
				// System.out.println("I am AT" +localPointer);
				// System.out.println(localPointer+0.5);
				// System.out.println(localList.get((int)(localPointer+0.5)));
				return true;
			}
		}

		return false;
	}

	/**
	 * Method to merge the current token with the next token, assumes whitespace
	 * separator between tokens when merged. The token iterator should now point
	 * to the newly merged token (i.e. the current one)
	 * 
	 * @return true if the merge succeeded, false otherwise
	 */
	public boolean mergeWithNext() {

		if (!(localList.isEmpty())) {
			if (localPointer >= -0.5
					&& localPointer <= (localList.size() - 2.5)) {
				String first = localList.get((int) (localPointer + 0.5));
				String second = localList.get((int) (localPointer + 1.5));
				localList.remove((int) (localPointer + 0.5));
				localList.set((int) (localPointer + 0.5), first + " " + second);
				return true;
			}

		}
		// TODO: Implement this method
		return false;
	}

	/**
	 * Method to replace the current token with the given tokens The stream
	 * should be manipulated accordingly based upon the number of tokens set It
	 * is expected that remove will be called to delete a token instead of
	 * passing null or an empty string here. The iterator should point to the
	 * last set token, i.e, last token in the passed array.
	 * 
	 * @param newValue
	 *            : The array of new values with every new token as a separate
	 *            element within the array
	 */
	public void set(String... newValue) {
		// TODO: Implement this method
		tempList.clear();
		
		if (newValue != null && localList.size() > 0) {
			for (String str : newValue) {
				if (str != null && str.length() > 0) {

					tempList.add(str);

				}
			}

			if (!(tempList.isEmpty())) {
				localPointer = localPointer; 

				if (!(localList.isEmpty())
						&& localPointer <= (int) (localList.size() - 0.5)) {
					localList.remove((int) (localPointer+.5));
				}

				localList.addAll((int) (localPointer+.5), tempList);

				localPointer = localPointer + (int) (tempList.size()-1);
			}
		}
	}

	/**
	 * Iterator method: Method to reset the iterator to the start of the stream
	 * next must be called to get a token
	 */
	public void reset() {

		localPointer = -0.5;
		// TODO: Implement this method
	}

	/**
	 * Iterator method: Method to set the iterator to beyond the last token in
	 * the stream previous must be called to get a token
	 */
	public void seekEnd() {

		localPointer = localList.size() - 0.5;
	}

	/**
	 * Method to merge this stream with another stream
	 * 
	 * @param other
	 *            : The stream to be merged
	 */
	public void merge(TokenStream other) {

		// System.out.println(other);
		// System.out.println("other size" + other.getAllTokens());
		if (other != null && other.hasNext()) {
			// System.out.println("reached");
			localList.addAll(other.getAllTokens());
		}
		// TODO: Implement this method
	}

	public void setList(ArrayList<String> localList2) {
		localList.clear();
		localPointer = -0.5;
		localList.addAll(localList2);

	}
}
