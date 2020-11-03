/*
 * Programmer: Diana Vu
 * Date: September 14, 2020
 * Description: This is Unit Two test that will test the generate method in probability generator 
 * 
 */

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;

public class UnitTwoTest<T> {

	UnitTwoTest(){
		
	}
	
	void run() {
		//set data
		String[] myList = {"a", "c", "a", "d", "a", "a", "c", "b", "d", "a"};
		
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
		System.out.println("Unit Test Two: \n");
		System.out.println(" acadaacbda:  PST L=3 Pmin=0.1 \n" + "-----Tree-----\n");
		t.print();

		System.out.println("\n acadaacbda:  PST L=3 Pmin=0.15 \n" + "-----Tree-----\n");
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