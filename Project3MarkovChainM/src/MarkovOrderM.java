/*
 * Programmer: Diana Vu
 * Date: October 5, 2020
 * Description: This is the markov generator class where I will create an algorithm that predicts the next 'T' in a sequence to make a coherent melody style. 
 * 
 */

import java.util.ArrayList;

public class MarkovOrderM<T> extends MarkovGenerator<T>{
	
	int orderM = 1; //set order of Markov chain
	ArrayList<T> curSequence; //an arraylist holding arraylists
	ArrayList<ArrayList<T>> uniqueAlphabetSequence; //make an arraylist (uniquealphabetsequence) that holds cursequences
	
	MarkovOrderM() {
		curSequence = new ArrayList(); //current sequence 
		uniqueAlphabetSequence = new ArrayList(); //add current sequences into transition table
	}
	
	void train(ArrayList<T> newTokens) {
//		for i = orderM -1 to (i < size of the input - 1) do 
		for(int i = orderM - 1; i < newTokens.size() - 1; i++){
			
			int tokenIndex = 0; //token index is equal to index of token in alphabet
			int rowIndex = -1;
					
//			1.	Create the current sequence (eg. curSequence) of size orderM from the input
//			Remember to start the index into the input at 0 (with this algorithm) 
//				a.	add the previous tokens to a container (eg ArrayList). 
//				b.	You may do this in a for-loop or use .subList()
//					i.	https://beginnersbook.com/2013/12/how-to-get-sublist-of-an-arraylist-with-example/
			
			for(int j = i - (orderM - 1); j < i; j++) { 
				curSequence.add(newTokens.get(j));
			}
						
//			2.	Find  curSequence in uniqueAlphabetSequences			
			rowIndex = uniqueAlphabetSequence.indexOf(curSequence);
				
//			if curSequence is not found
			if(rowIndex == -1) {
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
			
//			tokenIndex = the next index of the token in the alphabet (i+1)
			tokenIndex = alphabet.indexOf(newTokens.get(i+1));
//					
//			if tokenIndex is not found in the alphabet
			if(tokenIndex == -1){
					
//				1. tokenIndex = size of the alphabet 
				tokenIndex = alphabet.size();
						
//				2. add the token to the alphabet
				alphabet.add(newTokens.get(i+1));
						
//				3. expand transitionTable horizontally
				for(int p = 0; p < transitionTable.size(); p++) {
					 transitionTable.get(p).add(0); //add a 0 to all of the arrays in the transition table.
				}
			}

//			4.	Update the counts – since we started after the beginning, rowIndex will not be -1
//				a.	Get the row using rowIndex
//				b.	Get the column using tokenIndex
//				c.	Add one to that value retrieved from the transition table
			if(rowIndex > -1) {
				transitionTable.get(rowIndex).set(tokenIndex, transitionTable.get(rowIndex).get(tokenIndex)+1);
			}
		}
		orderM = orderM + 1; //increment orderM after every loop
	}
	
	void printMarkovM(ArrayList<T> newTokens) {
		System.out.println(alphabet); //printing out what is in alphabet(ie the tokens)
		
		for(int i = 0; i < uniqueAlphabetSequence.size(); i++) { //for each row in transition table
			System.out.print(uniqueAlphabetSequence.get(i));
			
			for(int j = 0; j < transitionTable.get(i).size(); j++) { //print each element in the row
				
				for(int k = 0; k < transitionTable.get(i).size(); k++) { //find sum of the row to divide element by 
					sum = sum + transitionTable.get(i).get(k);
				}
				
				if (sum == 0) //for rows that have nothing (i.e. 0)
					sum = 1;
				
				System.out.printf(" %.4f ", transitionTable.get(i).get(j) / sum); //print out element divided by sum
				sum = 0; //reset sum for other rows
			}
			System.out.println("\n");
		}
	}

}


