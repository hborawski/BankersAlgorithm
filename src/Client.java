
public class Client extends Thread{
	Banker banker;
	int nUnits;
	int nRequests;
	long minSleepMillis;
	long maxSleepMillis;
	
	public Client(String name, Banker banker, int nUnits, int nRequests, long minSleepMillis, long maxSleepMillis){
		super(name);
		this.banker = banker;
		this.nUnits = nUnits;
		this.nRequests = nRequests;
		this.minSleepMillis = minSleepMillis;
		this.maxSleepMillis = maxSleepMillis;
	}
	
	public void run(){
		banker.setClaim(nUnits);
	}
}
