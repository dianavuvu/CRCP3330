/*
 * Programmer: Diana Vu
 * Date: October 5, 2020
 * Description: This is the markov generator class where I will create an algorithm that predicts the next 'T' in a sequence to make a coherent melody style. 
 * 
 */

import java.util.ArrayList;

public class MarkovOrderM<T> extends MarkovGenerator<T>{
	
	int orderM = 2; //set order of Markov chain
	ArrayList<ArrayList<T>> curSequence;
	ArrayList<ArrayList<ArrayList<T>>> uniqueAlphabetSequence; //make an arraylist (uniquealphabetsequence) that holds cursequence arraylist
	
	MarkovOrderM() {
		curSequence = new ArrayList(); //current sequence 
		uniqueAlphabetSequence = new ArrayList(); //add current sequences into transition table
	}
	
	void train(ArrayList<T> newTokens) {
//		for i = orderM -1 to (i < size of the input - 1) do 
		for(int i = orderM - 1; i <= newTokens.size() - 1; i++ ){
			
			int tokenIndex = alphabet.indexOf(newTokens.get(i)); //token index is equal to index of token in alphabet
			int rowIndex = 0;
					
//			1.	Create the current sequence (eg. curSequence) of size orderM from the input
//			Remember to start the index into the input at 0 (with this algorithm) 
//				a.	add the previous tokens to a container (eg ArrayList). 
//				b.	You may do this in a for-loop or use .subList()
//					i.	https://beginnersbook.com/2013/12/how-to-get-sublist-of-an-arraylist-with-example/
			
			for(int j = 0; j < orderM; j++) { 
				curSequence.get(j).add(newTokens.get(i));
			}
						
//			2.	Find  curSequence in uniqueAlphabetSequences
//			if curSequence is not found
			if(curSequence == -1) {
//				1. set rowIndex to the size of uniqueAlphabetSequences
				 rowIndex = uniqueAlphabetSequence.size();
				 
//				2. add the curSequence to uniqueAlphabetSequences
				 uniqueAlphabetSequence.add(curSequence);

//				3. add a new row to the transition table the size of the alphabet
				ArrayList<Integer> newRow = new ArrayList(); //initialize
				for(int j = 0; j < alphabet.size(); j++) { //create a new array that is the size of the alphabet 
					newRow.add(0);
				}
				transitionTable.add(newRow); //Then add to your transition table (the array of arrays)	
			}
						
//			3.	Find the current next token (tokenIndex)
//			{
//				tokenIndex = the next index of the token in the alphabet (i+1)
				tokenIndex = alphabet.indexOf(newTokens.get(i+1));
//					
//				if tokenIndex is not found in the alphabet
				for(int k = 0; k < alphabet.size(); k++) {
					if(tokenIndex == -1){
//						1. tokenIndex = size of the alphabet 
						tokenIndex = alphabet.size();
						
//						2. add the token to the alphabet
						alphabet.add(newTokens.get(i));
						
//						3. expand transitionTable horizontally
						for(int p = 0; p < transitionTable.size(); p++) {
							 transitionTable.get(k).add(0); //add a 0 to all of the arrays in the transition table.
						}
					}
				}
//			}

//			4.	Update the counts – since we started after the beginning, rowIndex will not be -1
//				a.	Get the row using rowIndex
//				b.	Get the column using tokenIndex
//				c.	Add one to that value retrieved from the transition table
			if(rowIndex > -1) {
				transitionTable.get(rowIndex).set(tokenIndex, transitionTable.get(rowIndex).get(tokenIndex)+1);
			}
		}
	}
}


