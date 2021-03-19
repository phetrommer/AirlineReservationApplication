package Assignment2;

/**
 * this class is used to create the seat position objects
 * @author Liam Yates - 18016696
 */

public class SeatPosition {

	private int row;
	private char column;

	public SeatPosition(int row, char column)
	{
		this.row = row;
		this.column = column;
	}

	public int getRow() 
	{
		return row;
	}

	public char getColumn() 
	{
		return column;
	}
	
	public String toString()
	{
		return column + Integer.toString(row);
	}
	
}
