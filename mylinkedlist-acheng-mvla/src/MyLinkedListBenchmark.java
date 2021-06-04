import java.util.ArrayList;

public class MyLinkedListBenchmark {
	public MyLinkedListBenchmark() {
		
	}
	public static void main(String[] args) {
		MyLinkedListBenchmark bm = new MyLinkedListBenchmark();
		bm.addAtFront();
		bm.addAtBack();
		bm.addMiddle();
		bm.getRandIndex();
	}
	//add at front
	public  void addAtFront() {
		System.out.println("Add At Front Test");		System.out.println("NumsAdded \t ArrayList \t LinkedList");

		ArrayList<Integer> aList;
		MyLinkedList<Integer> lList;
		int trials = 10;
		int numIntsToAdd = 5000;
		int trialInc = 5000;
		for(int i =0; i < trials; i++) {
			aList = new ArrayList<>();
			long aStartTime = System.nanoTime();
			for(int j =0; j < numIntsToAdd;j++) {
				aList.add(0,j);
			}
			long aEndTime = System.nanoTime();
			lList = new MyLinkedList<>();
			long lStartTime = System.nanoTime();
			for(int j =0; j < numIntsToAdd;j++) {
				lList.add(0,j);
			}
			long lEndTime = System.nanoTime();
			System.out.print(numIntsToAdd + "\t" + (aEndTime-aStartTime) + "\t" + (lEndTime-lStartTime)+"\n");
			numIntsToAdd+=trialInc;
		}
	}
	//add at back
	public void addAtBack() {
		System.out.println("Add At Back Test");		System.out.println("NumsAdded \t ArrayList \t LinkedList");

		ArrayList<Integer> aList;
		MyLinkedList<Integer> lList;
		int trials = 10;
		int numIntsToAdd = 5000;
		int trialInc = 5000;
		for(int i =0; i < trials; i++) {
			aList = new ArrayList<>();
			long aStartTime = System.nanoTime();
			for(int j =0; j < numIntsToAdd;j++) {
				aList.add(j);
			}
			long aEndTime = System.nanoTime();
			lList = new MyLinkedList<>();
			long lStartTime = System.nanoTime();
			for(int j =0; j < numIntsToAdd;j++) {
				lList.add(j);
			}
			long lEndTime = System.nanoTime();
			System.out.print(numIntsToAdd + "\t" + (aEndTime-aStartTime) + "\t" + (lEndTime-lStartTime)+"\n");
			numIntsToAdd+=trialInc;
		}
	}
	public void addMiddle() {
		System.out.println("Add In Middle Test");		System.out.println("NumsAdded \t ArrayList \t LinkedList");

		ArrayList<Integer> aList;
		MyLinkedList<Integer> lList;
		int trials = 10;
		int numIntsToAdd = 5000;
		int trialInc = 5000;
		for(int i =0; i < trials; i++) {
			aList = new ArrayList<>();
			long aStartTime = System.nanoTime();
			for(int j =0; j < numIntsToAdd;j++) {
				aList.add(aList.size()/2,j);
			}
			long aEndTime = System.nanoTime();
			lList = new MyLinkedList<>();
			long lStartTime = System.nanoTime();
			for(int j =0; j < numIntsToAdd;j++) {
				lList.add(lList.size()/2,j);
			}
			long lEndTime = System.nanoTime();
			System.out.print(numIntsToAdd + "\t" + (aEndTime-aStartTime) + "\t" + (lEndTime-lStartTime)+"\n");
			numIntsToAdd+=trialInc;
		}
	}
	public void getRandIndex() {
		System.out.println("Get Rand Index Test");
		System.out.println("NumsAdded \t ArrayList \t LinkedList");
		ArrayList<Integer> aList;
		MyLinkedList<Integer> lList;
		int trials = 10;
		int numIntsToAdd = 5000;
		int trialInc = 5000;
	
		for(int i =0; i < trials; i++) {
			aList = new ArrayList<>();
			int ind[] = new int[numIntsToAdd];
			for(int j =0; j < numIntsToAdd;j++) {
				aList.add(0,j);
				ind[j] = (int) Math.floor(Math.random()*numIntsToAdd);
			}
			long aStartTime = System.nanoTime();
			for(int j = 0; j < numIntsToAdd; j++) {
				aList.get(ind[j]);
			}
			long aEndTime = System.nanoTime();
			lList = new MyLinkedList<>();
			for(int j =0; j < numIntsToAdd;j++) {
				lList.add(0,j);
			}
			long lStartTime = System.nanoTime();
			for(int j = 0; j < numIntsToAdd; j++) {
				lList.get(ind[j]);
			}
			long lEndTime = System.nanoTime();
			System.out.print(numIntsToAdd + "\t" + (aEndTime-aStartTime) + "\t" + (lEndTime-lStartTime)+"\n");
			numIntsToAdd+=trialInc;
		}
	}
}
