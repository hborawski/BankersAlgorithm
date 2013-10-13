import java.util.ArrayList;


public class Banker {
	
	private int totalResources;
	private ArrayList<String> registeredNames;
	private ArrayList<int[]> registeredClaims;
	
	public Banker(int nUnits){
		totalResources = nUnits;
		registeredNames = new ArrayList<String>();
		registeredClaims = new ArrayList<int[]>();
	}
	
	public synchronized void setClaim(int nUnits){
		//The current  thread (available via static method Thread. currentThread()) attempts 
		//to register a claim for up to nUnits units of resource.
		
		//Check to see if thread already has claim
		for(int i=0;i<registeredNames.size();i++){
			if(registeredNames.get(i).matches(Thread.currentThread().getName())){
				System.exit(1);
			}
		}
		
		//Check to see if the thread is requesting an invalid amount of resources
		if((nUnits > totalResources) || (nUnits < 0)){
			System.exit(1);
		}
		
		//Register the thread
		registeredNames.add(Thread.currentThread().getName());
		int[] temp = new int[2];
		temp[0] = 0;
		temp[1] = nUnits;
		System.out.println("Thread " + Thread.currentThread().getName() + " sets a claim for " + nUnits);
		
	}
	
	public boolean request(int nUnits){
		//The current thread requests nUnits more resources.
		return false;
	}
	
	public void release(int nUnits){
		//The current thread releases nUnits resources.
	}
	
	public int allocated(){
		//Returns the number of units allocated to the current thread.
		return 0;
	}
	
	public int remaining(){
		//Returns the number of units remaining in the current thread's claim.
		return 0;
	}
}
