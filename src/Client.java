import java.util.Random;


public class Client extends Thread{
	Banker banker;
	int nUnits;
	int nRequests;
	long minSleepMillis;
	long maxSleepMillis;
	Random generator;
	
	public Client(String name, Banker banker, int nUnits, int nRequests, long minSleepMillis, long maxSleepMillis){
		super(name);
		this.banker = banker;
		this.nUnits = nUnits;
		this.nRequests = nRequests;
		this.minSleepMillis = minSleepMillis;
		this.maxSleepMillis = maxSleepMillis;
		generator = new Random();
	}
	
	public void run(){
		banker.setClaim(nUnits);
		
		for(int i=0;i<nRequests;i++){
			if(banker.remaining() == 0){
				banker.release(nUnits);
			} else {
				int request = 0;
				while(request == 0)
					request = generator.nextInt(banker.remaining()+1);
				banker.request(request);
			}
			
			sleepCatch(minSleepMillis + (long)(Math.random() * ((maxSleepMillis - minSleepMillis) + 1)));
		}
		
		banker.release(banker.allocated());
		
		return;
	}
	
	/**
	 * Method to encapsulate Thread.sleep to catch interrupted exceptions
	 * 
	 * @param millis how long to sleep for
	 */
	public void sleepCatch(long millis){
		try{
			Thread.sleep(millis);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
