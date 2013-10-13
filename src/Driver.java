import java.util.Random;


public class Driver {
	
	public static void main(String [] args){
		
		int numThreads = 10;
		int bankersResources = 50;
		int standardResources = 10;
		long sleepTimeMin = 10;
		long sleepTimeMax = 20;
		int numRequests = 10;
		
		Client[] clients = new Client[numThreads];
		
		Banker banker = new Banker(bankersResources);

		for(int i=0;i<numThreads;i++){
				clients[i] = new Client("thread " + i, banker, standardResources, numRequests, sleepTimeMin, sleepTimeMax);
		}
		
		for(int i=0;i<numThreads;i++){
			clients[i].start();
		}
		
	}

}
