/*
 * Programmer: Diana Vu
 * Date: September 8, 2020
 * Description: This is Unit One test that will test the train method in probability generator 
 * 
 */

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UnitFourTest {
	
	UnitFourTest(){
		
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
		
		//initialize tree
		Tree t = new Tree();
		Tree two = new Tree();
		
		//training
		float pMin = 0.1f;
		t.train(midiNotesMary.getPitchArray(), pMin);
		
		float p = 0.15f;
		two.train(midiNotesMary.getPitchArray(), p);
		
		//print out the tree
		System.out.println("Unit Test Four: \n");
		System.out.println(" Mary Had a Little Lamb:  PST L=3 Pmin=0.1 \n" + "-----Tree-----\n");
		t.print();

		System.out.println("\n Mary Had a Little Lamb:  PST L=3 Pmin=0.15 \n" + "-----Tree-----\n");
		two.print();

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
