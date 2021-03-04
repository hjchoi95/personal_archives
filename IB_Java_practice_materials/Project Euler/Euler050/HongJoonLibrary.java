import java.math.BigInteger;
import java.io.*;
/*contents
15----------Print methods
100---------Calc methods





*/
class HongJoonLibrary			//	 Just tried to test
{
	final static int screenSize = 30;
	final static int delayMilliSeconds = 15;
	static long initialTimeInSpeedCheck=0;
	static java.util.Scanner s = new java.util.Scanner(System.in);
//===================================Print methods====================================
	
	static int inputInt()			//Simple s.nextInt() method. Safe from datatype exception
	{	
		String input = s.next();
		try{return Integer.parseInt(input);}
		catch (Exception e){System.out.println("Invalid input! Caused by : "+e);return 0;}
	}

	public static void lineSpace(int a)		//Spaces (int a) lines
	{
		for (int i=0;i<a ;i++ )
			System.out.println("");
	}

	public static void clearscreen()		//Clears screen
	{
		for (int i=0;i<screenSize ;i++ )
			System.out.println("");
	}

	public static void textAnimation(String a)		//Prints (String a) character by character
	{
		char[] txt = a.toCharArray();
		for (int i = 0;i<a.length();i++ )
		{
			delay(delayMilliSeconds);
			System.out.print(txt[i]);
		}
		System.out.println("");
	}

	public static String allignAtPosition(String a, String b, int allign)			//Allign (String b) on (byte allign)th line. (String a) is what comes earlier.
	{
		String ss = a;
		for (int i=0;i<a.length()-allign ;i++ )
			ss+="\b";
		for (int i=allign;i>a.length() ;i-- )
			ss+=" ";
		ss+=b;
		return ss;
	}

	public static String allignLeft(String n, int tab)						// writes string and leaves space
	{
		do 
			n+=" ";
		while (n.length()<tab);
		return n;
	}

	public static String allignMiddle(String a, int distance)	// " aaa ccc" if distance = 5
	{
		String ss = "";
		int space=distance-a.length();
		if (space%2!=0)
		    ss+=" ";
		for (int i=0;i<space/2 ;i++ )
		    ss+=" ";
		ss+=a;
		for (int i=0;i<space/2 ;i++ )
		    ss+=" ";
		return ss;
	}

	public static String allignRight(String a, int allign)
	{
		String ss="";
		for (int i=0;i<allign-a.length() ;i++ )
			ss+=" ";
		return ss+a;
	}

	public static String appendCommas(int n)
    {
    	String ss = "";
		int count=0;
		while (n>=1)
		{
			if (count%3==0 && count>1)
				ss=","+ss;
			ss=n%10+ss;
			n/=10;
			count++;
		}
		return ss;
    }

//===================================CalculationMethods====================================
	public static double random(double a, double b)		//returns random number rnging from (double a) to (double b)
	{
		return a+Math.random()*(b-a);
	}

	public static double round(double num, int digit)             //rounds number to given digit(decimal place)
	{
		return (double)((int)(num*digit))/digit;
	}

	public static int search(int a, int[] s)		//Search for (int a) in array (int[]s) and returns position of (int a) else, returns -1
	{
		for (int i=0;i<s.length ;i++ )
			if (a == s[i])
				return i;
		return -1;
	}
	
