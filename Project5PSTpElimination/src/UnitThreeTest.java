/*
 * Programmer: Diana Vu
 * Date: September 14, 2020
 * Description: This is Unit Three test that will generate 10,000 melodies and print out the data set from those entries
 * 
 */

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;

public class UnitThreeTest<T> {
	
	UnitThreeTest(){
		
	}
	
	void run() {
		//set data
		String[] myList = {"a", "b", "c", "c", "c", "d", "a", "a", "d", "c", "d", "a", "a", "b", "c", "a", "d", "a", "d"};
		
		//set data in array list
		ArrayList<T> testList = new ArrayList(Arrays.asList(myList));	
		
		//initialize tree
		Tree<T> t = new Tree<T>();
		Tree<T> two = new Tree<T>();
		
		//train tree
		float pMin = 0.1f;
		t.train(testList, pMin);
		
		float p = 0.15f;
		two.train(testList, p);
		
		//print out the tree
		System.out.println("Unit Test Three: \n");
		System.out.println(" abcccdaadcdaabcadad:  PST L=3 Pmin=0.1 \n" + "-----Tree-----\n");
		t.print();

		System.out.println("\n abcccdaadcdaabcadad:  PST L=3 Pmin=0.15 \n" + "-----Tree-----\n");
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
