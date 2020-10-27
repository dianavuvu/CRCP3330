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
		Character[] myList = {'a', 'c', 'a', 'd', 'a', 'a', 'c', 'b', 'd', 'a'};
		
		//set data in array list
		ArrayList<T> testList = new ArrayList(Arrays.asList(myList));	
		
		//initialize tree
		Tree<T> t = new Tree<T>();
		
		//train tree
		t.train(testList);
		
		//print out the tree
		System.out.println("Unit Test Two: \n");
		System.out.println(" acadaacbda \n" + "-----Tree-----\n");
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