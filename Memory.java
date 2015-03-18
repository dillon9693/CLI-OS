/**
 * A class to represent the memory that the processor
 * accesses when executing processes.
 */
 
 public class Memory {
 
	public int[] mem;
	
	/*
	 * Constructor
	 */
	public Memory() {
		mem = new int[300];
	}
	
	/**
	 * Method to compact the memory to allow more space for processes
	 */
	public void compact() {
		int counter = 0;
		for(int a=0; a<mem.length; a++) {
			counter = 0;
			if(mem[a] == 0) { //checks if location has nothing in a page
				counter++;
				//checks how many consecutive empty pages there are
				for(int b=a+1; b<mem.length; b++) {
					if(mem[b] == 0) {
						counter++;
					}
					else {
						break;
					}
				}
				for(int c=a; c<mem.length; c++) {
					if((c + counter) > (mem.length - 1)) {
						mem[c] = 0;
					}
					else {
						//set empty pages to value of other process
						mem[c] = mem[c + counter];
					}
				}
			}
		}				
	} 
	/*
	 * Displays the contents of the memory.
	 */
	public void display() {
		System.out.println("MEMORY");
		System.out.println("");
		for(int i=0; i<mem.length; i++) {
			System.out.print(mem[i] + ", ");
		}
		System.out.println("\n");
	}
}		