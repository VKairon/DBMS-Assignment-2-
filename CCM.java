import java.util.LinkedList;
import java.util.Queue;

public class CCM 
{
	static Queue<Transaction> q = new LinkedList<Transaction>();
	static boolean DB_locked= false;
	public static boolean Lock(Flight F, Transaction T)
	{
		if (DB_locked==false)
		{
			DB_locked=true;
			return true;
		}
		else
		{
			q.add(T);
			return false;
		}
		
	}
	public static void Unlock(Flight F, Transaction T)
	{
		if (q.isEmpty()==false)
		{
			Transaction T2= q.remove();
			//thread.notify
		}
	}
}
