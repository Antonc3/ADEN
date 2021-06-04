package tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class MyBSTBenchmark.
 */
public class MyBSTBenchmark {

	/** The a dict. */
	private static ArrayList<String> aDict;
	
	/** The a small. */
	private static ArrayList<String> aSmall;
	
	/** The b dict. */
	private static MyBST<String> bDict;
	
	/** The b small. */
	private static MyBST<String> bSmall;
	
	/** The rnd string. */
	private static String[] rndString;
	
	/** The alist results. */
	private static long[] alistResults = new long[10];
	
	/** The bst results 1. */
	private static long[] bstResults1 = new long[10];
	
	/** The bst results 2. */
	private static long[] bstResults2 = new long[10];
	
	/** The num trials. */
	private static int numTrials = 10;
	
	/** The num search. */
	private static int numSearch = 5000;
	
	/** The inc search. */
	private static int incSearch = 5000;
	
	/**
	 * Instantiates a new my BST benchmark.
	 */
	public MyBSTBenchmark() {
		aDict = new ArrayList<String>();
		aSmall = new ArrayList<String>();
		bDict = new MyBST<String>();
		bSmall = new MyBST<String>();

	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		MyBSTBenchmark bm = new MyBSTBenchmark();
		/* TODO: You will be benchmarking a BST against a ArrayList against two dictionaries:
		 * words.small.txt and dict.txt - we will be using these in a later project. You will also be comparing
		 * your BST performance against words.small_po.txt and dict_po.txt - so for each dictionary, there will be 
		 * 3 sets of data: the performance of the ArrayList using a linear search strategy, the performance of the 
		 * BST search on the same dictionary, and the performance of the BST search on the "_po" version of the dictionary
		 *  
		 */
		
		// Start with the small dictionary: words.small.txt
		// initialize the array list from the dictionary  - this method is provided for you
		bm.initArrayList("resources/words.small.txt",aSmall);
		// initialize an array of 4096 strings with random words from the arrayList
		// This method is provided for you...
		bm.initRandomSearchArray(aSmall);
		System.out.println("Benchmarking ArrayList on words.small.txt");
		// Benchmark linear searches on the arraylist. This method is provided for you
		bm.benchmarkArrayList(aSmall, alistResults);
		aSmall = new ArrayList<String>();   // frees the memory used by the arraylist
 
		// TODO #1 - complete this method - it will be reused 
		bm.initBST("resources/words.small.txt",bSmall);
		System.out.println("Benchmarking BST on words.small.txt");
		// TODO #2 - complete this method
		bm.benchmarkBST(bSmall,bstResults1);
		bSmall = new MyBST<String>();
		
		// TODO #3 - repeat all the steps for initializing and benchmarking the BST, but this 
		//           time use words.small_po.txt as the file to initialize the BST. Make sure to use
		//           the bstResults2 array to record the data. You can reuse the bSmall bst here...

		bm.initBST("resources/words.small_po.txt", bSmall);
		bm.benchmarkBST(bSmall, bstResults2);
		
		// print the data..
		numSearch = 5000;
		System.out.println("Search performance on words.small.txt");
		System.out.println("numSearch\tArrayList\tBST1\tBST2");
		for (int i = 0; i < numTrials; i++ ) {
			System.out.println(numSearch+"\t"+alistResults[i]+"\t"+bstResults1[i]+"\t"+bstResults2[i]);
			numSearch += 5000;
		}
		
		// TODO #4: repeat all of the above code, but use dict.txt as the dictionary (and
		//          dict_po.txt for TODO #3. Use the aDict array and bDict BST for this...

		System.out.println("Benchmarking ArrayList on dict.txt");

		bm.initArrayList("resources/dict.txt", aDict);
		bm.initRandomSearchArray(aDict);
		bm.benchmarkArrayList(aDict, alistResults);
		System.out.println("Benchmarking BST on dict.txt");
		bm.initBST("resources/dict.txt", bDict);
		bm.benchmarkBST(bDict, bstResults1);
		bDict = new MyBST<String>();
		bm.initBST("resources/dict_po.txt", bDict);
		bm.benchmarkBST(bDict, bstResults2);
		numSearch = 5000;
		System.out.println("Search performance on dict.txt");
		System.out.println("numSearch\tArrayList\tBST1\tBST2");
		for (int i = 0; i < numTrials; i++ ) {
			System.out.println(numSearch+"\t"+alistResults[i]+"\t"+bstResults1[i]+"\t"+bstResults2[i]);
			numSearch += 5000;
		}
	}

	/**
	 * Inits the random search array. Note that this will randomly select
	 * 4096 words to search for. Why 4096? That is because 4096 is 2^12, so
	 * implementing a modulo is extremely simple and efficient: if i is incrementing
	 * between 0 and 50000, the index to the rndString is (i & 0xfff) 
	 *
	 * @param alist the alist
	 */
	public void initRandomSearchArray(ArrayList<String> alist) {
		rndString = new String[4096];
		Random rn = new Random();
		int maxIndex = alist.size();
		for (int i = 0; i < rndString.length; i++ ) {
			rndString[i] = alist.get(rn.nextInt(maxIndex));
		}
	}
	
	/**
	 * Inits the array list.
	 *
	 * @param filename the filename
	 * @param aList the ArrayList to initialize
	 */
	public void initArrayList(String filename, ArrayList<String> aList) {
		try (BufferedReader br = new BufferedReader(new FileReader (filename))) {
			String line;
			while ((line = br.readLine())!= null) {
				aList.add(line);
			}
		} catch (IOException e) { 
			System.err.println("Error in reading file: "+filename);
			e.printStackTrace();
		}		
	}

	/**
	 * Inits the BST from the specified file.
	 *
	 * @param filename the filename
	 * @param bst the bst to initialize  
	 */
	public void initBST(String filename, MyBST<String> bst) {
		try (BufferedReader br = new BufferedReader(new FileReader (filename))) {
			String line;
			while ((line = br.readLine())!= null) {
				bst.insert(line);
			}
		} catch (IOException e) { 
			System.err.println("Error in reading file: "+filename);
			e.printStackTrace();
		}		
	}
	
	
    /**
     * Benchmark BST. Uses the search method of the BST to find a random word
     *
     * @param bst the bst
     * @param results an array of longs that represent the results of each trial
     */
    public void benchmarkBST(MyBST<String> bst, long[] results) {
    	numSearch = 5000;
    	
    	for (int trial = 0;  trial < numTrials; trial++) {
    		long start = System.nanoTime();
    		for (int i = 0; i < numSearch; i++) {
    			String word = rndString[i & 0xfff]; 
    			bst.search(word);
    		}
    		long stop = System.nanoTime();
    		results[trial] = stop - start;
    		numSearch += incSearch;
    	}    
    }
	
	/**
	 * Benchmark array list. Performs a linear search for the random word.
	 *
	 * @param aList the a list
	 * @param results the results
	 */
	public void benchmarkArrayList(ArrayList<String> aList, long[] results) {
    	numSearch = 5000;
    	
    	for (int trial = 0;  trial < numTrials; trial++) {
    		long start = System.nanoTime();
    		for (int i = 0; i < numSearch; i++) {
    			String word = rndString[i & 0xfff];   // equivalent to i % 4096
    			for (int j = 0; !(word.equals(aList.get(j))); j++) ;  // implements a linear search through the arraylist
    		}
    		long stop = System.nanoTime();
    		results[trial] = stop - start;
    		numSearch += incSearch;
    	}
 	}
	
}


