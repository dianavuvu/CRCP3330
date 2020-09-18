/*
 * Programmer: Diana Vu
 * Date: September 21, 2020
 * Description: This is the markov generator class where I will create an algorithm that predicts the next 'T' in a sequence to make a coherent melody style. 
 * 
 */

import java.util.ArrayList;

public class MarkovGenerator<T> extends ProbabilityGenerator<T>{
	
	MarkovGenerator(){
		super();
	}
	
	T generate() {
		T newToken = null;
		
		return newToken;
	}
	
	void train(ArrayList<T> newTokens) {
		//to declare
		ArrayList<ArrayList<Integer>> transitionTable;

		//to create the ArrayList
		transitionTable = new ArrayList();
		
		int lastIndex = -1;
		
		for(int i = 0; i <= newTokens.size(); i++) { //for each token in input array
			int tokenIndex = (int)alphabet.get(i); //token index is equal to index of token in alphabet
			
			if ((int)alphabet.get(i) == -1) {
				tokenIndex = alphabet.size(); //tokenIndex = size of alphabet
				
				//add a new row to the transition table (expand vertically)
				ArrayList<Integer> newRow = new ArrayList();
				for(int j = 0; j <= alphabet.size(); j++) { //create a new array that is the size of the alphabet 
					newRow.add(0);
				}
				transitionTable.add(newRow); //Then add to your transition table (the array of arrays)
		
				//add a new column (expand horizontally)
				for(int k = 0; k <= transitionTable.size(); k++) {
					 transitionTable.get(k).add(0); //add a 0 on to all of the arrays in the transition table.
				}
		
				alphabet.add(alphabet.get(i)); //add the token to the alphabet array 
			}
			
		    //now add the counts to the transition table
			if(lastIndex > -1) { //that is, we have a previous token so its not the 1st time thru
				//1.	Use lastIndex to get the correct row (array) in your transition table.
				//2.	Use the tokenIndex to index the correct column (value of the row you accessed)
				//3.	Add 1 to that value.
				transitionTable.get(lastIndex).get(tokenIndex).add(1);
			}
			
			lastIndex = tokenIndex; //setting current to previous for next round
		}
	}
	
	ArrayList<T> generate(int length, T initToken) {
		ArrayList<T> newSequence = new ArrayList<T>();
//		for (int i = 0; i < length; i++) {
//			newSequence.add(generate());
//		}
		return newSequence;
	}
	
	ArrayList<T> generate(int length) {
		ArrayList<T> newSequence = new ArrayList<T>();
//		for (int i = 0; i < length; i++) {
//			newSequence.add(generate());
//		}
		return newSequence;
	}
}
