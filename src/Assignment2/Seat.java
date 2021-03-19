package Assignment2;

/**
 * this class creates the seat objects with relevant
 * fields.
 * @author Liam Yates - 18016696
 */

public class Seat {

	private boolean firstClass;
	private boolean reserved;
	private SeatType seatType;
	private SeatPosition seatPosition;
	
	public Seat(SeatPosition seatposition, SeatType seattype, boolean firstClass)
	{
		this.seatPosition = seatposition;
		this.seatType = seattype;
		this.firstClass = firstClass;
	}
	
	public boolean isReserved() 
	{
		return reserved;
	}

	public void setReserved(boolean reserved) 
	{
		this.reserved = reserved;
	}

	public boolean isFirstClass() 
	{
		return firstClass;
	}

	public SeatType getSeatType() 
	{
		return seatType;
	}

	public SeatPosition getSeatPosition() 
	{
		return seatPosition;
	}
	
	public String getSeatDescription()
	{
		return "The " + (firstClass ? "first" : "economy")  + " class " + seatType + " seat at " + seatPosition + " is " + (reserved ? "reserved." : "not reserved." );
	}
	
	/**
	 * This toString is what creates the actual String plane layout array
	 * which displays the seats and isles.
	 * @return a plane seating layout
	 */	
	public String toString()
	{
		String L_bracket = "[";
		String s_type = seatType.toString().substring(0, 1);
		char temp = s_type.charAt(0);
		if (firstClass == true) 
		{
			L_bracket += Character.toUpperCase(temp);
		}
		else 
		{
			L_bracket += Character.toUpperCase(temp);
		}
		if (!reserved)
		{
			L_bracket += " _ ]";
		}
		else
		{
			L_bracket += " X ]";
		}
		return L_bracket;
	}
}
