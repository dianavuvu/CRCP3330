/* 
 * Programmer: Diana Vu 
 * Date: September 8, 2020
 * Description: Demonstration of MIDI file manipulations, etc. & 'MelodyPlayer' sequencer
 * 
 */

import processing.core.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//importing the JMusic stuff
import jm.music.data.*;
import jm.JMC;
import jm.util.*;
import jm.midi.*;

import java.io.UnsupportedEncodingException;
import java.net.*;

//import javax.sound.midi.*;

			//make sure this class name matches your file name, if not fix.
public class HelloWorldMidiMain extends PApplet {

	MelodyPlayer player; //play a midi sequence
	MidiFileToNotes midiNotes; //read a midi file
	boolean playMelody = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("HelloWorldMidiMain"); //change this to match above class & file name 
	
	}

	//setting the window size to 300x300
	public void settings() {
		size(500, 500);
	}

	//doing all the setup stuff
	public void setup() {
		fill(120, 50, 240);
		
	}

	public void draw() {


		textSize(15);
		fill(0, 0, 200);
		text("Press 1 to start Unit Test One\n", width/10, height/6);
		

	    
	    
//		fill(0);
//		rect(20, 20, 20, 20);
		
//		fill(0, 200, 0);
//		text("Press 2 to start Unit Test Two\n", width/10, height/4);
//		
//		fill(200, 0, 0);
//		text("Press 3 to start Unit Test Three\n", width/10, height/3);
//		
//		fill(200, 0, 200);
//		text("Press 4 to stop melody\n", width/10, height/2);
	}

	//this finds the absolute path of a file
	String getPath(String path) {

		String filePath = "";
		try {
			filePath = URLDecoder.decode(getClass().getResource(path).getPath(), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}

	//this function is not currently called. you may call this from setup() if you want to test
	//this just plays the midi file -- all of it via your software synth. You will not use this function in upcoming projects
	//but it could be a good debug tool.
	void playMidiFile(String filename) {
		Score theScore = new Score("Temporary score");
		Read.midi(theScore, filename);
		Play.midi(theScore);
	}

	//this starts & restarts the melody.
	public void keyPressed() {
		
		//instantiate unit tests
		Position test = new Position();
		
		if (key == ' ') {
			player.reset();
			println("Melody started!");
		}
		else if (key == '1'){		
			//run unit 1
			test.run();
		}
		else if (key == '2') {
			//initialize
			int squareWidth = width / 10;
		    int squareHeight = height / 10;
		    ColorSquare colorSquare = new ColorSquare();
		    
			//draw the rectangles
		    for (int i = 0; i < 16; i++) { //iterating through every i until the size of N
	            for (int j = 0; j < 16; j++) {
	                int x1 = i * squareWidth;  //should increase by 1 every iteration, top corner of square
	                int y1 = j * squareHeight;
	                int x2 = x1 + squareWidth;  //should increase by 1 every iteration, bottoom corner of square
	                int y2 = y1 + squareHeight;

	                //color
	                int color = colorSquare.run();
	                
	                //change grey to red
	                if(color == 100) {
	                	fill(255, 0, 0);
	                }
		                else{
		                	fill(color);
		                }

	                rect(x1, y1, x2, y2);    //setting corners of sqaure
//	                gl::drawSolidRect(curSquare);   //drawing the square
	            }
		    }	
		}
	}
}
