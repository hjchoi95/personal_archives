/*
Change the program above so that decimal places line up. To do this you must change the
decimal answer xx into a string (String yy = “” + xx;). Then use the command yy.indexOf(‘.’)
to find the position the decimal place is in the string. eg if String yy = “47.29”; then
yy.indexOf(‘.’) will be 2. Remember that counting starts from 0. Then add enough spaces at
the start to line up the number.
*/
public class pr72 extends IBIO
{
    public static void main (String args [] )
    {
		double xx = 1;
		for (int i = 0; i < 10; i++)
		{ 
			xx = xx * 3.732;
			xx = (double)((long)(xx*100))/100;
			String yy = "" + xx;
			int a = yy.indexOf('.');
			for (int k = 0; k<(6-a); k++)			// number of space
				out(" ");
			output(xx);
		}
    }
}
/*
     3.73
    13.92
    51.94
   193.84
   723.41
  2699.76
 10075.5
 37601.76
140329.76
523710.66
*/