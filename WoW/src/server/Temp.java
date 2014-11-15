package server;
import java.io.*;
import java.util.Hashtable;

import UsefulThings.Quicksort;
//import client.Whirlpool;
public class Temp {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] a = {"apples","pudd","pie","fruit","qip","zip","moon"};
		Quicksort qsort = new Quicksort();
		a = (String[]) qsort.sort(a);
		for(String i: a)
			System.out.println(i);
	}

}
