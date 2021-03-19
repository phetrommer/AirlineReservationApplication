package Assignment2;

/**
 * this class is used to create the airline objects.
 * @author Liam Yates - 18016696
 */

public abstract class Airline {

	private String airlineName;
	
	abstract public Seat reserveFirstClass(Flight aFlight, SeatType aType);
	abstract public Seat reserveEconomy(Flight aFlight, SeatType aType);

	public Airline(String airlineName)
	{
		this.airlineName = airlineName;
	}
	
	public String getAirlineName() 
	{
		return airlineName;
	}
	
	public void setAirlineName(String airlineName) 
	{
		this.airlineName = airlineName;
	}
	
	public String toString()
	{
		return null;
	}
}
