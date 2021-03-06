/* 
 * Programmer: Diana Vu 
 * Date: September 8, 2020
 * Description: Demonstration of MIDI file manipulations, etc. & 'MelodyPlayer' sequencer
 * 
 */

import processing.core.*;
import java.util.*; 

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
		size(300, 300);
	}

	//doing all the setup stuff
	public void setup() {
		fill(120, 50, 240);
		
	}

	public void draw() {
		// returns a url
//		String filePath = getPath("mid/gardel_por.mid");
//		// playMidiFile(filePath);
//
//		midiNotes = new MidiFileToNotes(filePath); //creates a new MidiFileToNotes -- reminder -- ALL objects in Java must 
//													//be created with "new". Note how every object is a pointer or reference. Every. single. one.
//
//		// which line to read in --> this object only reads one line (or ie, voice or ie, one instrument)'s worth of data from the file
//		midiNotes.setWhichLine(0);
//		
//		//Create my generator for pitch and rhythm
//		MarkovOrderM<Integer> pG = new MarkovOrderM<Integer>(10);
//		MarkovOrderM<Double> rG = new MarkovOrderM<Double>(10);
//		MarkovGenerator<Double> rhyG = new MarkovGenerator<Double>();
//		MarkovGenerator<Integer> pitG = new MarkovGenerator<Integer>();
//		
//		//training
//		pG.train(midiNotes.getPitchArray());
//		pitG.train(midiNotes.getPitchArray());
//		rG.train(midiNotes.getRhythmArray());
//		rhyG.train(midiNotes.getRhythmArray());
//		
//		ArrayList<Double> nR = new ArrayList<Double>(); //create array list to hold generated initSeq
//		ArrayList<Integer> nP = new ArrayList<Integer>();
//		
//		nP = pitG.generate(20); //create initSeq4
//		nR = rhyG.generate(20);
//		
//		player = new MelodyPlayer(this, 100.0f);
//
//		player.setup();
//		player.setMelody(pG.generate(nP, 20));
//		player.setRhythm(rG.generate(nR, 20));
//		
//		if(playMelody) {
//			player.play(); //play each note in the sequence -- the player will determine whether is time for a note onset
//		}

		textSize(15);
		fill(0, 0, 200);
		text("Press 1 to start Unit Test One\n", width/10, height/6);
		
		fill(0, 200, 0);
		text("Press 2 to start Unit Test Two\n", width/10, height/4);
		
		fill(200, 0, 0);
		text("Press 3 to start Unit Test Three\n", width/10, height/3);
		
		fill(200, 0, 200);
		text("Press 4 to stop melody\n", width/10, height/2);
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
		UnitOneTest test = new UnitOneTest();
		UnitTwoTest t = new UnitTwoTest();
		UnitThreeTest ts = new UnitThreeTest();
		
		if (key == ' ') {
			player.reset();
			println("Melody started!");
		}
		else if (key == '1'){		
			//run unit 1
			test.run();
		}
		else if (key == '2') {
			//run unit 2
			t.run();
		}
		else if (key == '3') {
			//run unit 3
			ts.run();
		}
		else if (key == '4') {
			playMelody = !playMelody;
		}
	}
}
