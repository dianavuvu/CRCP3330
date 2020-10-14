/*
 * Programmer: Diana Vu
 * Date: October 5, 2020
 * Description: This is the markov generator class where I will create an algorithm that predicts the next 'T' in a sequence to make a coherent melody style. 
 * 
 */

import java.util.ArrayList;

public class MarkovOrderM<T> extends MarkovGenerator<T>{
	
	ArrayList<ArrayList<T>> uniqueAlphabetSequence; //make an array list (uniqueAlphabetSequence) that holds curSequences
	int orderM; //for setting orderM in the constructor
	MarkovGenerator<T> markGenerator = new MarkovGenerator<T>(); //create markov generator
	
	MarkovOrderM(int orderM1){ //overloaded constructor
		orderM = orderM1;
		uniqueAlphabetSequence = new ArrayList(); //add current sequences into transition table
	}
	
	T generate(ArrayList<T> initSeq) {
		T newToken = null;
		float sumAlphabetCounts = 0; //initialize sum of alphabet counts array
		
//		curSeqIndex  = find the index of initSeq in uniqueAlphabetSequence 
		int curSeqIndex = uniqueAlphabetSequence.indexOf(initSeq);
		
//		if(initSeq is not found){
		if(curSeqIndex == -1) {
//			1. generate from a trained markov chain 1
//			//note – there are other solutions, you could generate from prob. dist. instead.
//			//you could rollback your generation one character & generate again
//
//			//whatever you choose will affect your transition tables in Unit 3 – note that only the sequences 
//			//found in training will have the reported probabilities for each row. Eg. you will see "discrepancies"
//			//in the rhythms, as the symbol 4.0 comes after things but nothing comes after it.
			
			newToken = markGenerator.generate(initSeq.get(initSeq.size() - 1)); //generating from Project 2 MarkovGenerator
		}

		else{
//			1. find the row in the transition table using curSeqIndex 
			alphabet_counts = transitionTable.get(curSeqIndex);
			
//			2. generate from that row using your Probability Generator NOTE: remember to handle 0% probability across all tokens
			for(int i = 0; i < alphabet_counts.size(); i++){ //sum alphabet_counts
				sumAlphabetCounts = sumAlphabetCounts + alphabet_counts.get(i);
			}
			
			total = sumAlphabetCounts;
			
			if (total == 0) {
				return probGenerator.generate(); //for rows with nothing (i.e. 0)
			}
			else {
				return super.generate(); //handing generate function from ProbGen array from transitionTable
			}
		}
		return newToken;
	}
	
	ArrayList generate(ArrayList<T> initSeq, int numTokensToGen){
//		1.	create an ArrayList of T - outputMelody
		ArrayList<T> outputMelody = new ArrayList();
		T genToken;
		
//		2.	for 1 to numTokensToGen do 
		for(int i = 0;  i <= numTokensToGen; i++){
			
//			1.	call your single generate using your initSeq
			genToken = generate(initSeq);
			
//			2.	remove the first token you added from your initSeq
			initSeq.remove(0);
			
//			3.	add the generated token to your initSeq
			initSeq.add(genToken);
			
//			4.	add the generated token to outputMelody
			outputMelody.add(genToken);
			
//			5.	remove the first token off the top of the initSeq
//			initSeq.remove(initSeq.size() - 1);
		}
		
		return outputMelody;
	}

	
	void train(ArrayList<T> newTokens) {
//		for i = orderM -1 to (i < size of the input - 1) do 
		
		markGenerator.train(newTokens); //training for generate function
		
		for(int i = orderM - 1; i < newTokens.size() - 1; i++){
			
			int tokenIndex = 0; //token index is equal to index of token in alphabet
			int rowIndex = -1; //initialize rowIndex
			ArrayList<T> curSequence = new ArrayList(); //create a new curSequence EVERY TIME!!!! 
					
//			1.	Create the current sequence (e.g. curSequence) of size orderM from the input
//			Remember to start the index into the input at 0 (with this algorithm) 
//				a.	add the previous tokens to a container (e.g. ArrayList). 
			
			for(int j = i - (orderM - 1); j <= i; j++) { 
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
	}
	
	void printMarkovM(ArrayList<T> newTokens) {
		System.out.println(alphabet); //printing out what is in alphabet(i.e the tokens)
		
		for(int i = 0; i < uniqueAlphabetSequence.size(); i++) { //for each row in uniqueAlphabetSequence
			System.out.print(uniqueAlphabetSequence.get(i));
			
			for(int j = 0; j < transitionTable.get(i).size(); j++) { //print each element in the row
				
				for(int k = 0; k < transitionTable.get(i).size(); k++) { //find sum of the row to divide element by 
					sum = sum + transitionTable.get(i).get(k);
				}
				
				if (sum == 0) //for rows that have nothing (i.e. 0)
					sum = 1;
				
				System.out.print(" " + transitionTable.get(i).get(j) / sum); //print out element divided by sum
				sum = 0; //reset sum for other rows
			}
			System.out.println("\n");
		}
	}

}//end class


