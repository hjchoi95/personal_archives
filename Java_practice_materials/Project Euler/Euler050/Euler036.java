class Euler036 extends HongJoonLibrary
{
	public static void main(String[] args) 
	{
		int ans=0;
		for (int i=1;i<1000000 ;i++ )
			if (Integer.toBinaryString(i).equals(inverseString(Integer.toBinaryString(i))) &&  (""+i).equals(inverseString(""+i)))
				ans+=i;
		System.out.println("ans : "+ans);
	}
}
// 5 lines of program to solve project euler problem!!!!