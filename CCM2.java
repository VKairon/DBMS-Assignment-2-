import java.util.HashMap;
import java.util.LinkedList;

public class CCM2
{
	HashMap<Integer,Flight> flightMap = new HashMap<>();
	HashMap<Integer,Passenger> passMap = new HashMap<>();
	static boolean flight_locked= false;
	static boolean pass_locked= false;
	
	public static synchronized boolean Lock_Flight(Flight F, Transaction T)
	{
		if (flight_locked==false)
		{
			System.out.println("Locking transaction - " + T.transNum +" and flight " + F.name);
			flight_locked=true;
			return true;
		}
		else
		{
			//add(F) to hashmap;
			map.put(T.transNum,F)
			System.out.println("Transaction added = " + T.transNum + " and flight " + F.name);
			return false;
		}
		
	}
	
	public static synchronized boolean Lock_Pass(Passenger P, Transaction T)
	{
		if (pass_locked==false)
		{
			System.out.println("Locking transaction - " + T.transNum +" and passenger no. " + P.id);
			pass_locked=true;
			return true;
		}
		else
		{
			//add(P) to hashmap;
			System.out.println("Transaction added = " + T.transNum + " and passenger no. " + P.id);
			return false;
		}
		
	}
	public static void Unlock_Flight(Flight F,Transaction T)
	{
		if () //if flight object F is unlocked based on hashmap
		{
			System.out.println("Unlocked Flight F = " F.name);
			flight_locked = false;
			
		}
		else
		{
			//remove Flight object F from hashmap
			System.out.println("Removing flight " + F.name +" from hashmap");
		}
	}
	
	public static void Unlock_Pass(Passenger P,Transaction T)
	{
		if () //if passenger object P is unlocked based on hashmap
		{
			System.out.println("Unlocked Passenger P = " +P.id);
			pass_locked = false;
			
		}
		else
		{
			//remove Passenger object P from hashmap
			System.out.println("Removing passenger no. " + P.id +" from hashmap");
		}
	}
}
