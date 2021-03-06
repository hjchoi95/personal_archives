/****************************************************************************************
JUMBO Seafood Restaurant Reservation Program

Author : HongJoon Choi, 000763-068
School : Overseas Family School
Date     : 30.01.2013

Used Editplus+ 3 for IDE

*****************************************************************************************/

import java.io.*;
class MainMenu extends Utility
{
	static Restaurant myRestaurant = new Restaurant();
	int dfad=0;
	public static void main(String[] args) throws IOException
	{

		myRestaurant.load();
		
		while (true)
		{
			clearScreen();
			System.out.println(allignMiddle("|"+allignMiddle("MAIN MENU",20)+"|",70));
			System.out.println("			[1] Show list");
			System.out.println("			[2] Add reservation to list");
			System.out.println("			[3] Search for names");
			System.out.println("			[4] Current reservation status");
			System.out.println("			[5] Delete outdated reservations");
			System.out.println("			[6] Save and quit");
			spaceLines(3);
			
			switch (inputInt())
			{
			case 1:
				myRestaurant.list();
				break;
			case 2:
				myRestaurant.add();
				break;
			case 3:
				myRestaurant.search();
				break;
			case 4:
				myRestaurant.reservationStatus();
				break;
			case 5:
				myRestaurant.deleteOutdatedReservations();
				break;
			case 6:
				quit();
			default :
				break;
			}
		}
	}

	public static void quit()
	{
		myRestaurant.save();
		System.exit(0);
	}

}