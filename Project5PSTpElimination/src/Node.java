/*
 * Programmer: Diana Vu
 * Date: October 27, 2020
 * Description: This is the Node class where we build the child nodes for our PST 
 * 
 */

import java.util.ArrayList;

public class Node<T> {
	
	ArrayList<T> tokenSequence;
	ArrayList<Node> children;
	int count;

	
	Node(ArrayList<T> sequence){
		tokenSequence = new ArrayList<T>();
		children = new ArrayList<Node>();
		count = 1; //counter for how many times node appears in the input
		
		//set tokenSequence to current sequence 
		tokenSequence = sequence; 
	}
	
	ArrayList<T> getTokenSequence(){
		return tokenSequence;
	}
	
	//adding a node to the tree
	boolean addNode(Node node) {
		
		boolean found = false; 
		
		//if tokenSequence of this node is same as sequence of added node
		if(tokenSequence.equals(node.getTokenSequence())) {
			found = true;
			count++; //adding one to count
		}
		else if(amIASuffix(node) || (tokenSequence.size() == 0)) {

			int i = 0;// initialize increment
			
			while(!found && i < children.size()) {
				
				//did child nodes add the node?
				//add node to all children node
				found = children.get(i).addNode(node);	
				i++; //increment
			}
			
			//if not found && length of node is one less than this tokenSequence
			if (!found ){
				
				children.add(node); //add node to children array
				found = true;
			}
		}
		return found;		
	}
	
	//to determine if node is a suffix or not
	boolean amIASuffix(Node node) {
		boolean amI = true;
		
		//for empty string
		if(tokenSequence.size() == 0) {
			return true;
		}
		
		//if they are the same size false
		if(node.getTokenSequence().size() == tokenSequence.size()) {
			return false;
		}
			
		int i = 1;
		//for the size of tokenSequence
		while(amI && i <= tokenSequence.size()) {

			//if the last token are the same
			amI = node.getTokenSequence().get(node.getTokenSequence().size() - i).equals(tokenSequence.get(tokenSequence.size() - i));
			
			i++; //increment
		}	
		return amI;
	}
	
	boolean pMinElimination(int totalTokens, float pMin) {
		
		float emProb = 0;
		boolean shouldRemove; //initializing variables
		
		//find number of times that sequence could have occurred
		emProb = ((float)count) / ((float)totalTokens - ((float)tokenSequence.size() - 1));
		
//		System.out.println(tokenSequence);
//		System.out.println(emProb);
		
		//shouldRemove = empirical probability of token sequence < pMin
		shouldRemove = emProb < pMin;
//		System.out.println(shouldRemove);
		
		//for empty root
		if(tokenSequence.size() == 0) {
			shouldRemove = false;
			System.out.println("setting here");
		}
		
//		System.out.println(tokenSequence.size());
		
		//if we DON'T remove node
		if(shouldRemove == false) {
			
			//for each node
			for(int i = children.size() - 1; i >= 0; i--) { //deleting from the back
				
				//call pMinElimination on all children nodes
				boolean remove = children.get(i).pMinElimination(totalTokens, pMin);
				
				//if it returns true
				if(remove == true) {
					
					//remove entire node
					children.remove(children.get(i));
				}
			}
		}
		return shouldRemove; //return shouldRemove
	}
	
	//print method 
	void print() {
		
		//print token sequence
		System.out.println(tokenSequence);
		
		for(int i = 0; i < children.size(); i++) { //for each node in children
			children.get(i).print(1);
		}
	}
	
	//determine how much spaces to increment
	void print(int numSpacesBefore) {
		
		for (int i = 1; i <= numSpacesBefore; i++) {
			System.out.print("  ");
		}
		
		System.out.println("--> " + tokenSequence);
		
		for(int i = 0; i < children.size(); i++) { //for each node in children
			children.get(i).print(numSpacesBefore + 1);
		}
	}

}
