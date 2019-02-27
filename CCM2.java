import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
public class CCM2
{

	static Map<Flight,ArrayList<TransactionStatus>> flightMap = new HashMap<>();

	static Map<Passenger,ArrayList<TransactionStatus>> passMap = new HashMap<>();

	static boolean flight_locked= false;
	static boolean pass_locked= false;


	public static void initializeLockTable()
	{
		for(int i  = 0; i < flightMap.size(); i++)
		{
			ArrayList<TransactionStatus> a1 = new ArrayList<TransactionStatus>();
			flightMap.put(Flight_Database.FlightList.get(i), a1);
		}

		for(int i  = 0; i < passMap.size(); i++)
		{
			ArrayList<TransactionStatus> a2 = new ArrayList<TransactionStatus>();
			passMap.put(Flight_Database.PassList.get(i), a2);
		}

	}
	public static synchronized boolean Lock_Flight(Flight F, Transaction2 T, char lockType)
	{
		if(flightMap.containsKey(F))
		{
		if ((flightMap.get(F).isEmpty()))
		{

			System.out.println("Locking transaction - " + T.transNum +" and flight " + F.name);
			F.isLocked=true;
			return true; //lock granted
		}
		else
		{
			switch(lockType)
			{
			case 'S': 
				if (flightMap.get(F).get(0).transNum==T.transNum)
				{
					F.isLocked=true;
					return true; //lock granted
				}
				else
				{
					if (flightMap.get(F).get(0).lockType=='S')
						{
							F.isLocked=true;
							return true; //lock granted
						}
					else
					{
						//add(F) to hashmap;
						flightMap.get(F).add(new TransactionStatus(T.transNum,lockType));
						System.out.println("Transaction added = " + T.transNum + " and flight " + F.name);
						return false;
					}
				}
			case 'X':
				if (flightMap.get(F).get(0).transNum==T.transNum)
				{
					F.isLocked=true;
					return true; //lock granted
				}
				else
				{
					//add(F) to hashmap;
					flightMap.get(F).add(new TransactionStatus(T.transNum,lockType));
					System.out.println("Transaction added = " + T.transNum + " and flight " + F.name);
					return false;
				}
			}
		}
		}
		return false;
	}
	public static synchronized boolean Lock_Pass(Passenger P, Transaction2 T, char lockType)
	{
		if(passMap.containsKey(P)) {
		if ((passMap.get(P).isEmpty()))
		{
			System.out.println("Locking transaction - " + T.transNum +" and passenger no. " + P.id);
			P.isLocked=true;
			return true;
		}
		else
		{
			//add(P) to hashmap;
			passMap.get(P).add(new TransactionStatus(T.transNum,lockType));
			System.out.println("Transaction added = " + T.transNum + " and passenger no. " + P.id);
			return false;
		}
		}
		else
			return false;

	}
	public static void Unlock_Flight(Flight F,Transaction2 T)
	{
		if(flightMap.containsKey(F))
		{
		if (flightMap.get(F).isEmpty())//if flight object F in hashmap does not have any mapped values
		{
			System.out.println("Unlocked Flight F = " + F.name);
			F.isLocked = false;
		}
		else
		{
			//remove Transaction T from arraylist of F in hashmap
			flightMap.get(F).remove(0);
			System.out.println("Removing transaction " + T.transNum +" from flight " +F.name + " in hashmap");
		}
		}
		
	}

	public static void Unlock_Pass(Passenger P,Transaction2 T)
	{
		if(passMap.containsKey(P)) {
		if (passMap.get(P).isEmpty()) //if passenger object P is unlocked based on hashmap
		{
			System.out.println("Unlocked Passenger P = " +P.id);
			P.isLocked = false;

		}
		else
		{
			//remove Passenger object P from hashmap
			passMap.get(P).remove(0);
			System.out.println("Removing transaction " + T.transNum +" from passenger no. " + P.id +" in hashmap");
		}
		}
		
		
	}
}
