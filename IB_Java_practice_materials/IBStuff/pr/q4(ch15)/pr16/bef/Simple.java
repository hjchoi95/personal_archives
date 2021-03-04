class Simple
{
	static java.util.Scanner s = new java.util.Scanner(System.in);
	public static void main (String args[]) throws Exception
	{
		int input;
		Company comp = new Company("employeeFile");
		comp.read();
		MainMenuLoop : while (true)
		{
			clearscreen();
			System.out.println("		   ----------Main Menu---------");
			printLine(7);
			System.out.println("			List :		[1]\n");
			System.out.println("			Add  :		[2]\n");
			System.out.println("			Search  :	[3]\n");
			System.out.println("			Quit :		[5]");
			printLine(5);
			input = s.nextInt();
			switch (input)
			{
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break MainMenuLoop;
			default :
				break;
			}
		}
		comp.save();
	}

	public static void printLine(int a)		//Spaces (int a) lines
	{
		for (int i=0;i<a ;i++ )
			System.out.println("");
	}

	public static void clearscreen()		//Clears screen
	{
		for (int i=0;i<30 ;i++ )
			System.out.println("");
	}

	public static String allignedString(String a, String b, int allign)			//Allign (String b) on (byte allign)th line. (String a) is what comes earlier.
	{
		String print="";
		print+=a;
		for (int i=0;i<a.length()-allign ;i++ )
			print+="\b";
		for (int i=allign;i>a.length() ;i-- )
			print+=" ";
		print+=b;
		return print;
	}

}
