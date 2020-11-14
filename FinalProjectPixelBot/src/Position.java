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
	
	void run() {
		
		//set data
		//Integer[] xList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
		Integer[] xList = {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1,
						   1, 1, 1, 0, 0, 2, 2, 2, 2, 1, 1, 0, 0, 1, 1, 1,
						   1, 1, 6, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 0, 1, 1, 
						   1, 0, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 0, 1, 
						   1, 0, 1, 2, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 0, 1, 
						   6, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 0, 
						   0, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 2, 0,
						   0, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0,
						   0, 1, 1, 2, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 0,
						   0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 0,
						   0, 1, 2, 2, 0, 0, 6, 0, 0, 0, 0, 0, 2, 2, 2, 0,
						   1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 
						   1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 
						   1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 
						   1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 
						   1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1};
		
		//set data in array list
		ArrayList<Integer> testList = new ArrayList(Arrays.asList(xList));	
		
		//initialize image
		MarkovOrderM<Integer> posGenerator = new MarkovOrderM<Integer>(1);
		//ProbabilityGenerator<Integer> pGenerator = new ProbabilityGenerator<Integer>();
		
		//train data
		posGenerator.train(testList);
		//pGenerator.train(testList);
		
		//print image
		System.out.println(testList);
		//posGenerator.print(testList);
		print(testList);
		
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
