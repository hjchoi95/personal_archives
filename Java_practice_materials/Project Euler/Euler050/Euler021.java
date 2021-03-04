class Euler021
{
	public static void main(String[] args) 
	{
		int[] amicable = new int[10000];
		int roll = 0;
		int ans = 0;

		for (int i=0; i<10000; i++)
		{
			System.out.println(i+"/"+10000);
			for (int j=0; j<10000; j++)
			{
				if (i!=j && d(i)==j && d(j)==i && !search(i, amicable))
				{
					amicable[roll++]=i;
					amicable[roll++]=j;
					System.out.println(roll);
				}
			}
		}
		System.out.println(add(amicable));
		
	} 
	
	public static int add(int[] s)
	{
		int n=0;
		for (int i=0; i<s.length; i++)
			n+=s[i];
		return n;
	}

	public static boolean search(int a, int[] s)		//Search for (int a) in array (int[]s) and returns position of (int a) else, returns -1
	{
		for (int i=0;i<s.length ;i++ )
			if (a == s[i])
				return true;
		return false;
	}

	public static int d(int num)
	{
		int sum=0;
		 for (int i=1; i<=num/2; i++)
			 if (num%i==0)
				 sum+=i;
		 return sum;
	}
}