	public static int[] smartSearch(String ss, String[] staff, int roll)		// search for array of data that includes any part of the string
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
				System.out.println(reAssembledString);	// enable it to test algorithm
				if ((reAssembledString.toLowerCase()).startsWith(ss.toLowerCase()))
				{
					System.out.println("					Found!!");
					list[numberFound]=i;
					numberFound++;
					break;
				}
			}
		}
		return list;
		//Method to print searched Strings : 
		/*
		for (int i=0;i<maxArraySize ;i++ )
		{
			if (listFound[i]==0)
				break;
			pp(data[listFound[i]]);
		}
		*/
	}

	public static String[] delete(int roll, int found, String[] staff)		//delete data from list
	{
		String[] staffOverwrite = new String[roll];
		for (int i=0;i<found ;i++ )
			staffOverwrite[i] = staff[i];
		for (int j=found;j<roll-1 ;j++ )
			staffOverwrite[j] = staff[j+1];
		return staffOverwrite;
	}

	
	
	public static int minOf(double[] list, int length)
	{
		double temp=list[0];
		int position=0;
		for (int i=0;i<length;i++)
			if (list[i]<temp)
			{
				temp=list[i];
				position=i;
			}
		return position;
	}

	public static int maxOf(double[] list, int length)
	{
		double temp=list[0];
		int position=0;
		for (int i=0;i<length;i++)
			if (list[i]>temp)
			{
				temp=list[i];
				position=i;
			}
		return position;
	}

	public static int[] sort(int[] data, int length, int a)		//returns array with sorted integers. From lower value to higher value if a=0, from higher to lower if a=1
	{
		int swap=0;
		for (int k=0;k<length ;k++ )
			for (int i=0;i<length ;i++ )
				if ((data[k]<data[i] && a==0) || (data[k]>data[i] && a==1))
				{
					swap = data[k];
					data[k] = data[i];
					data[i] = swap;
				}
		return data;
	}

	public static int[] selectionSort(int[] list)//select smallest number
	{
		int[] sortedList = new int[list.length];
		for (int i=0;i<list.length; i++)
		{
			int locationOfMin = i;
			for (int j=i;j<list.length ;j++ )
				if (list[j]<list[i])
					locationOfMin = j;
			sortedList[i] = list[locationOfMin];
		}
		return sortedList;

	}

//===================================Thread/IO/etc...====================================
	public static void delay(int a)		//Stop Thread for (int a) MilliSeconds
	{
		try {Thread.sleep((int)a);}
		catch (Exception e){System.out.println("Delay Function Error \n Caused by:"+e);}
	}
	
	public static String[] fileReader(String fileName) throws IOException		// returns array with loaded data of file (String filename)
	{
		int count=0;
		String[] file = new String[300000000];
		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		BufferedReader in  = new BufferedReader(fr);
		while (in.ready())
			file[count++] = in.readLine();
		return file;
	}

	public static void save(String fileName, String[]data, int arraySize) throws IOException
	{
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		PrintWriter out = new PrintWriter(fw);
		for (int i=0;i<arraySize ;i++ )
				out.println(data[i]);
		out.close();
	}

//===================================ProgramTest====================================
	public static void test()		
	{
		System.out.println("read");
	}

	public static long time()
	{
		return System.currentTimeMillis();
	}

	public static void startTimer()
	{
		initialTimeInSpeedCheck=System.currentTimeMillis();
	}

	public static void endTimer()
	{
		System.out.println("time = "+(System.currentTimeMillis()-initialTimeInSpeedCheck)+"ms");
	}

	public static void beep()
	{
		System.out.print((char)7);
	}

