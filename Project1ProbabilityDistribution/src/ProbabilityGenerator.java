/*
 * Programmer: Diana Vu
 * Date: September 8, 2020
 * Description: This is the probability generator class where I will create an algorithm that predicts the next 'T' in a sequence. 
 * 
 */

import java.util.ArrayList;

public class ProbabilityGenerator<T> {
	ArrayList<T> alphabet; // initializing alphabet array list
	ArrayList<Integer> alphabet_counts; // initializing alphabet count array list
	float total = 0; //initializing total

	ProbabilityGenerator() {
		alphabet = new ArrayList<T>();
		alphabet_counts = new ArrayList<Integer>();
	}

	// it is training probability generator with new data
	void train(ArrayList<T> newTokens) {

		for (int i = 0; i <= newTokens.size() - 1; i++) { // for loop to go through newTokens
			int index = alphabet.indexOf(newTokens.get(i));
			if (index == -1) {
				index = alphabet.size(); // index = size of alphabet
				alphabet.add(newTokens.get(i)); // add newTokens to alphabet array
				alphabet_counts.add(0); // add 0 to alphabets counts array
			}

			int val = alphabet_counts.get(index);
			val = val + 1;
			alphabet_counts.set(index, val); // incrementing alphabet counts by 1
		}
		
	}
	
	void print(ArrayList<T> newTokens) {
		total = total + newTokens.size();	// divide by total amount
		
		// create print probability function
		for (int i = 0; i < alphabet_counts.size(); i++) {
			System.out.println("Token: " + alphabet.get(i) + " | " + "Probability:" + (alphabet_counts.get(i) / total)); // print out all the pitches & rhythms																									
		}

	}

	T generate() {
		T newToken = null; 
		
		ArrayList<Integer> newProbs = new ArrayList<Integer>(); //intialize newProbs arraylist
		
		//generate random number from 0 - 1
		float rIndex = (float) Math.random();
		float index = alphabet.size() * rIndex;
		
		//create sumProbs array
		for (int i = 1; i < alphabet.size() - 1; i++) {
			
//			if (i == 0)  // first iteration of alphabet
//				newProbs.add(alphabet_counts.get(0));

			int p = alphabet_counts.get(i);		//current distribution		
			int r = alphabet_counts.get(i - 1);	//previous distribution
			int t = p + r;						//add sum
					
			newProbs.add(t); // add to new array
		}
		
		//debugging, check if size is the same
		if (alphabet.size() == newProbs.size())
			System.out.println("YAY the size is the same! :)");
		
		//found --> rIndex <= sumProbs[index];
		
		boolean found = false;
		int i = 0; //index for while loop
		
		//while loop to look through newProbs array to find newToken to generate
		while(!found && i < newProbs.size()) {
			//index < size - 1
			if (index <= newProbs.get(i)) {			//if x < index then newToken = that index
				newToken = alphabet.get(i);			//return alphabet.get(index);
				found = true;
			}
			i++; //increment the index size
		}
		return newToken;
	}

	ArrayList<T> generate(int length) {
		ArrayList<T> newSequence = new ArrayList<T>();
		for (int i = 0; i < length; i++) {
			newSequence.add(generate());
		}
		return newSequence;
	}
}
