/** 
 * Class definition for a Node of a singly linked list of Game Entries. 
 */
public class Node {

	public int cycles;	//number of cycles in a process
	public int memory;	//number of pages of memory needed
	public int priority;	//priority
	public int prob;	//probability of I/O event occurring in given cycle
	public Node next;		//refers to the next node in the list
	public int processNum;
	public boolean inMem;
	
	/** 
	 * Constructor: creates a node with the given entries and next Node. 
	 */
	public Node(int i, int j, int k, int l, Node n, int p) {
		cycles = i;
		memory = j;
		priority = k;
		prob = l;
		next = n;
		processNum = p;
		inMem = false;
	}
	
	public void display() {
		System.out.println(cycles);
		System.out.println(memory);
		System.out.println(priority);
		System.out.println(prob);
	}
}
