/*
 * Programmer: Diana Vu
 * Date: September 8, 2020
 * Description: This is Unit One test that will test the train method in probability generator 
 * 
 */

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UnitOneTest {
	
	UnitOneTest(){
		
	}
	
	void run() {
		//put in unit test class
		MidiFileToNotes midiNotesMary; //read midi file
		
		// returns a url
		String filePath = getPath("mid/MaryHadALittleLamb.mid");

		midiNotesMary = new MidiFileToNotes(filePath); //creates a new MidiFileToNotes -- reminder -- ALL objects in Java must 
													   //be created with "new". Note how every object is a pointer or reference. Every. single. one.


	    // which line to read in --> this object only reads one line (or ie, voice or ie, one instrument)'s worth of data from the file
		midiNotesMary.setWhichLine(0);
		
		MarkovGenerator<Integer> pitchGenerator = new MarkovGenerator<Integer>();
		MarkovGenerator<Double> rhythmGenerator = new MarkovGenerator<Double>();
		ProbabilityGenerator<Integer> pGenerator = new ProbabilityGenerator<Integer>();
		ProbabilityGenerator<Double> rGenerator = new ProbabilityGenerator<Double>();
		
		//training
		pitchGenerator.train(midiNotesMary.getPitchArray());
		rhythmGenerator.train(midiNotesMary.getRhythmArray());
		pGenerator.train(midiNotesMary.getPitchArray());
		rGenerator.train(midiNotesMary.getRhythmArray());
		
		//print out the distribution
		System.out.println("Project 1\n" + "Unit Test One: \n");
		System.out.println("\n Pitches: \n" + "-----Probability Distribution-----\n");
		pGenerator.print(midiNotesMary.getPitchArray());
		System.out.println("\n Rhythms: \n" + "-----Probability Distribution-----\n");
		rGenerator.print(midiNotesMary.getRhythmArray());
		System.out.println("------");
		
		//print out transition table
		System.out.println("Project 2\n " + "Unit Test One: \n");
		System.out.println("\n Pitches: \n" + "-----Transition Table -----\n");
		pitchGenerator.printMarkov(midiNotesMary.getPitchArray());
		System.out.println("\n Rhythms: \n" + "-----Transition Table-----\n");
		rhythmGenerator.printMarkov(midiNotesMary.getRhythmArray());
		System.out.println("------");
		
		//print out transition table
		System.out.println("Project 3\n " + "Unit Test One: \n");
		for(int i = 1; i <= 10; i++) {
			MarkovOrderM<Integer> pGen = new MarkovOrderM<Integer>(i); //create new object
			
			//training data
			pGen.train(midiNotesMary.getPitchArray());
			
			System.out.println("\n Pitches for order " + i + ":\n" + "-----Transition Table -----\n");
			pGen.printMarkovM(midiNotesMary.getPitchArray());
			System.out.println("------");
		}

		for(int i = 1; i <= 10; i++) {
			MarkovOrderM<Double> rGen = new MarkovOrderM<Double>(i); //create new object
			
			//training data
			rGen.train(midiNotesMary.getRhythmArray());
			
			System.out.println("\n Rhythms for order " + i + ":\n" + "-----Transition Table -----\n");
			rGen.printMarkovM(midiNotesMary.getRhythmArray());
			System.out.println("------");
		}

	}

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

}
