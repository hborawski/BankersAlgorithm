
public class Banker {
	private int resources;
	public Banker(int nUnits){
		resources = nUnits;
	}
	
	public void setClaim(int nUnits){
		//The current  thread (available via static method Thread. currentThread()) attempts 
		//to register a claim for up to nUnits units of resource.
		
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
