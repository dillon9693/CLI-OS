
/*Java Class to read in from an external text file and populate
 * a queue with processes. The user interface within the terminal
 * is capable of viewing all current processes and provides the
 * ability to add a new process to the queue. When the user chooses
 * to quit, the program shuts down and writes the new process queue
 * to a text file.
 */

import java.util.Scanner;
import java.io.*;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class OSInterface {
	
	//Method that provides a menu for user to choose what to do
	public static void choose() {
		System.out.println("");
		System.out.println("Welcome to the process manager!");
		System.out.println("");
		
		System.out.println("Enter 1 to see processes: ");
		System.out.println("Enter 2 to add a new process: ");
		System.out.println("Enter 3 to run processes with First Come First Served: ");
		System.out.println("Enter 4 to run processes with Round Robin: ");
		System.out.println("Enter 5 to quit: ");
	}
	
	/*Method to write the current information stored into the queue
	 * to the "process.txt" file.
	 */
	public static void writeFile(Queue q) {
		try{
			FileWriter fout = new FileWriter("process.txt");
			BufferedWriter out = new BufferedWriter(fout);
			
			Node cur = q.front;
			int i = 1;
			while(i <= q.size) { //while loop cycles through each node of the queue
				out.write(cur.cycles + ",");
				out.write(cur.memory + ",");
				out.write(cur.priority + ",");
				out.write(cur.prob + ",");
				out.write(cur.processNum + ",");
				if(cur.next != null)
					out.newLine();
				cur = cur.next;
				i++;
			}
			out.close();
			}
			catch (IOException e) { //if there is an error, the file will not save correctly
				System.out.println("Output file \"userdata.txt\" did not save correctly.\nThe program will now exit.\n");
				System.exit(0);
			} //end catch
	}
	
	public static void main(String[] args) 
	{
		Scanner s = new Scanner(System.in);
		
		Queue processQueue = new Queue(); //creates the process queue
		Memory memory = new Memory();
		
		int counter = 1;
		try {
		
			FileReader reader = new FileReader("process.txt");
			Scanner processScanner = new Scanner(reader);
			processScanner.useDelimiter(",");
			
			
			while (processScanner.hasNextLine()) {

				//reads each element, seperated by commas, from the text file
				String a = processScanner.next();
				String b = processScanner.next();
				String c = processScanner.next();
				String d = processScanner.next();
				//String e = processScanner.next();

				//changes the numbers, as strings, into actual integers
				int first = Integer.parseInt(a);
				int second = Integer.parseInt(b);
				int third = Integer.parseInt(c);
				int fourth = Integer.parseInt(d);
				//int fifth = Integer.parseInt(e);
				
				//creates a Node in the queue for each line of information
				processQueue.enqueue(first, second, third, fourth, counter);
				
				processScanner.nextLine();
				counter++;
					
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Error");
		}
		do {
			choose(); //display menu
			
			String e = s.next();//store user input into a variable
			
			if(e.equals("1")) {
				System.out.println("List of Current Processes \n");
				processQueue.display(); //displays all processes
				try {	//wait 4 seconds before displaying menu again when it loops
				Thread.currentThread().sleep(3000);
				}
				catch(InterruptedException ie){
					System.out.println("Error");
				}
			}
			
			if(e.equals("2")) {
				//asks for information about the process to be added
				System.out.println("Enter New Process Information");
				System.out.print("Number of cycles required: ");
				int cyc = Integer.parseInt(s.next());
				System.out.print("Number of pages of memory needed: ");
				int mem = Integer.parseInt(s.next());
				System.out.print("Priority: ");
				int prior = Integer.parseInt(s.next());
				System.out.print("Probability of I/O event: ");
				int prob = Integer.parseInt(s.next());
				
				
				//adds the new process to the queue
				processQueue.enqueue(cyc, mem, prior, prob,counter);
				counter++;
			}
			if(e.equals("3")) { //First Come First Served
				//loops through queue thru first come first served algorithm
				while(processQueue.front != null) {
					System.out.println("\n\n\n");
					System.out.println("Currently Running Process....");
					System.out.println("");
					processQueue.displayFront(); //displays the process at the front of the queue
					processQueue.dequeue();
					System.out.println("\n\n");
					System.out.println("PROCESS COMPLETED");
				try {	//wait 1 second before displaying running process
					Thread.currentThread().sleep(3000);
				}
				catch(InterruptedException ie){
					System.out.println("Error");
				}
				}//end while
			}
			if(e.equals("4")) { //Round Robin
				int lastLoc = 0;
				int quantum = 4; //time quantum
				while(processQueue.size != 0) {
					Node f = processQueue.front;
					if(quantum == 4) {
						memory.compact(); //calls the memory compaction method
						if(f.inMem == false) {
							//add process number to next available location in memory
							for(int a=lastLoc; a<(lastLoc + f.memory); a++) {
								memory.mem[a] = f.processNum;
							}
							lastLoc += f.memory;
							f.inMem = true;
						}
					}

					
					System.out.println("\n\n\n");
					System.out.println("Currently Running Process");
					System.out.println("");
					
					processQueue.displayFront();//displays running process at the front of the queue
					memory.display();//displays the memory
					
					//decrements the number of cycles required to complete the running process
					processQueue.front.cycles--;
					quantum--; //decrements the time quantum
					
					if(processQueue.front.cycles == 0) { //if process is complete
						//deletes process from memory when completed
						for(int b=0; b<memory.mem.length; b++) {
							if(memory.mem[b] == processQueue.front.processNum) {
								memory.mem[b] = 0; //sets processes previous pages to unoccupied
							}
						}
							
						processQueue.dequeue();
						counter--;
						
						System.out.println("PROCESS COMPLETED");
						quantum = 4; //reset time quantum
					}
					else if(quantum == 0) { //if the time quantum is 0
						if(processQueue.size > 1){
							processQueue.frontToBack();
							System.out.println("PROCESS SWITCHING");
							quantum = 4; //reset time quantum
						}
					}
					
					try {	//wait 4 seconds before displaying running process
						Thread.currentThread().sleep(1000);
					}
					catch(InterruptedException ie){
						System.out.println("Error");
					}
				
				}//end while
			}
			if(e.equals("5")){
				System.out.println("");
				System.out.println("Goodbye!");
				
				writeFile(processQueue); //writes all processes in queue to text file
				break;
			}
		}	while(true);
	}
}

			