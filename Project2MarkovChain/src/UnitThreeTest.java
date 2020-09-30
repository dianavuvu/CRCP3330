/*
 * Programmer: Diana Vu
 * Date: September 28, 2020
 * Description: This is Unit Three test that will generate 10,000 melodies and print out the data set from those entries 
 * from projects 1 and 2
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
		MarkovGenerator<Integer> pitGenerator = new MarkovGenerator<Integer>(); //Project 2
		MarkovGenerator<Double> rhyGenerator = new MarkovGenerator<Double>();

		
		//generating melodies
		ArrayList<Integer> newPitches = new ArrayList<Integer>(); //initialize new array for generated pitches
		ArrayList<Double> newRhythms = new ArrayList<Double>(); //initialize new array for generated rhythms
		ArrayList<Integer> newPit = new ArrayList<Integer>(); //Project 2
		ArrayList<Double> newRhy = new ArrayList<Double>(); 
		//T initToken;
	
		//training
		pitchGenerator.train(midiNotesMary.getPitchArray());
		rhythmGenerator.train(midiNotesMary.getRhythmArray());
//		pitGenerator.train(midiNotesMary.getPitchArray()); //Project 2
//		rhyGenerator.train(midiNotesMary.getRhythmArray());
		
		//generate 10,000 times Project 1
		for (int i = 0; i <= 10000; i++) {
			newPitches = pitchGenerator.generate(20); //put new generated pitches in an array
			newRhythms = rhythmGenerator.generate(20); //put new generated rhythms in an array
			
			pitchGenerator.train(newPitches); //train newly generated melody
			rhythmGenerator.train(newRhythms);
			}
		
		//generate 10,000 times Project 2
//		for (int i = 0; i <= 10000; i++) {
//			initToken = pitchGenerator.generate(1); //generate 1 token
//			initRhy = rhythmGenerator.generate(1);
		
//			newPit = pitGenerator.generate(initToken, 20);
//			newRhy = rhyGenerator.generate(initRhy, 20);
		
//			pitGenerator.train(newPit);	
//			rhyGenerator.train(newRhy);
		
//			}
		
		//print out the distribution
//		System.out.println("Project 1:\n" + "Unit Test 3:");
//		System.out.println("\n Pitches: \n" + "-----Probability Distribution-----\n");
//		pitchGenerator.print(newPitches);
//		System.out.println("\n Rhythms: \n" + "-----Probability Distribution-----\n");
//		rhythmGenerator.print(newRhythms);
//		System.out.println("------");
		
		//print out transition table
		System.out.println("Project 2\n " + "Unit Test One: \n");
		System.out.println("\n Pitches: \n" + "-----Transition Table -----\n");
		pitGenerator.printMarkov(midiNotesMary.getPitchArray());
		System.out.println("\n Rhythms: \n" + "-----Transition Table-----\n");
		rhyGenerator.printMarkov(midiNotesMary.getRhythmArray());
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
