public class TestThread {

   public static void main(String args[]) throws InterruptedException 
   {
	  
	  TransactionThread R1 = new TransactionThread( "Thread-1");
      R1.start();
      R1.active=false;
//      synchronized(R1)
//      {
//    	  R1.wait();
//          System.out.println("R1 is waiting");
//      }
      TransactionThread R2 = new TransactionThread( "Thread-2");
      R2.start();
      
      TransactionThread R3 = new TransactionThread( "Thread-3");
      R3.start();
//      synchronized(R1)
//      {
//    	  Thread.sleep(10000);
//    	  R1.notify();
//          System.out.println("R1 is resumed");
//    	  
//      }      

   }

private static void synchronised(TestThread obj) {
	// TODO Auto-generated method stub
	
}   
}