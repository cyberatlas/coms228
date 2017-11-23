package edu.iastate.cs228.hw4;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * @author Alexander Stevenson
 * 
 *         An application class
 */
public class Dictionary {
	public static <K,V>void main(String[] args) throws FileNotFoundException {
		
		
		File infile = new File(args[0]);
		Scanner s = new Scanner(infile);
		//Creates the new entry tress
		//K is character V is String 
		EntryTree<Character, String> entryTree = new EntryTree<>();
		
		String instruction = "";
		Character[] key;
	
		while (s.hasNextLine()) {
			
			instruction = s.next();		
		}
	
	}
	
}
