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
	
	Node(ArrayList<T> sequence){
		tokenSequence = new ArrayList<T>();
		children = new ArrayList<Node>();
		
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
		}
		else if(amIASuffix(node) || (tokenSequence.size() == 0)) {
			
			//add node to all children node
			while(!found) {
				
				children.addNode(node);
				
				//did child nodes add the node?
				found = 
			}
			
			//if not found && length of node is one less than this tokenSequence
			if (!found && node.getTokenSequence().size() > tokenSequence.size() ){
				
				children.add(node); //add node to children array
				found = true;
			}
		}
		return found;		
	}
	
	//to determine if node is a suffix or not
	boolean amIASuffix(Node node) {
		boolean amI = true;
		
		//if(node.subList(0, node.size() - 1) == )
		
		//if they are equal
		if(node.getTokenSequence().equals(tokenSequence))
			return false;
		
		//for the size of tokenSequence
		for(int i = 1; i <= tokenSequence.size(); i++) {
		
			while(node.getTokenSequence().get(node.getTokenSequence().size() - i).equals(tokenSequence.get(tokenSequence.size() - i))) {
				//if the last token are the same
				//if(node.tokenSequence.get(node.tokenSequence.size() - i).equals(tokenSequence.get(tokenSequence.size() - i))) {
					amI = false;
				//}
			}	
		}
		return amI;
	}
	
	//print method 
	void print() {
		
		//print token sequence
		System.out.println(tokenSequence);
		
		for(int i = 0; i <= children.size(); i++) { //for each node in children
			children.get(i).print(1);
		}
	}
	
	//determine how much spaces to increment
	void print(int numSpacesBefore) {
		
		for (int i = 1; i <= numSpacesBefore; i++) {
			System.out.print("--> " + tokenSequence.get(i));
		}
		
		for(int i = 0; i <= children.size(); i++) { //for each node in children
			children.get(i).print(numSpacesBefore + 1);
		}
	}

}
