/*
 * Programmer: Diana Vu
 * Date: September 14, 2020
 * Description: This is Unit Two test that will test the generate method in probability generator 
 * 
 */

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UnitTwoTest {
	
	MelodyPlayer player;
	
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
		
		//training
		pitchGenerator.train(midiNotesMary.getPitchArray());
		rhythmGenerator.train(midiNotesMary.getRhythmArray());
		
		//generating melodies
		pitchGenerator.generate(20);
		rhythmGenerator.generate(20);
		
		//printing out pitch and rhythm of generated melody
		System.out.println("\n Pitches: \n" + "-----Probability Distribution-----\n");
		pitchGenerator.print(midiNotesMary.getPitchArray());
		System.out.println("\n Rhythms: \n" + "-----Probability Distribution-----\n");
		rhythmGenerator.print(midiNotesMary.getRhythmArray());

		//player = new MelodyPlayer(this, 100.0f);

//		player.setup();
//		player.setMelody(pitchGenerator.generate(20));
//		player.setRhythm(rhythmGenerator.generate(20));


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