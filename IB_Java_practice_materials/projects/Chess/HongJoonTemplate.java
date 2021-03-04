import java.io.*;
class HongJoonTemplate			//	 Just tried to test
{
//===================================Variables====================================
	final static int arraySize = 30000000;
	static java.util.Scanner s = new java.util.Scanner(System.in);
//===================================PrintMethods====================================
	public static void printLine(int a)		//Spaces (int a) lines
	{
		for (int i=0;i<a ;i++ )
			System.out.println("");
	}
	public static void clearscreen()		//Clears screen
	{
		for (int i=0;i<35 ;i++ )
			System.out.println("");
	}
	public static void textAnimation(String a)		//Prints (String a) character by character
	{
		char[] txt = new char[a.length()];
		txt = a.toCharArray();
		for (int i = 0;i<a.length();i++ )
		{
			delay(15);
			System.out.print(txt[i]);
		}
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
//===================================CalculationMethods====================================
	public static double random(double a, double b)		//returns random number rnging from (double a) to (double b)
	{
		return a+Math.random()*(b-a);
	}
	public static int searchFor(int a, int[] s)		//Search for (int a) in array (int[]s) and returns position of (int a) else, returns -1
	{
		int size = 1000000;
		for (int i=0;i<size ;i++ )
			if (a == s[i])
				return i;
		return -1;
	}
	public static int minOf(int[] s, int size)
	{
		int i=size;
		for (int k=0;k<=i ;k++)
		{
			boolean minchk = true;
			for (int l=0;l<=i ;l++ )
				if ((s[k]>s[l])||s[k]==0)
					if (s[l]>0)
						minchk=false;
			if(minchk==true)
				return k;
		}
		return -1;
	}
	public static int maxOf(int[] s, int size)
	{
		int i=size;
		for (int k=0;k<=i ;k++)
		{
			boolean maxchk = true;
			for (int l=0;l<=i ;l++ )
				if ((s[k]<s[l])||s[k]==0)
					maxchk=false;
			if(maxchk==true)
				return k;
		}
		return -1;
	}
	public static int[] sort(int[] data, int size)
	{
		int[] sortedData = new int[size+1];	//add one for safety.
		int rank = 0;
		int expectedRank=0;
		for (int k=0;k<size ;k++ )
			for (int i=0;i<size ;i++ )
			{
				rank = 0;
				for (int j=0;j<size ;j++ )
					if (data[i]>data[j] )
						rank++;
				if (rank==expectedRank)
				{
					sortedData[expectedRank]=data[i];
					expectedRank++;
				}
			}
		return sortedData;
	}
//===================================Thread/IO/etc...====================================
	public static void delay(int a)		//Stop Thread for (int a) MilliSeconds
	{
		try 
		{ 
			Thread.sleep((int)a);
		}
		catch (Exception e)
		{ 
			System.out.println("Delay Function Error \n Caused by:"+e);
		}
	}
	public static void filewriter(String fileName) throws IOException
	{
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		PrintWriter out = new PrintWriter(fw);
	}
	public static String[] filereader(String fileName) throws IOException		// returns array with loaded data of file (String filename)
	{
		String[] file = new String[arraySize];
		int count=0;
		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		BufferedReader in  = new BufferedReader(fr);
		while (in.ready())
		{
			file[count] = in.readLine();
			count++;
		}
		return file;
	}

//===================================ProgramTest====================================
	public static void test()		//Stop Thread for (int a) MilliSeconds
	{
		System.out.println("read");
	}
	public static long time()
	{
		return System.currentTimeMillis();
	}
//===================================Extraaa====================================
//===================================IntegerToWord====================================
	public static String integerToWord(long num)
	{
		if (num==0)
			return "Zero ";
		String word="";
		if (num>=(1000000000*100))		//Billions
		{
			word+=digitsBelowHundreds(word, num/(1000000000*100));
			word+=CapitalOnFirstLetter(word, 'T')+"rillion ";
			if (num%(1000000000*100)!=0)
				word+="and ";
		}
		num=num%(1000000000*100);
		if (num>=1000000000)		//Billions
		{
			word+=digitsBelowHundreds(word, num/1000000000);
			word+=CapitalOnFirstLetter(word, 'B')+"illion ";
			if (num%1000000000!=0)
				word+="and ";
		}
		num=num%1000000000;
		if (num>=1000000)		//Millions
		{
			word+=digitsBelowHundreds(word, num/1000000);
			word+=CapitalOnFirstLetter(word, 'M')+"illion ";
			if (num%1000000!=0)
				word+="and ";
		}
		num=num%1000000;
		if (num>=1000)			//thousands
		{
			word+=digitsBelowHundreds(word, num/1000);
			word+=CapitalOnFirstLetter(word, 'T')+"housand ";
			if (num%1000!=0)
				word+="and ";
		}
		num=num%1000;
		word=word+digitsBelowHundreds(word, num);
		return word;
	}
	public static String digitsBelowHundreds(String sentence, long num)
	{
		String word ="";
		if (num>=100)		//hundreds
		{
			word+=numToString(sentence,num/100);
			word+="hundred ";
			if (num%100!=0)
				word+="and ";
		}
		num=num%100;//tens
		if (num>=10 && num<20)
				return word+tensToStringException(sentence, num%100);
		else if (num>=20)
			word+=tensToString(sentence, num/10, num%10==0);
		num=num%10;//ones
		word+=numToString(sentence, num);
		return word;
	}
	public static String numToString(String sentence, long num)
	{
		String word="";
		if (num==1)
		word+=CapitalOnFirstLetter(sentence, 'O')+"ne ";
		if (num==2)
			word+=CapitalOnFirstLetter(sentence, 'T')+"wo ";
		if (num==3)
			word+=CapitalOnFirstLetter(sentence, 'T')+"hree ";
		if (num==4)
			word+=CapitalOnFirstLetter(sentence, 'F')+"our ";
		if (num==5)
			word+=CapitalOnFirstLetter(sentence, 'F')+"ive ";
		if (num==6)
			word+=CapitalOnFirstLetter(sentence, 'S')+"ix ";
		if (num==7)
			word+=CapitalOnFirstLetter(sentence, 'S')+"even ";
		if (num==8)
			word+=CapitalOnFirstLetter(sentence, 'E')+"ight ";
		if (num==9)
			word+=CapitalOnFirstLetter(sentence, 'N')+"ine ";
		return word;
	}
	public static String tensToString(String sentence, long num, boolean hyphen)
	{
		String word="";
		String a="-";
		if (hyphen)
			a=" ";
		if (num==2)
			word+=CapitalOnFirstLetter(sentence, 'T')+"wenty"+a;
		if (num==3)
			word+=CapitalOnFirstLetter(sentence, 'T')+"hirty"+a;
		if (num==4)
			word+=CapitalOnFirstLetter(sentence, 'F')+"orty"+a;
		if (num==5)
			word+=CapitalOnFirstLetter(sentence, 'F')+"ifty"+a;
		if (num==6)
			word+=CapitalOnFirstLetter(sentence, 'S')+"ixty"+a;
		if (num==7)
			word+=CapitalOnFirstLetter(sentence, 'S')+"eventy"+a;
		if (num==8)
			word+=CapitalOnFirstLetter(sentence, 'E')+"ighty"+a;
		if (num==9)
			word+=CapitalOnFirstLetter(sentence, 'N')+"inety"+a;
		return word;
	}
	public static String tensToStringException(String sentence, long num)
	{
		String word="";
		if (num==10)
			word+=CapitalOnFirstLetter(sentence, 'T')+"en ";
		if (num==11)
			word+=CapitalOnFirstLetter(sentence, 'E')+"leven ";
		if (num==12)
			word+=CapitalOnFirstLetter(sentence, 'T')+"welve ";
		if (num==13)
			word+=CapitalOnFirstLetter(sentence, 'T')+"hirteen ";
		if (num==14)
			word+=CapitalOnFirstLetter(sentence, 'F')+"ourteen ";
		if (num==15)
			word+=CapitalOnFirstLetter(sentence, 'F')+"ifteen ";
		if (num==16)
			word+=CapitalOnFirstLetter(sentence, 'S')+"ixteen ";
		if (num==17)
			word+=CapitalOnFirstLetter(sentence, 'S')+"eventeen ";
		if (num==18)
			word+=CapitalOnFirstLetter(sentence, 'S')+"ighteen ";
		if (num==19)
			word+=CapitalOnFirstLetter(sentence, 'N')+"ineteen ";
		return word;
	}
	public static char CapitalOnFirstLetter(String sentence, char letter)
	{
		if (!sentence.equals("") || sentence.length()>0)
			letter=(char)((int)letter+32);
		return letter;
	}
}
