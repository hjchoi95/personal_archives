public class pr011remainderpr22
{
	public static void main (String[]args)
	{
		java.util. Scanner s = new java.util. Scanner(System.in);

		System.out.println("Type the number");
		int you = s.nextInt();

		if (you%2==0)
		{
			System.out.println("EVEN");
		}
		else if(you%2==1)
		{
			System.out.println("ODD");
		}
	}
}
/*
Type the number
462
EVEN
*/