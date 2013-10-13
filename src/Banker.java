import java.util.ArrayList;


public class Banker {
	
	private int totalResources;
	private int avalResources;
	private ArrayList<String> registeredNames;
	private ArrayList<int[]> registeredClaims;
	
	public Banker(int nUnits){
		totalResources = nUnits;
		avalResources = nUnits;
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
		registeredClaims.add(temp);
		System.out.println("Thread " + Thread.currentThread().getName() + " sets a claim for " + nUnits);
		
	}
	
	public synchronized boolean request(int nUnits){
		//The current thread requests nUnits more resources.
		int threadNum = -1;
		
		for(int i=0;i<registeredNames.size();i++){
			if(registeredNames.get(i).equals(Thread.currentThread().getName()))
				threadNum = i;
		}
		
		if((threadNum == -1) || (nUnits < 0))
			System.exit(1);
		
		if(registeredClaims.get(threadNum)[1] - registeredClaims.get(threadNum)[0] < nUnits)
			System.exit(1);
		
		System.out.println("Thread " + Thread.currentThread().getName() + " requests " + nUnits + " units");
	
		while(true){
			if(avalResources >= nUnits && safe(nUnits,threadNum)){
				avalResources-= nUnits;
				registeredClaims.get(threadNum)[0] += nUnits;
				System.out.println("Thread " + Thread.currentThread().getName() + " has " + nUnits + " allocated");
				return true;
			}
			System.out.println("Thread " + Thread.currentThread().getName() + " waits");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread " + Thread.currentThread().getName() + " awakened");
		}
	}
	
	private boolean safe(int nUnits,int threadNum) {
		if(registeredClaims.get(threadNum)[1]-registeredClaims.get(threadNum)[0] == nUnits)
			return true;
		
		int tempAval = avalResources - nUnits;
		for(int i=0;i<registeredClaims.size();i++){
			if(registeredClaims.get(i)[1]-registeredClaims.get(i)[0] == 0)
				tempAval += registeredClaims.get(i)[1];
		}
		
		if(registeredClaims.get(threadNum)[1]-registeredClaims.get(threadNum)[0] <= tempAval+nUnits)
			return true;
		
		boolean cantComplete = false;
		
		for(int i=0;i<registeredClaims.size();i++){
			if(registeredClaims.get(i)[1]-registeredClaims.get(i)[0] > tempAval)
				if(cantComplete == true)
					return false;
				else
					cantComplete = true;
		}
		return true;
	}

	public synchronized void release(int nUnits){
		//The current thread releases nUnits resources.
		int threadNum = -1;
		
		for(int i=0;i<registeredNames.size();i++){
			if(registeredNames.get(i).equals(Thread.currentThread().getName()))
				threadNum = i;
		}
		
		if((threadNum == -1) || (nUnits < 0))
			System.exit(1);
		
		if(registeredClaims.get(threadNum)[0] < nUnits)
			System.exit(1);
		
		registeredClaims.get(threadNum)[0] -= nUnits;
		avalResources += nUnits;
		System.out.println("Thread " + Thread.currentThread().getName() + " releases " + nUnits + " units");
		notifyAll();
		
	}
	
	public int allocated(){
		for(int i=0;i<registeredNames.size();i++){
			if(registeredNames.get(i).equals(Thread.currentThread().getName()))
				return registeredClaims.get(i)[0];
		}
		return 0;
	}
	
	public int remaining(){
		//Returns the number of units remaining in the current thread's claim.
		for(int i=0;i<registeredNames.size();i++){
			if(registeredNames.get(i).equals(Thread.currentThread().getName()))
				return registeredClaims.get(i)[1] - registeredClaims.get(i)[0];
		}
		return-1;
	}
}
