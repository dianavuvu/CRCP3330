/*
 * Programmer: Diana Vu
 * Date: October 27, 2020
 * Description: This is the Tree class where we build our PST 
 * 
 */

import java.util.ArrayList;

public class Tree<T> {
	
	//setting max sequence length
	int L = 3;
	Node root;

	Tree() {
		root = new Node();
	}

	// it is training probability generator with new data
	void train(ArrayList<T> newTokens) {
		
		ArrayList<T> curSequence = new ArrayList(); //create a new curSequence EVERY TIME!!!!
		
		for(int i = 1; i <= L; i++) { //for every interation of the size up until L
			
			for(int j = 0; j < newTokens.size() - (i-1); j++) { //for every token in the arraylist
				
				//finding current sequence
				for(int s = j - (L - 1); s <= j; s++) { 
					curSequence.add(newTokens.get(j));
				}
				
				//create new node with current sequence
				Node theNewNode = new Node();
				
				//add the node
				root.addNode(theNewNode);
			}
		}
	}
	
	//printing out data
	void print() {
		root.print();
	}
}
