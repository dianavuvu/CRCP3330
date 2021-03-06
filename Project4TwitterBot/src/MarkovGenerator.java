/*
 * Programmer: Diana Vu
 * Date: September 28, 2020
 * Description: This is the markov generator class where I will create an algorithm that predicts the next 'T' in a sequence to make a coherent melody style. 
 * 
 */

import java.util.ArrayList;

public class MarkovGenerator<T> extends ProbabilityGenerator<T>{
	//to declare
	ArrayList<ArrayList<Integer>> transitionTable; //initialize array list of array lists
	float sum = 0; //initialize sum
	ProbabilityGenerator<T> probGenerator = new ProbabilityGenerator<T>(); //create prob generator
	
	MarkovGenerator(){
		super();
		transitionTable = new ArrayList(); 		//to create the ArrayList
	}
	
	T generate(T initToken) { //find initToken from ProbabilityGenerator
		T newToken = null;

		int foundIndex = 0; //initialize found token index
		float sumAlphabetCounts = 0; //initialize sum of alphabet counts array

		//find initToken in the alphabet (ie, get the index of where it is)
		foundIndex = alphabet.indexOf(initToken);

//		Use that index to access the row (ie one array from the array of arrays) of probabilities in transitionTable
//		This array is the beginning of a probability distribution. It has all the counts. It is exactly like the array of counts that you had in Project 1. 
//		Thus, You already have a function which generates from a probability distribution.
//		Hand that function (your generate function from Project 1) your row(i.e., array of probabilities/counts) and generate from that. 
//		You may have to rewrite it to accommodate your new needs. Make sure Project 1 still works afterwards. Run your Project 1 unit tests to check.
		
		alphabet_counts = transitionTable.get(foundIndex); //calling ProbabilityGenerator generate
		
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

//		If you need to generate more than one symbol, use this result to generate another.
//		That is, set initToken = the token you just generated. Go to step 1.
	}
	
	void train(ArrayList<T> newTokens) {
		
		probGenerator.train(newTokens); //train the data first for generate
		
		int lastIndex = -1;
		
		for(int i = 0; i <= newTokens.size() - 1; i++) { //for each token in input array
			int tokenIndex = alphabet.indexOf(newTokens.get(i)); //token index is equal to index of token in alphabet
			
			if (tokenIndex == -1) {
				tokenIndex = alphabet.size(); //tokenIndex = size of alphabet
				
				//add a new row to the transition table (expand vertically)
				ArrayList<Integer> newRow = new ArrayList();
				for(int j = 0; j < alphabet.size(); j++) { //create a new array that is the size of the alphabet 
					newRow.add(0);
				}
				transitionTable.add(newRow); //Then add to your transition table (the array of arrays)
		
				//add a new column (expand horizontally)
				for(int k = 0; k < transitionTable.size(); k++) {
					 transitionTable.get(k).add(0); //add a 0 to all of the arrays in the transition table.
				}
		
				alphabet.add(newTokens.get(i)); //add the token to the alphabet array 
			}
			
		    //now add the counts to the transition table
			if(lastIndex > -1) { //that is, we have a previous token so its not the 1st time thru
				//1.	Use lastIndex to get the correct row (array) in your transition table.
				//2.	Use the tokenIndex to index the correct column (value of the row you accessed)
				//3.	Add 1 to that value.
				transitionTable.get(lastIndex).set(tokenIndex, transitionTable.get(lastIndex).get(tokenIndex)+1);
			}
			
			lastIndex = tokenIndex; //setting current to previous for next round
		}
	}
	
	void printMarkov(ArrayList<T> newTokens) {
		System.out.println(alphabet); //printing out what is in alphabet(ie the tokens)
		
		for(int i = 0; i < alphabet.size(); i++) { //for each row in transition table
			System.out.print(alphabet.get(i));
			
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
	
	ArrayList<T> generate(int length, T initToken) {
		ArrayList<T> newSequence = new ArrayList<T>();
		
		T token = null; //initialize variables
		T nToken = null;
		
		newSequence.add(initToken); //add original initToken to sequence
		
		for (int i = 0; i < length; i++) {
			
			if(i == 0) { //use parameter to generate first token
				token = generate(initToken); //generate initial token
				newSequence.add(token);
				nToken = generate(token);
				newSequence.add(nToken);
			}
			else { //use previous call to generate new tokens
				nToken = generate(nToken);
				newSequence.add(nToken);
			}
		}
		return newSequence;
	}
	
	ArrayList<T> generate(int length) {
		return generate(length, probGenerator.generate());
	}
}
