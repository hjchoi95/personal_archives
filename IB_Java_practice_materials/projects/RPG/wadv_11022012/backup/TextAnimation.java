public class TextAnimation
{
	wadv main = new wadv();
	private char[] txt = new char[100];
	private int txtLength;
	TextAnimation(String tx)
	{
		txt = tx.toCharArray();
		txtLength = tx.length();
		for (int i = 0;i<txtLength ;i++ )
		{
			main.delay(main.textSpeed);
			System.out.print(txt[i]);
		}
		System.out.println("");
	}
}

