/*
 * Programmer: Diana Vu
 * Date: November 17, 2020
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

public class ColorSquare extends PApplet { //inherit Processing properties
	
	int run() {
		
		int color = 0;
		
		//set data
		//Integer[] xList = {255, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
		Integer[] xList = {255, 255, 255, 255, 255, 0, 0, 0, 0, 0, 0, 255, 255, 255, 255, 255,
						   255, 255, 255, 0, 0, 100, 100, 100, 100, 255, 255, 0, 0, 255, 255, 255,
						   255, 255, 0, 255, 255, 100, 100, 100, 100, 255, 255, 255, 255, 0, 255, 255, 
						   255, 0, 255, 255, 100, 100, 100, 100, 100, 100, 255, 255, 255, 255, 0, 255, 
						   255, 0, 255, 100, 100, 255, 255, 255, 255, 100, 100, 255, 255, 255, 0, 255, 
						   0, 100, 100, 100, 255, 255, 255, 255, 255, 255, 100, 100, 100, 100, 100, 0, 
						   0, 100, 100, 100, 255, 255, 255, 255, 255, 255, 100, 100, 255, 255, 100, 0,
						   0, 255, 100, 100, 255, 255, 255, 255, 255, 255, 100, 255, 255, 255, 255, 0,
						   0, 255, 255, 100, 100, 255, 255, 255, 255, 100, 100, 255, 255, 255, 255, 0,
						   0, 255, 255, 100, 100, 100, 100, 100, 100, 100, 100, 100, 255, 255, 100, 0,
						   0, 255, 100, 100, 0, 0, 0, 0, 0, 0, 0, 0, 100, 100, 100, 0,
						   255, 0, 0, 0, 255, 255, 0, 255, 255, 0, 255, 255, 0, 0, 0, 255, 
						   255, 255, 0, 255, 255, 255, 0, 255, 255, 0, 255, 255, 255, 0, 255, 255, 
						   255, 255, 0, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 0, 255, 255, 
						   255, 255, 255, 0, 255, 255, 255, 255, 255, 255, 255, 255, 0, 255, 255, 255, 
						   255, 255, 255, 255, 0, 0, 0, 0, 0, 0, 0, 0, 255, 255, 255, 255};
		
		//set data in array list
		ArrayList<Integer> testList = new ArrayList(Arrays.asList(xList));	
		
		//initialize image
		MarkovOrderM<Integer> posGenerator = new MarkovOrderM<Integer>(1);
		//ProbabilityGenerator<Integer> pGenerator = new ProbabilityGenerator<Integer>();
		
		//train data
		posGenerator.train(testList);
		//pGenerator.train(testList);
		
		//print image
		//System.out.println(testList);
		//posGenerator.print(testList);
		
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
