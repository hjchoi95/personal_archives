import java.io.*;
class Employee extends DataBase
{
	private String name;
	private int wage;
	private int birth;
	Employee()
	{
	    name = "unnamed_employee";
	    wage = 0;
	    birth = 1940;
	}
	Employee(String name, int wage, int birth)
	{
		this.name = name;
		this.wage = wage;
		this.birth = birth;
	}
	public String getName()
	{
		return this.name;
	}
	public int getWage()
	{
		return this.wage;
	}
	public int getBirth()
	{
		return this.birth;
	}
	public String toString()
	{
		return allignToMiddle(name,"|",30)+allignToMiddle(""+wage,"|",20)+allignToMiddle(""+birth,"|",20);
	}
	public void save(PrintWriter xx) throws IOException
	{
		xx.println(name);
		xx.println(wage);
		xx.println(birth);
	}
	public void read(BufferedReader xx) throws IOException
	{
		name = xx.readLine();
		wage = Integer.parseInt(xx.readLine());
		birth = Integer.parseInt(xx.readLine());
	}
}