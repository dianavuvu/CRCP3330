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
		
		System.out.println("Pitches: \n" + "-----Probability Distribution-----\n");
		for (int i = 0; i<= 5; i++) {
			System.out.println("Token: " + token + "|" + "Probability:"+ midiNotesMary.getPitchArray(i));		//print out all the pitches
		}
		
		System.out.println("Rhythms: \n" + "-----Probability Distribution-----\n");
		for (int i = 0; i<= 5; i++) {
			System.out.println("Token: " + token + "|" + "Probability:"+ midiNotesMary.getRhythmArray(i));		
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
