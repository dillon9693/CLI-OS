/**
 * A class that implements a simple queue of processes
 * with a singly Linked List.
 */

public class Queue {

	public Node front; //variable stores the front of the queue
	public Node rear; // variable stores the end of the queue
	public int size; //size of the queue
	
	/**
	 * Constructor
	 */
	public Queue() {
		front = null;
		rear = null;
		size = 0;
	}
	/**
	 *Adds a value to the rear of the queue
	 *		@param integer value to be added to the queue
	 */
	public void enqueue(int c, int d, int e, int f, int p) {
		Node v = new Node(c, d, e, f, null,p);
		if(size == 0)
			front = v;
		else
			rear.next = v;
		rear = v;
		size++;
	}
	/**
	 *Adds a node to the rear of the queue
	 *		@param node object to be added to the queue
	 */ 
	public void enqueueNode(Node n) {
		if(size == 0)
			front = n;
		else
			rear.next = n;
		rear = n;
		size++;
	}
	
	/**
	 *Removes and returns a value from the front of the queue
	 *		@return integer value that is removed from the queue
	 */
	public Node dequeue() {
		Node temp = front;
		front = front.next;
		if(size == 1)
			rear = null;
		size--;
		return temp;
	}
	
	/**
	 *Returns the value at the front of the queue.
	 *		@return integer value at the front of the queue
	 */
	public int front() {
		return front.cycles;
	}
	
	/**
	 *Removes the node at the front of the queue and
	 *and puts it at the back of the queue.
	 */
	public void frontToBack() {
		Node temp = dequeue();
		if(temp.cycles != 0) {
			enqueueNode(temp);
		}
	}
	
	/**
	 * Returns true if the queue is empty, false otherwise.
	 *		@return true if queue is empty, false otherwise
	 */
	public boolean isEmpty() {
		if(size == 0)
			return true;
		return false;
	}
	
	/**
	 * Returns the number of values in the queue.
	 *		@return number of integers in the queue
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Displays the contents of the queue.
	 */
	public void display(){
		Node cur = front;
		while (cur != null){
			System.out.println("Process " + cur.processNum);
			System.out.println("Number of cycles required: " + cur.cycles);
			System.out.println("Number of pages of memory required: " + cur.memory);
			System.out.println("Priority: " + cur.priority);
			System.out.println("Probability of I/O event: " + cur.prob);
			
			System.out.println("");
			System.out.println("");
			cur = cur.next;
		}
		System.out.println("");
	}
	
	public void displayFront(){
		Node cur = front;
		//while (cur != null){
			System.out.println("Process " + cur.processNum);
			System.out.println("Number of cycles required: " + cur.cycles);
			System.out.println("Number of pages of memory required: " + cur.memory);
			System.out.println("Priority: " + cur.priority);
			System.out.println("Probability of I/O event: " + cur.prob);
			
			System.out.println("");
			System.out.println("");
			//cur = cur.next;
		//}
		System.out.println("");
	}
}

/** 
 * A class with a main method to test
 * the queue's methods.
 
class QueueTest {
	public static void main(String args[]) {
		Queue q = new Queue();
		
		
		q.enqueue(5,6,7,8);
		q.display();
		q.enqueue(6,7,8,9);
		q.display();
		q.enqueue(7,8,9,1);
		q.display();
		System.out.println(q.dequeue());
		q.display();
		q.dequeue();
		q.dequeue();
		System.out.println(q.isEmpty());
		q.enqueue(8,9,1,2);
		q.enqueue(4,5,6,7);
		q.enqueue(9,1,2,3);
		q.display();
		System.out.println("Size is " + q.size());
		System.out.println("The front of the queue is " + q.front());
	}
}
	*/
	
		
		
	