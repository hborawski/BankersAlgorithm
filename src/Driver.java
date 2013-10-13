
public class Driver {
	
	public static void main(String [] args){
		Banker banker = new Banker(10);
		
		Client a = new Client("Alabama", banker, 5, 0, 0, 0);
		Client c = new Client("Colorado", banker, 5, 0, 0, 0);
		Client d = new Client("Deleware", banker, 5, 0, 0, 0);
		Client f = new Client("Florida", banker, 5, 0, 0, 0);
		Client g = new Client("Georgia", banker, 5, 0, 0, 0);
		
		a.start();
		c.start();
		d.start();
		f.start();
		g.start();
	}

}
