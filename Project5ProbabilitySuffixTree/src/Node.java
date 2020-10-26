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
		
		//if tokenSequence is same as sequence of added node
		if(tokenSequence == node) {
			found = true;
		}
		else if(amIASuffix(node) || (tokenSequence.size() == 0)) {
			
			//add node to all children node
			node.addNode(node);
			
			//did child nodes add the node?
			found = true;
			
			//if not found && length of node is one less than this tokenSequence
			if (!found && node.tokenSequence > tokenSequence ){
				
				children.add(node); //add node to children array
				found = true;
			}
		}
		return found;		
	}
	
	//to determine if node is a suffix or not
	boolean amIASuffix(Node node) {
		boolean amI = false;
		
		//if(node.subList(0, node.size() - 1) == )
		
		//if they are equal
		if(node.equals(tokenSequence - 1))
			return true;
	
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
			System.out.print("--> " + tokenSequence.get(i));
		}
		
		for(int i = 0; i <= children.size(); i++) { //for each node in children
			node.print(numSpacesBefore + 1);
		}
	}

}
