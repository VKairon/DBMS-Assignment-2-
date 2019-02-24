import java.util.Random;

class ThreadConcurrency implements Runnable {
   private Thread t;
   private String threadName;
   Transaction trans;
   
   ThreadConcurrency(String name) throws InterruptedException 
   {
      threadName = name;
      System.out.println("Creating " +  threadName);
      
      Random rand = new Random();
      int random = rand.nextInt((5 - 1) + 1) + 1;
//      System.out.println(random);
      switch(random)
      {
      case 1:
    	  Flight f = Flight_Database.FlightList.get(1);
    	  int id = rand.nextInt((50 - 1) + 1) + 1;
    	  trans.Reserve(f, id);
    	  break;
      case 2: 
    	  f = Flight_Database.FlightList.get(2);
    	  id = rand.nextInt((50 - 1) + 1) + 1;
    	  trans.Cancel(f, id);
    	  break;
      case 3: 
    	  f = Flight_Database.FlightList.get(3);
    	  id = rand.nextInt((50 - 1) + 1) + 1;
    	  trans.My_Flights(id);
    	  break;
      case 4: 
    	  trans.Total_Reservations();
    	  break;
      case 5: 
    	  int r1 = rand.nextInt((5 - 1) + 1) + 1;
    	  int r2 = rand.nextInt((5 - 1) + 1) + 1;
    	  Flight f1 = Flight_Database.FlightList.get(r1);
    	  Flight f2 = Flight_Database.FlightList.get(r2);
    	  id = rand.nextInt((50 - 1) + 1) + 1;
    	  trans.Transfer(f1, f2, id);
    	  break;
      default: 
    	  break;
    
      }
   }
   
   public void run() {
      System.out.println("Running " +  threadName);
      try {
         for(int i = 4; i > 0; i--) {
            System.out.println("Thread: " + threadName + ", " + i);
            // Let the thread sleep for a while.
            Thread.sleep(50);
         }
      } catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
      }
      System.out.println("Thread " +  threadName + " exiting.");
   }
   
   public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
}

