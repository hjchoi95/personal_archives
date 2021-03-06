import java.io.*;
class DataBase
{
//
//watt Leg = 1.7air*1.3support*(maxLoad^1.2+def*3)
//watt body = hp/2-load*2*(mp/50)^1/2
//watt wep = (atkAir*1.2)*dmg*(spd/2)^1/2*(acc/100)*4-load*2
//
//			mobile	normal	armed	air
//plasma :	140%	100%	70%		100%
//normal :	100%	100%	100%	100%
//explosive:	70%		100%	140%	100%
//
//Equation for getting A unit's con over B : con(A,B) = (atk(A)*efficiency(A,B)-def(B))*cost(B)*atkSpeed(A) / maxHP(B)*cost(A)
//Equation for selecting A Army(unit[]) against B Army(unit[]) : con(A,B) = 
//========================================Parts Information==========================================
	static Leg legParts[] = new Leg[100];// air, name, melee, stability, maxload, watt
	static Body bodyParts[] = new Body[100]; // name, hp, mp, type, load, watt
	static Weapon weaponParts[] = new Weapon[100];// ,atkAir, name, atk, acc, splash, type, load, watt
	static Unit unit[] = new Unit[10];


	DataBase()
	{
//========================================Leg Parts==========================================
		legParts[0] = new Leg(false, "-", true, 0, 0, 0, 0);//melee, basic
		legParts[1] = new Leg(false, "Walker", true, 10, 40, 65, 1000);//melee, basic
		legParts[2] = new Leg(false, "Kiwi", false, 0, 35, 65, 2000);//support, basic
		legParts[3] = new Leg(false, "RoadRunner", true, 15, 55, 95, 2000);//melee, standards
		legParts[4] = new Leg(false, "Hover", true, 10, 70, 120, 3000);//melee, standard
		legParts[5] = new Leg(false, "offLoader", false, 20, 120, 300, 4000);//support, heavy
		legParts[6] = new Leg(true, "Patroler", true, -5, 45, 105, 6000);//air, melee, light
		legParts[7] = new Leg(false, "Tottler", false, 15, 65, 150, 6000);//support, standard
		legParts[8] = new Leg(false, "Tank", true, 40, 80, 240, 10000);//ground, heavy, tanking unit
		legParts[9] = new Leg(true, "Firgate", true, 10, 70, 200, 12000);//air, melee, standard
		legParts[10] = new Leg(false, "Galloper", true, 15, 100, 180, 9000);//ground, melee, standard

//========================================Body Parts==========================================
		bodyParts[0] = new Body("-", 0, 0, "-", 0, 0, 0);//mobile : light, low health, low mana // armed : heavy, high health, high mana
		bodyParts[1] = new Body("Infantry", 125, 60, "mobile", 15, 50, 1000);
		bodyParts[2] = new Body("QuarterDeck", 240, 100, "armed", 24, 120, 3000);
		bodyParts[3] = new Body("Corporal", 150, 120, "normal", 17, 70, 2000);
		bodyParts[4] = new Body("Squad", 330, 200, "armed", 30, 185, 4000);
		bodyParts[5] = new Body("Platoon", 250, 200, "mobile", 20, 145, 3000);
		bodyParts[6] = new Body("FlushDeck", 420, 220, "armed", 37, 240, 5000);
		bodyParts[7] = new Body("Sergeant", 400, 150, "normal", 34, 210, 7000);
		bodyParts[8] = new Body("Crusier", 550, 300, "armed", 45, 335, 5000);
		bodyParts[9] = new Body("FlushDeck", 420, 110, "normal", 37, 210, 5000);//
		bodyParts[10] = new Body("FlushDeck", 420, 110, "normal", 37, 210, 5000);

//========================================Weapon Parts==========================================
		weaponParts[0] = new Weapon(false, "-", 0, 0, 0, "-",0,0, 0);
		weaponParts[1] = new Weapon(false, "ArmourGun", 16, 80, 2, "normal",15,55, 1500);
		weaponParts[2] = new Weapon(true, "MachineGun", 12, 65, 4, "normal",20,75, 2000);
		weaponParts[3] = new Weapon(false, "Bazooka", 65, 85, 1, "explosive",30,135, 4000);
		weaponParts[4] = new Weapon(true, "Laser", 22, 100, 2, "normal",27,120, 5000);
		weaponParts[5] = new Weapon(true, "CanisterRifle", 32, 70, 2, "plasma",30,120, 3500);
		weaponParts[6] = new Weapon(false, "Rocketier", 55, 50, 3, "explosive",36,185, 4000);
		weaponParts[7] = new Weapon(true, "MiniGun", 25, 80, 4, "normal",40,210, 8000);
		weaponParts[8] = new Weapon(false, "Handcannon", 50, 90, 2, "explosive",38,215, 7000);
		weaponParts[9] = new Weapon(false, "Rocketier", 55, 50, 2, "explosive",34,120, 4000);//
		weaponParts[10] = new Weapon(false, "Rocketier", 55, 50, 2, "explosive",34,120, 4000);
	}







//========================================Program Methods==========================================
	final static int maxArraySize =30000000;
	final static int screenSize = 35;
	final static int delayMilliSeconds = 15;
	final static int battleSpeed = 1000;
	static java.util.Scanner s = new java.util.Scanner(System.in);

