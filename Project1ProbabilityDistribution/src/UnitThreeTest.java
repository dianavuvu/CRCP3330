/*
 * Programmer: Diana Vu
 * Date: September 14, 2020
 * Description: This is Unit Three test that will generate 10,000 melodies and print out the data set from those entries
 * 
 */

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

public class UnitThreeTest {
	
	UnitThreeTest(){
		
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
		
		ProbabilityGenerator<Integer> pitchGenerator = new ProbabilityGenerator<Integer>();
		ProbabilityGenerator<Double> rhythmGenerator = new ProbabilityGenerator<Double>();
		
		//generating melodies
		ArrayList<Integer> newPitches = new ArrayList<Integer>(); //initialize new array for generated pitches
		ArrayList<Double> newRhythms = new ArrayList<Double>(); //initialize new array for generated rhythms
	
		//training
		pitchGenerator.train(midiNotesMary.getPitchArray());
		rhythmGenerator.train(midiNotesMary.getRhythmArray());
		
		//generate 10,000 times
		for (int i = 0; i <= 10000; i++) {
			newPitches = pitchGenerator.generate(20); //put new generated pitches in an array
			newRhythms = rhythmGenerator.generate(20); //put new generated rhythms in an array
			
			pitchGenerator.train(newPitches); //train newly generated melody
			rhythmGenerator.train(newRhythms);
			}
		
		//print out the distribution
		System.out.println("Unit Test 3:\n");
		System.out.println("\n Pitches: \n" + "-----Probability Distribution-----\n");
		pitchGenerator.print(newPitches);
		System.out.println("\n Rhythms: \n" + "-----Probability Distribution-----\n");
		rhythmGenerator.print(newRhythms);
		System.out.println("------");

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
