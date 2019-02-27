import java.util.ArrayList;

public class Flight_Database 
{
	static ArrayList<Flight> FlightList; 
	static ArrayList<Passenger> PassList = new ArrayList<Passenger>(50);

	public static void initializeDB()
	{

		FlightList =  new ArrayList<Flight>();
		Flight F1 = new Flight("Air Asia", 365);
		Flight F2 = new Flight("Vistara Airlines",210);
		Flight F3 = new Flight("Emirates",340);
		Flight F4 = new Flight("Lufthansa",298);
		Flight F5 = new Flight("Indigo",225);

		FlightList.add(F1);
//		System.out.println("Seats = " + FlightList.get(0).seatsAvail);
		FlightList.add(F2);
		FlightList.add(F3);
		FlightList.add(F4);
		FlightList.add(F5);

		for(int i = 0; i < 1000; i++)
		{
			Passenger P1 = new Passenger(i+1);
			PassList.add(P1);
		}
		
	}
	public static Passenger if_ID_Exists(int id)
	{

		for(int i = 0; i < PassList.size(); i++)
		{
			if(id == PassList.get(i).id)
			{
//				System.out.println("ID "+ id+ " Pass "+ PassList.get(i).id);
				return PassList.get(i);
			}
		}
		return null;
	}
	public static int if_ID_Exists_getid(int id)
	{

		for(int i = 0; i < PassList.size(); i++)
		{
			if(id == PassList.get(i).id)
			{
				return i;
			}
		}
		return -1;
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
	public static boolean passenger_has_flight(Flight f, int id)
	{
		for(int i=0;i<f.seats.size();i++)
		{
			if(f.seats.get(i) == id)
				return true;
		}

		return false;
	}
}