	static int safeInput()			//Simple s.nextInt() method. Safe from datatype exception
	{	
		String input = s.next();
		try
		{
				return Integer.parseInt(input);
		}
		catch (Exception e)
		{
			return -1;
		}
	}

	public static void printLine(int a)		//Spaces (int a) lines
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
		char[] txt = new char[a.length()];
		txt = a.toCharArray();
		for (int i = 0;i<a.length();i++ )
		{
			delay(delayMilliSeconds);
			System.out.print(txt[i]);
		}
		System.out.println("");
	}

	public static String pad(String n, int tab)
	{
		do 
			n+=" ";
		while (n.length()<tab);
		return n;
	}

	public static String allignedString(String a, String b, int allign)			//Allign (String b) on (byte allign)th line. (String a) is what comes earlier.
	{
		String ss = a;
		for (int i=0;i<a.length()-allign ;i++ )
			ss+="\b";
		for (int i=allign;i>a.length() ;i-- )
			ss+=" ";
		ss+=b;
		return ss;
	}

	public static String allignToMiddle(String a, String c, int distance)	// " aaa ccc" if distance = 5
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
		return ss+c;
	}
	public String statusBar(double percentage, char dot)
	{
		String bar = "";
		percentage = (int)(percentage*20);
		if (percentage<0)
			percentage=0;
		for (int i = 0;i<percentage ;i++ )
			bar = bar+dot;
		for (int i = 0;i<(20-percentage) ;i++ )
			bar = bar+"_";
		return bar;
	}

	public static double random(double a, double b)		//returns random number rnging from (double a) to (double b)
	{
		return a+Math.random()*(b-a);
	}

	public static void delay(double a)		//Stop Thread for (int a) MilliSeconds
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
	public static char firstLetterOf(String ss)		//return first letter
	{
		if (ss.equals(""))
			return '*';
		return ss.charAt(0);
	}
	public static int searchFor(int a, int[] s)		//Search for (int a) in array (int[]s) and returns position of (int a) else, returns -1
	{
		for (int i=0;i<100 ;i++ )
		{
			try
			{
				if (a == s[i])
					return i;
			}
			catch (Exception e)
			{
				return -1;
			}
		}
		return -1;
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
	public static int[] sort(int[] data, int roll,int a)		//returns array with sorted integers. From lower value to higher value if a=0, from higher to lower if a=1
	{
		int swap=0;
		for (int k=0;k<roll ;k++ )
			for (int i=0;i<roll ;i++ )
				if ((data[k]<data[i] && a==0) || (data[k]>data[i] && a==1))
				{
					swap = data[k];
					data[k] = data[i];
					data[i] = swap;
				}
		return data;
	}
}
