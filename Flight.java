import java.util.ArrayList;

public class Flight 
{
	String name;
	int seatsAvail;
	int seatsReserved;
	int totalSeats;
	ArrayList<Integer> seats = new ArrayList<Integer>(totalSeats);
	
	public Flight(String name,int ts)
	{
		totalSeats = ts;
		seatsAvail = ts;
		seatsReserved = 0;
//		for(int i = 0; i<totalSeats; i++)
//		{
//			seats.set(i, -1);
//		}
	}

}
