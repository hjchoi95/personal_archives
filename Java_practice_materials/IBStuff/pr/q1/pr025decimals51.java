/*
Pr 5.1 Write a program that will add up the sequence
1/5 + 1/25 + 1/125 + 1/625 + �� for 100 terms. (0.25)
*/
public class pr025decimals51
{	public static void main (String []args)
	{
		double term = 1;
		double sum = 0;
		for (int i = 0;i<100 ;i++ )
		{
			term = term/5;
			sum=sum+term;
		}
		System.out.println("The sum is "+sum);
	}
}
/*
The sum is 0.2500000000000001
*/