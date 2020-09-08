/*
 * Programmer: Diana Vu
 * Date: September 8, 2020
 * Description: This is the probability generator class where I will create an algorithm that predicts the next 'T' in a sequence. 
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
		
		for (int i = 0; i <= newTokens.size() - 1; i++) {	//for loop to go through newTokens
			int index = alphabet.indexOf(newTokens.get(i));
				if (index == -1) {		 
					alphabet.add(newTokens.get(i));		//add newTokens to alphabet array
					alphabet_counts.add(0);				//add 0 to alphabets counts array
				}
			
		int val = alphabet_counts.get(i);
		val = val + 1;
		alphabet_counts.set(alphabet_counts.get(i), val);	//incrementing alphabet counts by 1
		}

		System.out.println("Pitches: \n" + "-----Probability Distribution-----\n");
		for (int i = 0; i<= 5; i++) {
			System.out.println("Token: " + index + "|" + "Probability:"+ midiNotesMary.getPitchArray(i));		//print out all the pitches
		}
		
		System.out.println("Rhythms: \n" + "-----Probability Distribution-----\n");
		for (int i = 0; i<= 5; i++) {
			System.out.println("Token: " + token + "|" + "Probability:"+ midiNotesMary.getRhythmArray(i));
		}
		
	}
	
	T generate() {
		T newToken = null;
		
		//float rIndex = (float) Math.random();
		
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
