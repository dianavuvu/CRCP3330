/*
 * Programmer: Diana Vu
 * Date: September 8, 2020
 * Description: This is Unit One test that will test the train method in probability generator 
 * 
 */

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;

public class UnitOneTest<T> {
	
	UnitOneTest(){
		
	}
	
	void run() {		
		
		//set data
		String[] myList = {"a", "b", "r", "a", "c", "a", "d", "a", "b", "r", "a"};
		
		//set data in array list
		ArrayList<T> testList = new ArrayList(Arrays.asList(myList));	
		
		//initialize tree
		Tree<T> t = new Tree<T>();
		
		//train tree
		t.train(testList);
		
		//print out the tree
		System.out.println("Unit Test One: \n");
		System.out.println(" abracadabra \n" + "-----Tree-----\n");
		t.print();

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
