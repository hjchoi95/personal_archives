import java.io.*;
class DataBase
{
	final static int rollSize = 1000;
	static java.util.Scanner s = new java.util.Scanner(System.in);

	public static char firstLetterOf(String ss)		//return first letter
	{
		if (ss.equals(""))
			return '*';
		return ss.charAt(0);
	}


	static int safeInput()			//Simple s.nextInt() method. Safe from datatype exception
	{
		String input = s.next();
	try
	{
			return Integer.parseInt(input);
	}
	catch (Exception e)
	{
			System.out.println("Invalid input! Caused by : "+e);
	   return 0;
	}
	}

	public static void printLine(int a)		//Spaces (int a) lines
	{
		for (int i=0;i<a ;i++ )
			System.out.println("");
	}

	public static String[] delete(int roll, int found, String[] staff)
	{
		//System.out.println(roll);
		//System.out.println(found);
		String[] staffOverwrite = new String[roll];
		for (int i=0;i<found ;i++ )
		{
			staffOverwrite[i] = staff[i];
		}
		for (int j=found;j<roll-1 ;j++ )
		{
			staffOverwrite[j] = staff[j+1];
		}
		return staffOverwrite;
	}

	public static int[] searchEngine(String ss, String[] staff, int roll)
	{
		int[] list = new int[roll];
		for (int i=0;i<roll ;i++ )	 //initialize array
			list[i] = -1;
		int numberFound = 0;
		for (int i=0;i<roll ;i++ )
		{
			char[] disAssemble = staff[i].toCharArray();
			for (int k=0;k<staff[i].length() ;k++ )
			{
				String reAssembledString ="";
				for (int j=k;j<staff[i].length() ;j++ )
					reAssembledString+=disAssemble[j];
				//pp(reAssembledString);	// disable two comments to test algorithm
				if ((reAssembledString.toLowerCase()).startsWith(ss.toLowerCase()))
				{
					//pp("					Found!!");
					list[numberFound]=i;
					numberFound++;
					break;
				}
			}
		}
		return list;
	}

	public static String[] sort(String[] data, int roll)		//returns array with sorted integers. From lower value to higher value if a=0, from higher to lower if a=1
	{
		String staff[] = new String[rollSize];
		staff = data;
		String swap="";
		for (int k=0;k<roll ;k++ )
			for (int i=0;i<roll ;i++ )
				if (staff[k].compareTo( staff[i] )<0)
				{
					swap = data[k];
					data[k] = data[i];
					data[i] = swap;
				}
		return data;
	}

	public static String integerToWord(long num)
	{
		if (num==0)
			return "zero ";
		String word="";
		if (num>=1000)			//thousands
		{
			word+=digitsBelowHundreds(num/1000);
			word+="thousand ";
			if (num%1000!=0)
				word+="and ";
		}
		num=num%1000;
		word=word+digitsBelowHundreds(num);
		return word;
	}
	public static String digitsBelowHundreds(long num)
	{
		String word ="";
		if (num>=100)		//hundreds
		{
			word+=numToString(num/100);
			word+="hundred ";
			if (num%100!=0)
				word+="and ";
		}
		num=num%100;//tens
		if (num>=10 && num<20)
				return word+tensToStringException(num%100);
		else if (num>=20)
			word+=tensToString(num/10, num%10==0);
		num=num%10;//ones
		word+=numToString(num);
		return word;
	}
	public static String numToString(long num)
	{
		String word="";
		if (num==1)
		word+="one ";
		if (num==2)
			word+="two ";
		if (num==3)
			word+="three ";
		if (num==4)
			word+="four ";
		if (num==5)
			word+="five ";
		if (num==6)
			word+="six ";
		if (num==7)
			word+="seven ";
		if (num==8)
			word+="eight ";
		if (num==9)
			word+="nine ";
		return word;
	}
	public static String tensToString(long num, boolean hyphen)
	{
		String word="";
		String a="-";
		if (hyphen)
			a=" ";
		if (num==2)
			word+="twenty"+a;
		if (num==3)
			word+="thirty"+a;
		if (num==4)
			word+="forty"+a;
		if (num==5)
			word+="fifty"+a;
		if (num==6)
			word+="sixty"+a;
		if (num==7)
			word+="seventy"+a;
		if (num==8)
			word+="eighty"+a;
		if (num==9)
			word+="ninety"+a;
		return word;
	}
	public static String tensToStringException(long num)
	{
		String word="";
		if (num==10)
			word+="ten ";
		if (num==11)
			word+="eleven ";
		if (num==12)
			word+="twelve ";
		if (num==13)
			word+="thirteen ";
		if (num==14)
			word+="fourteen ";
		if (num==15)
			word+="fifteen ";
		if (num==16)
			word+="sixteen ";
		if (num==17)
			word+="seventeen ";
		if (num==18)
			word+="eighteen ";
		if (num==19)
			word+="nineteen ";
		return word;
	}
}