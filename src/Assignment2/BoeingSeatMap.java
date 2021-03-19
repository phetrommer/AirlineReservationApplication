package Assignment2;

/**
 * This class creates the Air Bus seat map which initializes 
 * an array of seats in an aircraft to form the passenger 
 * seating layout.
 * @author Liam Yates - 18016696
 */

public class BoeingSeatMap extends SeatMap {

	@Override
	protected void initialiseSeatMap() 
	{
		char column = '@';
		SeatType s_type = null;
		boolean f_class = false;
		
		for (int k = 0; k < nColumns; k++)
		{
			column++;
			for (int i = 0; i <= nRows - 1; i++)
			{
				SeatPosition pos = new SeatPosition((i + 1), column);
				
				if (i + 1 <= nFirstClassRows)
				{
					f_class = true;
				}
				else 
				{
					f_class = false;
				}
				
				if (nColumns < nRows)
				{
					if (k == 0 || k == nColumns - 1) 
					{
						s_type = SeatType.WINDOW;
					}
					else if (k == 1 || k == 2 || k == 4 || k == 5)
					{
						s_type = SeatType.AISLE;
					}
					else
					{
						s_type = SeatType.MIDDLE;
					}
				}
				this.seats[k][i] = new Seat(pos, s_type, f_class);
				seats[k][i].setReserved(false);
			}
		}	
	}
	
	public BoeingSeatMap()
	{
		nRows = 10;
		nColumns = 7;
		nFirstClassRows = 4;
		this.seats = new Seat[nColumns][nRows];

		initialiseSeatMap();
	}
}
