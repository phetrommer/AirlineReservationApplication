package Assignment2;

/**
 * this class is used to create the Flight objects
 * which stores the flights relevant information
 * @author Liam Yates - 18016696
 */

public class Flight {

	private String flightIdentifier;
	private String originCity;
	private String destCity;
	private String departureTime;
	private SeatMap seating;
	private String seatmapType;
	
	public Flight(String flightIdentifier, String originCity, String destCity, String departureTime, String seatmapType, SeatMap seating) 
	{
		this.flightIdentifier = flightIdentifier;
		this.originCity = originCity;
		this.destCity = destCity;
		this.departureTime = departureTime;
		this.seatmapType = seatmapType;
		this.seating = seating;
	}

	public String getseatmapType()
	{
		return seatmapType;
	}
	
	public String getOriginCity() 
	{
		return originCity;
	}

	public void setOriginCity(String originCity) 
	{
		this.originCity = originCity;
	}

	public String getDestCity() 
	{
		return destCity;
	}

	public void setDestCity(String destCity) 
	{
		this.destCity = destCity;
	}

	public String getDepartureTime() 
	{
		return departureTime;
	}

	public void setDepartureTime(String departureTime) 
	{
		this.departureTime = departureTime;
	}

	public SeatMap getSeating() 
	{
		return seating;
	}

	public void setSeating(SeatMap seating) 
	{
		this.seating = seating;
	}

	public String getFlightIdentifier() 
	{
		return flightIdentifier;
	}
	
	public String toString()
	{
		return "Flight: " + flightIdentifier + " from: " + originCity + " to: " + destCity + " departing: " + departureTime;
	}
}
