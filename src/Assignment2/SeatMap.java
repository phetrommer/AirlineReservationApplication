package Assignment2;

/**
 * this class is used to create a seatmap for the selected 
 * flight which contains a toString to print out the specified 
 * seatmap. it also contains various methods related
 * to getting seat objects.
 * 
 * @author Liam Yates - 18016696
 */

public abstract class SeatMap {

	protected Seat[][] seats;
	protected int nRows;
	protected int nColumns;
	protected int nFirstClassRows;
	
	abstract protected void initialiseSeatMap();
	
	public char lastSeatColumn()
	{
		return this.seats[nColumns-1][nRows-1].getSeatPosition().getColumn();
	}
	
	public int lastSeatRow()
	{
		return this.seats[nColumns-1][nRows-1].getSeatPosition().getRow();
	}
	
	/**
	 * gets the requested seat
	 * @param seatRow the seats row
	 * @param seatColumn the seats column
	 * @return the requested seat
	 */
	
	public Seat getSeat(int seatRow, char seatColumn)
	{
		for (int r = 0; r < nRows; r++)
		{
			for (int c = 0; c < nColumns; c++)
			{
				if (seats[r][c] != null)
				{
					if (seats[r][c].getSeatPosition().getRow() == seatRow && seats[r][c].getSeatPosition().getColumn() == seatColumn)
					{
						return seats[r][c];
					}
				}
			}
		}
		return null;
	}
	
	public Seat getLeft(Seat aSeat)
	{
		if (aSeat.getSeatPosition().getColumn() == 'A')
		{
			return null;
		}
		else 
		{
			return this.seats[aSeat.getSeatPosition().getColumn() - 'B'][aSeat.getSeatPosition().getRow() - 1];
		}
	}
	
	public Seat getRight(Seat aSeat)
	{
		if(aSeat.getSeatPosition().getColumn() == nColumns + '@') 
		{
			return null;
		}
		else
		{
			return this.seats[aSeat.getSeatPosition().getColumn() - '@'][aSeat.getSeatPosition().getRow() - 1];
		}
	}
	
	/**
	 * creates the seatmap layout to print to console.
	 * @return the built seatmap.
	 */
	
	public String toString()
	{
		String temp = "    ";
		char x = 'A';
		
		for (int k = 0; k <= nColumns - 1; k++)
		{
			temp += x;
			temp += "      ";
			x++;
		}
		
		temp += "\n";
		
		for (int i = 0; i <= nRows - 1; i++)
		{
			if (i <= 8)
			{
				temp += " ";
			}
			temp += i + 1;
			
			for (int y = 0; y <= nColumns - 1; y++)
			{
				if (i >= nFirstClassRows)
				{
					temp += " " + this.seats[y][i].toString().toLowerCase();
				}
				else 
				{
					temp += " " + this.seats[y][i].toString();
				}
			}
			temp += "\n";
		}
		return temp;
	}
	
	/**
	 * checks the seatmap for any available economy seats
	 * of the requested seat type.
	 * @param aType the requested seat type
	 * @return a seat if found or null if not found
	 */
	
	public Seat queryAvailableEconomySeat(SeatType aType)
	{
		Seat temp = null;
		
		for (int c = 0; c < nColumns; c++)
		{
			for (int r = nFirstClassRows; r < nRows; r++)
			{
				if (seats[c][r].getSeatType() == aType && !seats[c][r].isReserved())
				{
					return seats[c][r];
				}
				if (!seats[c][r].isReserved() && temp == null)
				{
					temp = seats[c][r];
				}
			}
		}
		if (temp == null)
		{
			return null;
		}
		else
		{
			return this.getSeatsArray()[temp.getSeatPosition().getColumn() - 'A'][temp.getSeatPosition().getRow() - 1];
		}
	}
	
	/**
	 * checks the seatmap for any available first class seats
	 * of the requested seat type.
	 * @param aType the requested seat type
	 * @return a seat if found or null if not found
	 */
	
	public Seat queryAvailableFirstClassSeat(SeatType aType)
	{
		Seat temp = null;
		
		for (int c = 0; c < nColumns; c++)
		{
			for (int r = 0; r < nFirstClassRows; r++)
			{
				if (seats[c][r].getSeatType() == aType && !seats[c][r].isReserved())
				{
					return seats[c][r];
				}
				if (!seats[c][r].isReserved() && temp == null)
				{
					temp = seats[c][r];
				}
			}
		}
		if (temp == null)
		{
			return null;
		}
		else 
		{
			return this.getSeatsArray()[temp.getSeatPosition().getColumn() - 'A'][temp.getSeatPosition().getRow() - 1];
		}
	}

	public int getnRows() 
	{
		return nRows;
	}

	public int getnColumns() 
	{
		return nColumns;
	}

	public int getnFirstClassRows() 
	{
		return nFirstClassRows;
	}
	
	public Seat[][] getSeatsArray()
	{
		return seats;
	}
}
