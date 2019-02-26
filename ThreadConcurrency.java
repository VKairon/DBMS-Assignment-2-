import java.util.Random;

class ThreadConcurrency implements Runnable {
	private Thread t;
	private String threadName;
	Transaction trans;
	static Random rand = new Random();

	ThreadConcurrency(String name) throws InterruptedException 
	{
		threadName = name;
		System.out.println("Creating " +  threadName);
		trans = new Transaction();
		Flight_Database.initializeDB();

	}

	public void run() {
		//		System.out.println("Running " +  threadName);
		try {
			System.out.println("Running " +  threadName);
//			while(CCM.DB_locked == false)
//			{
//				System.out.println(threadName + " unlocked");
				int random = rand.nextInt((5 - 1) + 1) + 1;
//				System.out.println("For " +  threadName);
				System.out.println("For " +  threadName+" Transaction Number - " + random);
				switch(random)
				{
				case 1:
					Flight f = Flight_Database.FlightList.get(1);
					int id = rand.nextInt((10 - 1) + 1) + 1;
					System.out.println("Transaction 1, id = " + id);
					trans.Reserve(f, id);
					break;
				case 2: 
					f = Flight_Database.FlightList.get(2);
					id = rand.nextInt((10 - 1) + 1) + 1;
					System.out.println("Transaction 2, id = " + id);
					trans.Cancel(f, id);
					break;
				case 3: 
					f = Flight_Database.FlightList.get(3);
					id = rand.nextInt((10 - 1) + 1) + 1;
					System.out.println("Transaction 3, id = " + id);
					trans.My_Flights(id);
					break;
				case 4: 
					trans.Total_Reservations();
					break;
				case 5: 
//					int r1 = rand.nextInt((5 - 1) + 1) + 1;
//					int r2 = rand.nextInt((5 - 1) + 1) + 1;
//					System.out.println("Flight 1 = " + r1);
//					System.out.println("Flight 2 = " + r2);
					Flight f1 = Flight_Database.FlightList.get(0);
					System.out.println("Flight 1 = " + f1.name);
					Flight f2 = Flight_Database.FlightList.get(1);
//					id = rand.nextInt((10 - 1) + 1) + 1;
					id = 1;
					System.out.println("Transaction 5, id = " + id);
					trans.Transfer(f1, f2, id);
					break;
				default: 
					break;

				}
//			}
		} catch (InterruptedException e) {
			System.out.println("Thread " +  threadName + " interrupted.");
		}
		System.out.println(threadName + " exiting.");
	}

	public void start() 
	{
//		System.out.println("Starting " +  threadName );
			if (t == null) {
				t = new Thread (this, threadName);
				t.start ();
			}
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		ThreadConcurrency t1 = new ThreadConcurrency("Thread 1");
		ThreadConcurrency t2 = new ThreadConcurrency("Thread 2");
		t1.start();
		t2.start();
	}
}

