
public class Transaction 
{
	public void reserve(Flight f, int id)
	{
		if(id>0)
		{
			CCM.Lock(f, this);
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

		}
		else
		{
			System.out.println("Error! Wrong Passenger ID");
			System.exit(0);
		}

		CCM.Unlock(f, this);
	}
	public void Cancel(Flight f, int i)
	{
		if(i>0)
		{
			CCM.Lock(f, this);
			//thread.sleep(50);
			if((Flight_Database.if_Flight_Exists(f)==true)&&(Flight_Database.if_ID_Exists(i)!=null))
			{
				Passenger p1 = Flight_Database.if_ID_Exists(i);
				if(p1.passenger_has_flight(f))
				{
					f.seatsAvail += 1;
					f.seatsReserved -=1;
					if(f.seats.size()>0)
					{
						f.seats.remove(i);
					}

					p1.flights.remove(f);

				}
			}
		}

		else
		{
			System.out.println("Error! Wrong Passenger ID");
			System.exit(0);
		}

		CCM.Unlock(f, this);
	}
}
