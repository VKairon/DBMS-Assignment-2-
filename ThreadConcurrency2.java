import java.util.Random;

class ThreadConcurrency2 implements Runnable {
	private Thread t;
	private String threadName;
	Transaction2 trans;
	static Random rand = new Random();

	ThreadConcurrency2(String name) throws InterruptedException 
	{
		threadName = name;
		System.out.println("Creating " +  threadName);
		trans = new Transaction2();
		Flight_Database.initializeDB();

	}

	public void run() {
		//		System.out.println("Running " +  threadName);
		try {
//			System.out.println("Running " +  threadName);
//			while(CCM2.DB_locked == false)
//			{
//				System.out.println("Database unlocked for " + threadName );
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
					int r1 = rand.nextInt((5 - 1) + 1) + 1;
					int r2 = rand.nextInt((5 - 1) + 1) + 1;
					//check that r1!=r2
					Flight f1 = Flight_Database.FlightList.get(r1-1);
					Flight f2 = Flight_Database.FlightList.get(r2-1);
					System.out.println("Transfers: Flight 1 = " + f1.name);
					System.out.println("Transfers: Flight 2 = " + f2.name);
					
					id = rand.nextInt((10 - 1) + 1) + 1;
//					id = 1;
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
			if (t == null) 
			{
				t = new Thread (this, threadName);
				t.start ();
			}
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		ThreadConcurrency2 t1 = new ThreadConcurrency2("Thread 1");
		ThreadConcurrency2 t2 = new ThreadConcurrency2("Thread 2");
		ThreadConcurrency2 t3 = new ThreadConcurrency2("Thread 3");
		t1.start();
		t2.start();
		t3.start();
	}
}

