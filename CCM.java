import java.util.LinkedList;
import java.util.Queue;

public class CCM 
{
	static Queue<Transaction> q = new LinkedList<Transaction>();
	static boolean DB_locked= false;
	
	public static synchronized boolean Lock(Transaction T)
	{
		if (DB_locked==false)
		{
			System.out.println("Locking transaction - " + T.transNum);
			DB_locked=true;
			return true;
		}
		else
		{
			q.add(T);
			System.out.println("Transaction added to queue = " + T.transNum);
			return false;
		}
		
	}
	public static void Unlock(Transaction T)
	{
		if (q.isEmpty())
		{
			System.out.println("Unlocked database");
			DB_locked = false;
			
		}
		else
		{
			Transaction T2 = q.remove();
			System.out.println("Removing transaction from queue - " + T2.transNum);

			//thread.notify
		}
	}
}
