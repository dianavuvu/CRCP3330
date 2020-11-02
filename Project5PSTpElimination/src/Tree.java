/*
 * Programmer: Diana Vu
 * Date: October 27, 2020
 * Description: This is the Tree class where we build our PST 
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class Tree<T>{
	
	int L; //setting max sequence length
	Node root; //initialize variables
	float pMin;
	int totalInputTokens;

	Tree() {
		root = new Node(new ArrayList()); //initialize
		pMin = 0.5f;
		L = 3;
		totalInputTokens = 0;
	}

	// it is training probability generator with new data
	void train(ArrayList<T> newTokens) {
//		String[] myList = {"a"};
//		String[] list = {"b"};
//		
//		//set data in array list
//		ArrayList<T> testList = new ArrayList(Arrays.asList(myList));
//		ArrayList<T> testL = new ArrayList(Arrays.asList(list));
//		
//		Node<String> node3 = new Node(testList);
//		Node<String> node2 = new Node(testL);
//		System.out.println( "TEST\n" + node2.amIASuffix(node3) );
		
		for(int i = 1; i <= L; i++) { //for every iteration of the size up until L
			
			for(int j = 0; j < newTokens.size() - (i-1); j++) { //for every token in the arrayList
				
				ArrayList<T> curSequence = new ArrayList(); //create a new curSequence EVERY TIME!!!!
				
				//finding current sequence
				for(int s = j; s < j + i; s++) { 
					curSequence.add(newTokens.get(s));
				}

				//System.out.println(curSequence);
				
				//create new node with current sequence	
				Node theNewNode = new Node(curSequence); //initialize the new node

				//add the node
				root.addNode(theNewNode);
			}
		}
		//sum total number of tokens
		totalInputTokens = totalInputTokens + newTokens.size();
		
		//use pMin elimination
		root.pMinElimination(totalInputTokens, pMin);
	}
	
	//printing out data
	void print() {
		root.print();
	}
}
