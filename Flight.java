import java.util.ArrayList;

public class Flight 
{
	boolean isLocked = false;
	String name;
	int seatsAvail;
	int seatsReserved;
	int totalSeats;
	ArrayList<Integer> seats = new ArrayList<Integer>(totalSeats);
	
	public Flight(String name,int ts)
	{
		this.name = name;
		totalSeats = ts;
		seatsAvail = ts;
		seatsReserved = 0;
//		for(int i = 0; i<totalSeats; i++)
//		{
//			seats.set(i, -1);
//		}
	}

}
