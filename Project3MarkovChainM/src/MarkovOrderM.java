/*
 * Programmer: Diana Vu
 * Date: October 5, 2020
 * Description: This is the markov generator class where I will create an algorithm that predicts the next 'T' in a sequence to make a coherent melody style. 
 * 
 */

import java.util.ArrayList;

public class MarkovOrderM<T> extends MarkovGenerator<T>{
	
	MarkovOrderM() {
		//int orderM = curSequence.size(); //set order of Markov chain
	}
	
	
	void train(ArrayList<T> newTokens) {
//		for i = orderM -1 to (i < size of the input - 1) do 
//		for(int i = orderM - 1; i < newTokens.size() - 1; i++ ){
//					
//			1.	Create the current sequence (eg. curSequence) of size orderM from the input
//			Remember to start the index into the input at 0 (with this algorithm) 
//				a.	add the previous tokens to a container (eg ArrayList). 
//				b.	You may do this in a for-loop or use .subList()
//					i.	https://beginnersbook.com/2013/12/how-to-get-sublist-of-an-arraylist-with-example/
//						
//			2.	Find  curSequence in uniqueAlphabetSequences
//			if curSequence is not found
//			{
//				1. set rowIndex to the size of uniqueAlphabetSequences
//						
//				2. add the curSequence to uniqueAlphabetSequences
//
//				3. add a new row to the transition table the size of the alphabet
//			}
//
//			3.	Find the current next token (tokenIndex)
//			{
//				tokenIndex = the next index of the token in the alphabet (i+1)
//					
//				if tokenIndex is not found in the alphabet
//				{
//					1. tokenIndex = size of the alphabet 
//					2. add the token to the alphabet
//					3. expand transitionTable horizontally
//				}
//			}
//					
//			4.	Update the counts – since we started after the beginning, rowIndex will not be -1
//				a.	Get the row using rowIndex
//				b.	Get the column using tokenIndex
//				c.	Add one to that value retrieved from the transition table
//		}

	}
	
	
}


