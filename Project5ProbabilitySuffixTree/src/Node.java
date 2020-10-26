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
	
	Node(){
		tokenSequence = new ArrayList<T>();
		children = new ArrayList<Node>();
	}
	
	//adding a node to the tree
	boolean addNode(Node node) {
		
		boolean found = false; 
		
		if(tokenSequence == theNewNode) {
			found = true;
		}
		else if(amIASuffix(node) || (tokenSequence.size() == 0)) {
			
			//add node to all children node
			
			//if not found
		}
		return found;		
	}
	
	//to determine if node is a suffix or not
	void amIASuffix(Node node) {
		
	}
	
	//print method 
	void print() {
		
		//print token sequence
		System.out.println(tokenSequence);
		
		for(int i = 0; i <= children.size(); i++) { //for each node in children
			node.print(1);
		}
	}
	
	//determine how much spaces to increment
	void print(int numSpacesBefore) {
		
		for (int i = 1; i <= numSpacesBefore; i++) {
			print(" ");
		}
	}

}
