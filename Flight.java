
public class Flight 
{
	String name;
	int seatsAvail;
	int seatsReserved;
	int totalSeats;
	int[] seats = new int[totalSeats];
	
	public Flight(int ts)
	{
		totalSeats = ts;
		for(int i = 0; i<totalSeats; i++)
		{
			seats[i] = -1;
		}
	}

}
