/*
 * Programmer: Diana Vu
 * Date: September 28, 2020
 * Description: This is Unit Two test that will test the generate method in probability generator and markov generator
 * from projects 1 and 2
 * 
 */

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

public class UnitTwoTest {

	UnitTwoTest(){
		
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
		
		//initialize generators for pitch and rhythm
		ProbabilityGenerator<Integer> pitchGenerator = new ProbabilityGenerator<Integer>();
		ProbabilityGenerator<Double> rhythmGenerator = new ProbabilityGenerator<Double>();
		MarkovGenerator<Integer> pitchG = new MarkovGenerator<Integer>(); //project 2
		MarkovGenerator<Double> rhythmG = new MarkovGenerator<Double>();
		
		//training
		pitchGenerator.train(midiNotesMary.getPitchArray());
		rhythmGenerator.train(midiNotesMary.getRhythmArray());
		pitchG.train(midiNotesMary.getPitchArray()); //proj 2
		rhythmG.train(midiNotesMary.getRhythmArray());
		
		//generating melodies
		ArrayList<Integer> newPitches = new ArrayList<Integer>(); //initialize new array for generated pitches
		ArrayList<Double> newRhythms = new ArrayList<Double>(); //initialize new array for generated rhythms
		ArrayList<Integer> newP = new ArrayList<Integer>(); //initialize new array for generated pitches //project 2
		ArrayList<Double> newR = new ArrayList<Double>(); //initialize new array for generated rhythms //project 2
		
		newPitches = pitchGenerator.generate(20); //put new generated pitches in an array
		newRhythms = rhythmGenerator.generate(20); //put new generated rhythms in an array
		newP = pitchG.generate(20); //put new generated pitches in an array //project 2
		newR = rhythmG.generate(20); //put new generated rhythms in an array //project 2
		
		//printing out pitch and rhythm tokens of generated melody Project 1
		System.out.println("Project 1:\n" + "Unit Test Two:\n" + " Generated Tokens for Pitches: \n");
		System.out.println(newPitches);
		System.out.println("\n Generated Tokens for Rhythm: \n");
		System.out.println(newRhythms);
		
		//printing out pitch and rhythm tokens of generated melody Project 2
		System.out.println("Project 2:\n" + "Unit Test Two:\n" + " Generated Tokens for Pitches: \n");
		System.out.println(newP);
		System.out.println("\n Generated Tokens for Rhythm: \n");
		System.out.println(newR);
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