//===================================Math Methods====================================
	public static double[] solveQuadraticEquation(double a, double b, double c)
	{	
		double[] x = new double[2];
		int i=0;
		if (b*b-4*a*c>0)
		{
			x[0] = (-b-Math.sqrt(b*b-4*a*c))/(2*a);
			x[1] = (-b-Math.sqrt(b*b-4*a*c))/(2*a);
		}
		return x;
	}

	public static boolean primeCheck(int num)
	{
		for (int a = 2;a<num/1.8 ;a++ )
			if (num%a==0 && num!=2)
				return false;
		return true;
	}

	public static String inverseString(String ss)
	{
		String result="";
		char[] ssC = ss.toCharArray();
		for (int i=ss.length()-1;i>=0 ;i-- )
			result = result+ssC[i];
		return result;
	}

	public static int binaryToDecimal(String bin)
	{
		char[] xx = bin.toCharArray();
		int dec = 0;
		for (int i=0;i<bin.length() ;i++ )
			if (xx[i]!='1' && xx[i] !='0')
				return 0;
		for (int k = 0; k<bin.length();k++)			// binary conversion algorithm
		{
			int t=1;
			for (int a = bin.length()-1; a>k; a--)
				t=t*2;
			dec = dec+((int)xx[k]-48)*t;
		}
		return dec;
	}

	public static String numArrangement(int num)
	{
		int[] convert = new int[10];
		String arrangedString = "";
		while (num>0)
		{
			convert[num%10]++;
			num/=10;
		}
		for (int i=9;i>=0 ;i-- )
			arrangedString+=convert[i];
		return arrangedString;
	}

	static int gcd(int f, int g)//Greatest common divisor
	{
		while (true)
		{
			if (f>g)
				f = f-g;
			if (g>f)
				g = g-f;
			if (g == f)
				break;
		}
		return g;
	}

	public static int[] primeNumbersUntil(int num)
	{
		boolean chk = false;
		int[] prime = new int[(int)(num/5)+100];
		prime[0] = 2;
		int roll=1;
		for (int you=1;you<num ;you+=2 )		//for optimum performance
		{
			chk=false;
			for (int a = 2;a<you ;a++ )									//factor finding
			{
				if (you%a==0)
				{
					chk = true;
					break;		//for optimum performance
				}
			}
			if (!chk && you!=1)
			{
				prime[roll]=you;
				roll++;
			}
		}
		return prime;
	}
	
	public static int XOR(int x, int y)
	{
		byte maxBinSize=25;
		String XOR = "";
		String xBin = "";//Integer.toBinaryString(x);
		String yBin = "";//Integer.toBinaryString(y);
		for (int i=0;i<maxBinSize-(Integer.toBinaryString(x)).length() ;i++)
			xBin+="0";
		for (int i=0;i<maxBinSize-(Integer.toBinaryString(y)).length() ;i++)
			yBin+="0";
		xBin+=""+Integer.toBinaryString(x);
		yBin+=""+Integer.toBinaryString(y);
		for (int i=0;i<maxBinSize ;i++ )
		{
			if (xBin.charAt(i)!=yBin.charAt(i))
				XOR+="1";
			else
				XOR+="0"; 
		}
		return binaryToDecimal(XOR);
	}

	public static String encryptOrDecrypt(String data, int masterKey)
	{
		char[] xx = data.toCharArray();
		data="";
		for (int j=0;j<10000 ;j++ )
		{
			try
			{
				data+=(char)(XOR((int)xx[j],masterKey));
			}
			catch (Exception e)
			{
				break;
			}
		}
		return data;
	}

//===================================IntegerToWord(String)====================================
	public static String convertIntegerToSpoken(long num)
	{
		if (num==0)
			return "zero ";
		String word="";
		if (num>=(1000000000*100))		//Billions
		{
			word+=digitsBelowHundreds(num/(1000000000*100));
			word+="trillion ";
			if (num%(1000000000*100)!=0)
				word+="and ";
		}
		num=num%(1000000000*100);
		if (num>=1000000000)		//Billions
		{
			word+=digitsBelowHundreds(num/1000000000);
			word+="billion ";
			if (num%1000000000!=0)
				word+="and ";
		}
		num=num%1000000000;
		if (num>=1000000)		//Millions
		{
			word+=digitsBelowHundreds(num/1000000);
			word+="million ";
			if (num%1000000!=0)
				word+="and ";
		}
		num=num%1000000;
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
		else if (num==2)
			word+="two ";
		else if (num==3)
			word+="three ";
		else if (num==4)
			word+="four ";
		else if (num==5)
			word+="five ";
		else if (num==6)
			word+="six ";
		else if (num==7)
			word+="seven ";
		else if (num==8)
			word+="eight ";
		else if (num==9)
			word+="nine ";
		return word;
	}
	public static String tensToString(long num, boolean hyphen)
	{
		String word="";
		String a="-";
		if (hyphen)
			a=" ";
		else if (num==2)
			word+="twenty"+a;
		else if (num==3)
			word+="thirty"+a;
		else if (num==4)
			word+="forty"+a;
		else if (num==5)
			word+="fifty"+a;
		else if (num==6)
			word+="sixty"+a;
		else if (num==7)
			word+="seventy"+a;
		else if (num==8)
			word+="eighty"+a;
		else if (num==9)
			word+="ninety"+a;
		return word;
	}
	public static String tensToStringException(long num)
	{
		String word="";
		if (num==10)
			word+="ten ";
		else if (num==11)
			word+="eleven ";
		else if (num==12)
			word+="twelve ";
		else if (num==13)
			word+="thirteen ";
		else if (num==14)
			word+="fourteen ";
		else if (num==15)
			word+="fifteen ";
		else if (num==16)
			word+="sixteen ";
		else if (num==17)
			word+="seventeen ";
		else if (num==18)
			word+="eighteen ";
		else if (num==19)
			word+="nineteen ";
		return word;
	}
}
