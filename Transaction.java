
public class Transaction 
{
	public void Reserve(Flight f, int id)
	{
		if(id>0)
		{
			CCM.Lock(this);
			if(f.seatsAvail>0)
			{
				//thread.sleep(50);
				f.seatsAvail-=1;
				f.seatsReserved+=1;
				if(f.seats.size()<f.totalSeats)
				{
					f.seats.add(id);
					//check for duplicate id too
				}

				if (Flight_Database.if_ID_Exists(id)!=null)
				{
					Passenger p = Flight_Database.if_ID_Exists(id);
					p.flights.add(f);
				}
				else
				{
					Passenger p = new Passenger(id);
					p.flights.add(f);
					Flight_Database.PassList.add(p);
				}
			}
			else
			{
				System.out.println("Sorry! No seats available in flight " + f.name);
			}

		}
		else
		{
			System.out.println("Error! Wrong Passenger ID");
	
		}

		CCM.Unlock(this);
	}
	public void Cancel(Flight f, int i)
	{
		if(i>0)
		{
			CCM.Lock(this);
			//thread.sleep(50);
			if((Flight_Database.if_Flight_Exists(f)==true)&&(Flight_Database.if_ID_Exists(i)!=null))
			{
				Passenger p1 = Flight_Database.if_ID_Exists(i);
				if(p1.passenger_has_flight(f))
				{
					f.seatsAvail += 1;
					f.seatsReserved -=1;
					f.seats.remove(i);
					p1.flights.remove(f);

				}
			}
		}

		else
		{
			System.out.println("Error! Wrong Passenger ID");
			
		}

		CCM.Unlock(this);
	}
	public void My_Flights(int id)
	{
		if(id>0)
		{
				CCM.Lock(this);
				if (Flight_Database.if_ID_Exists(id)!=null)
				{
					Passenger p = Flight_Database.if_ID_Exists(id);
					System.out.println("Passenger ID: " + p.id);
					for(int i = 0; i < p.flights.size(); i++)
					{
						System.out.println("Flight " + (i+1) + ": " + p.flights.get(i).name);
					}
				}
				else
				{
					System.out.println("Sorry! Passenger ID does not exist!");
				}
				CCM.Unlock(this);
		}
		else
		{
			System.out.println("Sorry! Passenger ID does not exist!");
			
		}
	}
	public int Total_Reservations()
	{
		int sum = 0;
		for(int i = 0; i < Flight_Database.FlightList.size(); i++)
		{
			sum += Flight_Database.FlightList.get(i).seatsReserved;
		}
		
		System.out.println("Total Number of Reservations on all flights = "+sum);
		return sum;
	}
	
	public void Transfer(Flight F1, Flight F2, int i)
	{
		if(Flight_Database.if_Flight_Exists(F1)&&(Flight_Database.if_Flight_Exists(F2)))
		{
			if(Flight_Database.if_ID_Exists(i)!=null)
			{
				Passenger p = Flight_Database.if_ID_Exists(i);
				if(p.passenger_has_flight(F1))
				{
					this.Cancel(F1, i);
					this.Reserve(F2, i);
				}
				else
				{
					System.out.println("Passenger does not have a seat in flight " + F1.name);
				}
			}
			else
			{
				System.out.println("Passenger ID does not exist");
			}
		}
		else
		{
			System.out.println("Either F1 or F2 does not exist");
		}
	}
}
