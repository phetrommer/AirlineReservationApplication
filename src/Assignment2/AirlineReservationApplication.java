package Assignment2;

/**
 * This class contains the main of the application which implements
 * all classes and methods. This system allows the user to request
 * and reserve seats of their specified type for their desired airline.
 * it initializes the program with all relevant data, and then saves 
 * it into the flights.txt file once all requested seats have been booked. 
 * @author Liam Yates - 18016696
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AirlineReservationApplication {

	private static ArrayList<Flight> flights = new ArrayList<Flight>();
	private static ArrayList<Airline> airlines = new ArrayList<Airline>();
	private static Scanner keyboard = new Scanner(System.in);
	private static String input = null;
	private static boolean menu = true;
	private static int choice;
	private static String m_choice;
	
	private static void getSeatClass() throws NumberFormatException, IOException
	{
		//get seat class, show map, quit
		m_choice = null;
		while (menu)
		{
			System.out.println("1: Reserve First Class\n2: Reserve Economy Class\n3: Show Seating Map\n4: Quit");	
			m_choice = keyboard.nextLine();
			if ((m_choice.equals("1")) || (m_choice.equals("2")))
			{
				while (menu)
				{
					System.out.println("Which seat type?\n1: WINDOW\n2: AISLE\n3: MIDDLE");
					input = keyboard.nextLine();
					if ((input.equals("1")) || (input.equals("2")) || (input.equals("3"))) 
					{
						menu = false;
					}
				}
				menu = true;
				Seat temp = null;
				if (m_choice.equals("1"))
				{
					if (input.equals("1"))
					{
						temp = airlines.get(0).reserveFirstClass(flights.get(choice), SeatType.WINDOW);
					}
					if (input.equals("2"))
					{
						temp = airlines.get(0).reserveFirstClass(flights.get(choice), SeatType.AISLE);
					}
					if (input.equals("3")) 
					{
						temp = airlines.get(0).reserveFirstClass(flights.get(choice), SeatType.MIDDLE);
					}
					System.out.println(flights.get(choice).getSeating().toString());	
					System.out.println((temp == null ? "Flight is full! sorry!" : "Seat reservation: " + temp.getSeatDescription()));
				}	
				if (m_choice.equals("2"))
				{
					if (input.equals("1")) 
					{
						temp = airlines.get(0).reserveEconomy(flights.get(choice), SeatType.WINDOW);
					}
					if (input.equals("2"))
					{
						temp = airlines.get(0).reserveEconomy(flights.get(choice), SeatType.AISLE);
					}
					if (input.equals("3")) 
					{
						temp = airlines.get(0).reserveEconomy(flights.get(choice), SeatType.MIDDLE);
					}
					System.out.println(flights.get(choice).getSeating().toString());	
					System.out.println((temp == null ? "Flight is full! sorry!" : "Seat reservation: " + temp.getSeatDescription()));
				}
				menu = true;
			}
			if ((m_choice.equals("3")))
			{
				System.out.println(flights.get(choice).getSeating().toString());
			}
			if (m_choice.equals("4"))
			{
				System.out.println("Goodbye!");
				menu = false;
				m_choice = ("1");
			}
		}
		menu = true;
	}

	public static void getFlight() throws NumberFormatException, IOException
	{

		//get flight choice
		choice = Integer.parseInt(input) - 1;
		while (menu)
		{
			System.out.println("Which flight would you like to reserve a seat on? (choose a number)");
			for (int k = 0; k < 2; k++)
			{
				System.out.println(k+1 + ": " + flights.get(k));
			}
			input = keyboard.nextLine();
			if (input.equals("1") || input.equals("2"))
			{
				menu = false;
				System.out.println("Booking seats for " + flights.get(choice));
				System.out.println(flights.get(choice).getSeating().toString());
			}
		}
		menu = true;
		getSeatClass();
	}
	
	public static void getAirline() throws NumberFormatException, IOException
	{
		//gets airline choice
		while (menu)
		{
			System.out.println("Which airline would you like to travel with? (Select a number or write quit if you want to quit the application!)");
			System.out.println("1: SimpleWay Airline\n2: SmartLine Airline");
			input = keyboard.nextLine();
			if (input.equals("1") || input.equals("2"))
			{
				menu = false;
			}
			if (input.equals("quit"))
			{
				System.out.println("Quitting, goodbye!");
				update();
			}
			//creates airline for choice
			if (input.equals("1"))
			{
				airlines.add(0, new SimpleWay("SimpleWay Airline"));
				System.out.println("Welcome to the SimpleWay Airline reservation system");
			}
			if (input.equals("2"))
			{
				airlines.add(0, new SmartLine("SmartLine Airline"));
				System.out.println("Welcome to the SmartLine reservation system");
			}
		}
		menu = true;
		getFlight();
	}
	
	public static void setup() throws NumberFormatException, IOException
	{
		//attempts to read in file
		try 
		{
			File text = new File("flights.txt");
			if (text.length() == 0) 
			{
				System.out.println("flights.txt is empty! cannot continue!");
				System.exit(0);
			}
			keyboard = new Scanner(text);
			while (keyboard.hasNextLine())
			{
				String line = keyboard.nextLine();
				if (line.equals("@F_info"))
				{
					line = keyboard.nextLine();
					//stores pulled text into array for later use
					String[] f_info = line.split(",");
					if (f_info[4].equals("Airbus"))
					{
						AirBusSeatMap airMap = new AirBusSeatMap();
						flights.add(0, new Flight(f_info[0], f_info[1], f_info[2], f_info[3], f_info[4], airMap));
					}
					if (f_info[4].equals("Boeing"))
					{
						BoeingSeatMap boeMap = new BoeingSeatMap();
						flights.add(1, new Flight(f_info[0], f_info[1], f_info[2], f_info[3], f_info[4], boeMap));
					};	
				}
			}
		} 
		catch (IOException e) 
		{
			System.out.println("Error: cannot read from file");
		}
		catch (NumberFormatException e)
		{
			System.out.println("Integer parsing failed!");
		}
		
		//initialization complete - starts program
		System.out.println("*****Welcome to Airline Reservation Application*****");
		keyboard = new Scanner(System.in);
		boolean mainMenuChoice = true;
		while (mainMenuChoice)
		{
			getAirline();
		}
	}	

	/**
	 * This writes the updated seatmaps to the flights.txt. 
	 */
	
	public static void update() throws IOException
	{
		try 
		{
			FileWriter text = new FileWriter("flights.txt");
			text.write(Integer.toString(flights.size()) + "\n");

			for (int k = 0; k < flights.size(); k++)
			{
				String temp = flights.get(k).toString();
				String[] temp2 = temp.split(" ");
				
				text.write("@Flight\n");
				text.write("@F_info\n");
				text.write(temp2[1] + "," + temp2[3] + "," + temp2[5] + "," + temp2[7] + "," + flights.get(k).getseatmapType() + "\n");
				text.write("@F_R_Seats\n");
				
				for (int r = 0; r < flights.get(k).getSeating().getnRows(); r++)
				{
					for (int c = 0; c < flights.get(k).getSeating().getnColumns(); c++)
					{
						if (flights.get(k).getSeating().getSeatsArray()[c][r].isReserved() == true)
						{							
							text.write(flights.get(k).getSeating().getSeatsArray()[c][r].getSeatPosition().getRow() + "_" + flights.get(k).getSeating().getSeatsArray()[c][r].getSeatPosition().getColumn());
							text.write(",");
						}
					}
					}	
				text.write("\n@@\n");
			}

			text.close();
			System.exit(0);
		} 
		catch (IOException e) 
		{
			System.out.println("Cannot write to file!");
		}
	}
		public static void main(String[] argc) throws NumberFormatException, IOException
	{
		setup();
	}
}
