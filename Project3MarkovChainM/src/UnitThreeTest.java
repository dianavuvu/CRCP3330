/*
 * Programmer: Diana Vu
 * Date: October 12, 2020
 * Description: This is Unit Three test that will generate 10,000 melodies and print out the data set from those entries 
 * from projects 1 and 2 and 3
 * 
 */

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

public class UnitThreeTest<T> {
	
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
		pitGenerator.train(midiNotesMary.getPitchArray()); //Project 2
		rhyGenerator.train(midiNotesMary.getRhythmArray());
		
		//generate 10,000 times Project 1
		for (int i = 0; i <= 10000; i++) {
			newPitches = pitchGenerator.generate(20); //put new generated pitches in an array
			newRhythms = rhythmGenerator.generate(20); //put new generated rhythms in an array
			
			pitchGenerator.train(newPitches); //train newly generated melody
			rhythmGenerator.train(newRhythms);
			}
		
		//generate 10,000 times Project 2
		for (int i = 0; i <= 10000; i++) {
			T initPit = (T) pitchGenerator.generate(1); //generate 1 token using generator from last project
			T initRhy = (T) rhythmGenerator.generate(1);
		
			newPit = pitGenerator.generate(20); //generate 20 notes using initToken from MarkovGenerator
			newRhy = rhyGenerator.generate(20);
		
			pitGenerator.train(newPit);	//get probabilities of generation
			rhyGenerator.train(newRhy);
			}
		
		//print out the distribution
		System.out.println("Project 1:\n" + "Unit Test 3:");
		System.out.println("\n Pitches: \n" + "-----Probability Distribution-----\n");
		pitchGenerator.print(newPitches);
		System.out.println("\n Rhythms: \n" + "-----Probability Distribution-----\n");
		rhythmGenerator.print(newRhythms);
		System.out.println("------");
		
		//print out transition table
		System.out.println("Project 2\n " + "Unit Test One: \n");
		System.out.println("\n Pitches: \n" + "-----Transition Table -----\n");
		pitGenerator.printMarkov(midiNotesMary.getPitchArray());
		System.out.println("\n Rhythms: \n" + "-----Transition Table-----\n");
		rhyGenerator.printMarkov(midiNotesMary.getRhythmArray());
		System.out.println("------");
		
		//generate 10,000 times Project 3
		System.out.println("Project 3:\n" + "Unit Test 3:");
		
			for(int i = 1; i <= 10; i++) {
				MarkovOrderM<Integer> pGen = new MarkovOrderM<Integer>(i); //create new object
				MarkovGenerator<Integer> pitG = new MarkovGenerator<Integer>();
				
				//training data
				pGen.train(midiNotesMary.getPitchArray());
				pitG.train(midiNotesMary.getPitchArray());
				
				//generate data
				ArrayList<Integer> newP = new ArrayList<Integer>(); //initialize array lists
				ArrayList<Integer> nP = new ArrayList<Integer>();
				
				nP = pitG.generate(20); //generating initSeq
				
				//for (int j = 0; j <= 10000; j++) {
					newP = pGen.generate(nP, 20); //generate new tokens
					
					pGen.train(newP); //use newly generated tokens and train
				//}
				
				System.out.println("\n Pitches for order " + i + ":\n" + "-----Transition Table -----\n");
				pGen.printMarkovM(newP);
				System.out.println("------");
			}

			for(int i = 1; i <= 10; i++) {
				MarkovOrderM<Double> rGen = new MarkovOrderM<Double>(i); //create new object
				MarkovGenerator<Double> rhyG = new MarkovGenerator<Double>();
				
				//training data
				rGen.train(midiNotesMary.getRhythmArray());
				rhyG.train(midiNotesMary.getRhythmArray());
				
				ArrayList<Double> newR = new ArrayList<Double>(); //initialize array lists
				ArrayList<Double> nR = new ArrayList<Double>();
				
				nR = rhyG.generate(20); //generate initSeq
				
				//for (int j = 0; j <= 10000; j++) {
					newR = rGen.generate(nR, 20); //generate new tokens
					
					rGen.train(newR); //use newly generated tokens and train
				//}
				
				System.out.println("\n Rhythms for order " + i + ":\n" + "-----Transition Table -----\n");
				rGen.printMarkovM(newR);
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
