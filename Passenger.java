import java.util.ArrayList;

public class Passenger 
{
	int id;
	ArrayList<Flight> flights = new ArrayList<Flight>();
	
	public Passenger(int id)
	{
		this.id = id;
	}
	public boolean passenger_has_flight(Flight f)
	{
		if (this.flights.contains(f))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}