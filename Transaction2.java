import java.util.ArrayList;
import java.util.Map;
public class Transaction2
{
	int transNum = 0;

	public void Reserve(Flight f, int id) throws InterruptedException
	{
		transNum = 1;
		Passenger p = Flight_Database.if_ID_Exists(id);
		if((id>0)&& (p!=null))
		{
			while((CCM2.Lock_Pass(p,this,'S') == true ||(CCM2.Lock_Pass(p,this,'X') == true)) && ((CCM2.Lock_Flight(f, this,'S') == true) ||(CCM2.Lock_Flight(f, this,'X') == true)))  
			{

			}
			CCM2.Lock_Flight(f, this, 'S');
			CCM2.Lock_Pass(p,this,'S');
			if(f.seatsAvail>0)
			{
				Thread.sleep(50); //check later for correctness
				//				System.out.println("the id no = "+ id);
				//				System.out.println(f.seats.size()+f.totalSeats);
				if(f.seats.size()<f.totalSeats)
				{
					//check for duplicate id too
					if(f.seats.contains(id))
					{
						System.out.println("Duplicate ID!");
					}
					else
					{
						CCM2.Lock_Flight(f, this, 'X');
						f.seatsAvail-=1;
						f.seatsReserved+=1;
						f.seats.add(id);
						CCM2.Lock_Pass(p,this,'X');
						if (Flight_Database.if_ID_Exists(id)!=null)
						{

							p.flights.add(f);
						}
						else
						{
							Passenger p2 = new Passenger(id);
							p2.flights.add(f);
							Flight_Database.PassList.add(p2);
						}
					}

				}
			}
			else
			{
				System.out.println("Sorry! No seats available in flight " + f.name);
			}
			CCM2.Unlock_Pass(p,this);
			CCM2.Unlock_Flight(f,this);

		}
		else
		{
			System.out.println("Error! Wrong Passenger ID");

		}



	}
	public void Cancel(Flight f, int i)
	{
		transNum = 2;
		Passenger p1 = Flight_Database.if_ID_Exists(i);
		if((i>0)&& (p1!=null))
		{

			while((CCM2.Lock_Pass(p1,this,'S') == true ||(CCM2.Lock_Pass(p1,this,'X') == true)) && ((CCM2.Lock_Flight(f, this,'S') == true) ||(CCM2.Lock_Flight(f, this,'X') == true)))
			{

			}
			CCM2.Lock_Flight(f, this,'S');
			//			Thread.sleep(50);
			if((Flight_Database.if_Flight_Exists(f)==true)&&(Flight_Database.if_ID_Exists(i)!=null))
			{		
				CCM2.Lock_Pass(p1, this,'S');
				if(p1.passenger_has_flight(f))
				{
					CCM2.Lock_Flight(f, this,'X');
					f.seatsAvail += 1;
					f.seatsReserved -=1;
					for(int j = 0; j < f.seats.size(); j++)
					{
						if(f.seats.get(j) == i)
						{
							f.seats.remove(j);
						}
					}
					CCM2.Lock_Pass(p1, this,'X');
					p1.flights.remove(f);
				}
			}
			CCM2.Unlock_Pass(p1,this);
			CCM2.Unlock_Flight(f,this);
		}
		else
		{
			System.out.println("Error! Wrong Passenger ID");

		}

	}
	public void My_Flights(int id)
	{
		transNum = 3;
		Passenger P = Flight_Database.if_ID_Exists(id);
		if((id>0)&& (P!=null))
		{
			while((CCM2.Lock_Pass(P,this,'S') == true) ||(CCM2.Lock_Pass(P,this,'X') == true))
			{

			}
			if (P!=null)
			{
				//				Passenger p = Flight_Database.if_ID_Exists(id);
				CCM2.Lock_Pass(P,this,'S');
				System.out.println("Passenger ID: " + id);
				for(int i = 0; i < P.flights.size(); i++)
				{
					System.out.println("Flight " + (i+1) + ": " + P.flights.get(i).name);
				}
			}
			CCM2.Unlock_Pass(P,this);
		}
		else
		{
			System.out.println("Sorry! Passenger ID does not exist!");

		}
	}
	public int Total_Reservations()
	{
		transNum = 4;
		int sum = 0;
		for (Map.Entry<Flight,ArrayList<TransactionStatus>> entry : CCM2.flightMap.entrySet()) 
		{
			Flight f = entry.getKey();
			while((CCM2.Lock_Flight(f, this,'S') == true) ||(CCM2.Lock_Flight(f, this,'X') == true))
			{

			}
			CCM2.Lock_Flight(f, this,'S');
		} 
		for(int i = 0; i < Flight_Database.FlightList.size(); i++)
		{
			sum += Flight_Database.FlightList.get(i).seatsReserved;
		}
		System.out.println("Total Number of Reservations on all flights = "+sum);
		for (Map.Entry<Flight,ArrayList<TransactionStatus>> entry : CCM2.flightMap.entrySet()) 
		{
			Flight f = entry.getKey();
			CCM2.Unlock_Flight(f,this);
		}
		return sum;
	}

	public void Transfer(Flight F1, Flight F2, int i) throws InterruptedException
	{
		transNum = 5;
		Passenger P = Flight_Database.if_ID_Exists(i);

		if((i>0)&& (P!=null))
		{
			while((CCM2.Lock_Pass(P,this,'S') == true ||(CCM2.Lock_Pass(P,this,'X') == true)) && ((CCM2.Lock_Flight(F1, this,'S') == true) ||(CCM2.Lock_Flight(F1, this,'X') == true))&&((CCM2.Lock_Flight(F2, this,'S') == true) ||(CCM2.Lock_Flight(F2, this,'X') == true)))
			{

			}
			CCM2.Lock_Flight(F1, this,'S');
			CCM2.Lock_Flight(F2, this,'S');

			if(Flight_Database.if_Flight_Exists(F1)&&(Flight_Database.if_Flight_Exists(F2)))
			{
				if(Flight_Database.if_ID_Exists(i)!=null)
				{
					CCM2.Lock_Pass(P, this,'S');
					if(P.passenger_has_flight(F1))
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
			CCM2.Unlock_Pass(P,this);
			CCM2.Unlock_Flight(F1,this);
			CCM2.Unlock_Flight(F2,this);
		}
		else
		{
			System.out.println("Passenger ID does not exist");
		}
	}


}
