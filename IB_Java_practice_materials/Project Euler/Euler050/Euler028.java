class Euler028		//Spiral : Easiest I ever seen!!!!!
{
	final static int sF = 1001*1001;
	public static void main(String[] args) 
	{
		int ans=1;
		int add=1;
		int skip=0;
		while (add<sF)
		{
			if (Math.sqrt((double)add)-Math.round(Math.sqrt((double)add))==0)
				skip+=2;
			add+=skip;
			ans+=add;
		}
		System.out.println("ans = "+ans);
	}
}
