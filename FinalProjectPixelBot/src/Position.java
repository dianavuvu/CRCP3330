/*
 * Programmer: Diana Vu
 * Date: November 10, 2020
 * Description: This class will be responsible for creating brush strokes
 * 
 */

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

import processing.core.PApplet;

public class Position extends PApplet { //inherit Processing properties
	
	int run() {
		
		int color = 0;
		//set data
		//Integer[] xList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
		/*
		 * 10 = red
		 * 30 = pink
		 * 200 = light green
		 * 100 = green
		 */
		Integer[] aList = {255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255};
		Integer[] bList = {255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 200, 100, 255, 255, 255};
		Integer[] dList = {255, 255, 255, 255, 255, 255, 255, 255, 255, 10, 30, 200, 100, 255, 255}; 
		Integer[] eList = {255, 255, 255, 255, 255, 255, 255, 255, 10, 10, 30, 30, 200, 100, 255}; 
		Integer[] fList = {255, 255, 255, 255, 255, 255, 255, 10, 10, 0, 10, 30, 200, 100, 255}; 
		Integer[] gList = {255, 255, 255, 255, 255, 255, 10, 10, 10, 10, 10, 30, 200, 100, 255}; 
		Integer[] hList = {255, 255, 255, 255, 255, 10, 10, 10, 0, 10, 10, 30, 200, 100, 255};
		Integer[] iList = {255, 255, 255, 255, 10, 10, 10, 10, 10, 10, 10, 30, 200, 100, 255};
		Integer[] jList = {255, 255, 255, 10, 10, 10, 0, 10, 10, 10, 30, 30, 200, 100, 255};
		Integer[] kList = {255, 255, 10, 10, 0, 10, 10, 10, 10, 10, 30, 200, 100, 255, 255};
		Integer[] lList = {255, 200, 30, 30, 10, 10, 10, 10, 10, 30, 30, 200, 100, 100, 255, 255};
		Integer[] mList = {255, 100, 200, 30, 30, 30, 30, 30, 30, 200, 100, 100, 255, 255, 255}; 
		Integer[] nList = {255, 255, 100, 200, 200, 200, 200, 200, 200, 100, 100, 255, 255, 255, 255}; 
		Integer[] oList = {255, 255, 255, 255, 100, 100, 100, 100, 100, 255, 255, 255, 255, 255, 255}; 
		Integer[] pList = {255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255};
		
		//set data in array list
		ArrayList<Integer> testList = new ArrayList(Arrays.asList(aList));	
		
		//initialize image
		MarkovOrderM<Integer> posGenerator = new MarkovOrderM<Integer>(1);
		//ProbabilityGenerator<Integer> pGenerator = new ProbabilityGenerator<Integer>();
		
		//train data
		posGenerator.train(testList);
		//pGenerator.train(testList);
		
		color = testList.get((int)random(64));
		
		return color;
		
	}
	
	//method to print arraylist in image format
	void print(ArrayList<Integer> newTokens) {
		
		for(int i = 0; i < 16; i++) { //for each row of pixel
			
			for(int j = 0; j < 16; j++) { //row size of pixels
				
			System.out.print(newTokens.get(i) + " ");
			
			}
			
			System.out.println();
		}
	}

	
	public void draw() {
		
		//draw the rectangles
		fill(0);
		rect(20, 20, 20, 20);
		
	}
	
	private void makeFrameFullSize(JFrame aFrame) {
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    aFrame.setSize(screenSize.width, screenSize.height);
	}

}
