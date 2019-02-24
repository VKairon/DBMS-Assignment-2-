import java.util.ArrayList;

public class Flight_Database 
{
	static ArrayList<Flight> FlightList = new ArrayList<Flight>();
	static ArrayList<Passenger> PassList = new ArrayList<Passenger>();
	
	public static void initializeDB()
	{
	Flight F1 = new Flight("Air Asia", 365);
	Flight F2 = new Flight("Vistara Airlines",210);
	Flight F3 = new Flight("Emirates",340);
	
	FlightList.add(F1);
	FlightList.add(F2);
	FlightList.add(F3);
	
//	Passenger P1 = new Passenger();
//	PassList.add(P1);
	}
	public static Passenger if_ID_Exists(int id)
	{
		
		for(int i = 0; i < PassList.size(); i++)
		{
			if(id == PassList.get(i).id)
			{
				return PassList.get(i);
			}
		}
		return null;
	}
	
	public static boolean if_Flight_Exists(Flight f)
	{
		if (FlightList.contains(f))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
