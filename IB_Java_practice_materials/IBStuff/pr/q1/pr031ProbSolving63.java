public class pr031ProbSolving63
{	public static void main (String []args)
	{
		java.util.Scanner s = new java.util.Scanner(System.in);
		System.out.println("Determine the length");// input length
		int a = s.nextInt();
		int c = a;
		int d = 0;

		for (int i = a;i>0 ;i-- )										// increasing number of space
		{
			System.out.println("");
			for (int b=0;b<c ;b++ )	
				System.out.print(" ");
			for (int x = 0;x<a ;x++ )
				System.out.print("*");
			c--;
		}
		for (int i = 0;i<=a ;i++ )								// decreasing number of space
		{
			System.out.println("");
			for (int b=d;b>0 ;b--)
				System.out.print(" ");
			for (int y = 0;y<a ;y++ )
				System.out.print("*");
			d++;
		}
	} 
}
/*
Determine the length
7

       *******
      *******
     *******
    *******
   *******
  *******
 *******
*******
 *******
  *******
   *******
    *******
     *******
      *******
       *******
*/