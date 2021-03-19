package Assignment2;

/**
 * The SmartLine class has multiple checks to fit a specific criteria
 * it first checks whether the seats match the requested type then
 * it checks if there is anymore first class seats left because
 * then it reserves a seat in economy
 * 
 * @author Liam Yates - 18016696
 */

public class SmartLine extends Airline {

	Seat s_temp = null;
	Seat s_temp2 = null;
	Seat s_temp3 = null;
	
	public SmartLine(String airlineName) 
	{
		super(airlineName);
	}
	
	/**
	 * this method checks whether there are any available first class
	 * seats.
	 * @param aFlight the picked flight
	 * @param aType the requested seat type
	 * @return a seat object with a seat or null depending on situation
	 */
	
	public Seat firstClassCheck(Flight aFlight, SeatType aType)
	{
		Seat s_temp = null;
		
		for (int c = 0; c <= aFlight.getSeating().getnColumns() - 1; c++)
		{
			for (int r = 0; r <= aFlight.getSeating().getnRows() - 1; r++)
			{
				if (aFlight.getSeating().getSeatsArray()[c][r].equals(aFlight.getSeating().queryAvailableFirstClassSeat(aType)))
				{
					aFlight.getSeating().getSeatsArray()[c][r].setReserved(true);
					s_temp = aFlight.getSeating().getSeatsArray()[c][r];
					return s_temp;
				}
				if (s_temp == null)
				{
					if (aFlight.getSeating().getSeatsArray()[c][r].equals(aFlight.getSeating().queryAvailableFirstClassSeat(SeatType.AISLE)))
					{
						s_temp = aFlight.getSeating().getSeatsArray()[c][r];
					}
					if (aFlight.getSeating().getSeatsArray()[c][r].equals(aFlight.getSeating().queryAvailableFirstClassSeat(SeatType.MIDDLE)))
					{
						s_temp = aFlight.getSeating().getSeatsArray()[c][r];
					}
					if (aFlight.getSeating().getSeatsArray()[c][r].equals(aFlight.getSeating().queryAvailableFirstClassSeat(SeatType.WINDOW))) 
					{
						s_temp = aFlight.getSeating().getSeatsArray()[c][r];
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * this method checks if there are any free economy seats
	 * to book if there are no first classes available.
	 * it books a middle seat in economy and both left and right
	 * seats to it.
	 * @param aFlight the picked flight
	 * @param aType the requested seat type
	 * @return a seat object with a seat or null depending on situation
	 */
	
	public Seat firstClassEcon(Flight aFlight, SeatType aType)
	{
		for (int c = 0; c <= aFlight.getSeating().getnColumns() - 1; c++)
		{
			for (int r = 0; r <= aFlight.getSeating().getnRows() - 1; r++)
			{
				if (aFlight.getSeating().getSeatsArray()[c][r].equals(aFlight.getSeating().queryAvailableEconomySeat(SeatType.MIDDLE))) 
				{
					s_temp = aFlight.getSeating().getSeatsArray()[c][r];
					s_temp2 = aFlight.getSeating().getLeft(aFlight.getSeating().getSeatsArray()[c][r]);
					s_temp3 = aFlight.getSeating().getRight(aFlight.getSeating().getSeatsArray()[c][r]);
					if (s_temp2 != null && !s_temp2.isReserved() && s_temp3 != null && !s_temp3.isReserved()) 
					{
						aFlight.getSeating().getSeatsArray()[s_temp2.getSeatPosition().getColumn() - 'A'][s_temp2.getSeatPosition().getRow() - 1].setReserved(true);
						aFlight.getSeating().getSeatsArray()[s_temp3.getSeatPosition().getColumn() - 'A'][s_temp3.getSeatPosition().getRow() - 1].setReserved(true);
						aFlight.getSeating().getSeatsArray()[c][r].setReserved(true);
						return s_temp;
					}	
				}
			}	
		}
		System.out.println("No more first class seats, try economy!");
		return s_temp;
	}

	/**
	 * this method is called to check and reserve any first class seats
	 * if available. falls back to reserving econ if first class is full.
	 * @param aFlight the picked flight
	 * @param aType the requested seat type
	 * @return a seat object with a seat or null depending on situation
	 */
	
	public Seat reserveFirstClass(Flight aFlight, SeatType aType) 
	{
		s_temp = firstClassCheck(aFlight, aType);
		if (!(s_temp == null)) 
		{
			return s_temp;
		}
		
		// book any first class seat
		if (!(s_temp == null))
		{
			aFlight.getSeating().getSeatsArray()[s_temp.getSeatPosition().getColumn()][s_temp.getSeatPosition().getRow()].setReserved(true);
			return aFlight.getSeating().getSeatsArray()[s_temp.getSeatPosition().getColumn()][s_temp.getSeatPosition().getRow()];
		}
		
		s_temp = firstClassEcon(aFlight, aType);
		return s_temp;
	}

	/**
	 * this method is called to check and reserve any economy seats
	 * if available. 
	 * @param aFlight the picked flight
	 * @param aType the requested seat
	 * @return a seat object with a seat or null depending on situation
	 */
	
	public Seat reserveEconomy(Flight aFlight, SeatType aType) 
	{
		Seat s_temp = aFlight.getSeating().queryAvailableEconomySeat(aType);
		
		if (s_temp == null) 
		{
			s_temp = aFlight.getSeating().queryAvailableEconomySeat(SeatType.WINDOW);
			if (s_temp == null) 
			{
				s_temp = aFlight.getSeating().queryAvailableEconomySeat(SeatType.AISLE);
			}
			else 
			{
				s_temp = aFlight.getSeating().queryAvailableEconomySeat(SeatType.MIDDLE);
			}
			
			if (!(s_temp == null))
			{
				aFlight.getSeating().seats[s_temp.getSeatPosition().getColumn() - 'A'][s_temp.getSeatPosition().getRow() - 1].setReserved(true);
				return aFlight.getSeating().seats[s_temp.getSeatPosition().getColumn() - 'A'][s_temp.getSeatPosition().getRow() - 1];
			}
		}
		
		if (s_temp == null) 
		{
			System.out.println("The flight is full!");
			return null;
		}
		
		for (int c = 0; c <= aFlight.getSeating().getnColumns() - 1; c++) 
		{
			for (int r = 0; r <= aFlight.getSeating().getnRows() - 1; r++) 
			{
				if (aFlight.getSeating().seats[c][r].getSeatDescription().contains(s_temp.getSeatDescription())) 
				{
					aFlight.getSeating().seats[c][r].setReserved(true);
					return aFlight.getSeating().seats[c][r];
				}
			}
		}
		return s_temp;
	}
}
