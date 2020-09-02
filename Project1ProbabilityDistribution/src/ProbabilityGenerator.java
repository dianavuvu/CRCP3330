/*
 * Programmer: Diana Vu
 * Date: September 8, 2020
 * Description: 
 * This is the probability generator class where I will create an algorithm that predicts the next 'T' in a sequence. 
 * 
 */

import java.util.ArrayList;

public class ProbabilityGenerator <T> {
	ArrayList<T> alphabet; 					//initializing alphabet array list
	ArrayList<Integer> alphabet_counts; 	//initializing alphabet count array list 
	
	ProbabilityGenerator(){
		alphabet = new ArrayList<T>();
		alphabet_counts = new ArrayList<Integer>();
	}
	
	//it is training probability generator with new data
	void train(ArrayList<T> newTokens) {
		//code training
	}
	
	T generate() {
		T newToken = null;
		//do something here
		return newToken;
	}
	
	ArrayList<T> generate (int length){
		ArrayList<T> newSequence = new ArrayList<T>();
		for (int i = 0; i < length; i++) {
			newSequence.add(generate());
		}
		return newSequence;
	}
}
