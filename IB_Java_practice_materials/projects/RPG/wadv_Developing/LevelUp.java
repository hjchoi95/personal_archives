public class LevelUp extends wadv
{
	final int statPoint = 3;
	final int SpellPoint = 2;
	java.util.Scanner s = new java.util.Scanner(System.in);
	{
		wadv main = new wadv();
		if (main.exp >= main.expmax)
			{
				main.lv++;																// exp increment function
				main.exp = main.exp- main.expmax;
				main.expmax = main.increment;
				main.increment = main.increment+main.increment2;
				main.increment2 = main.increment2+2;
				if (main.lv>25)
					main.increment2 = main.increment2+1;
				if (main.lv>40)
					main.increment2 = main.increment2+1;
				if (main.lv>52)
					main.increment2 = main.increment2+1;
				if (main.lv>62)
					main.increment2 = main.increment2+1;
				if (main.lv>73)
					main.increment2 = main.increment2+3;
				if (main.lv>85)
					main.increment2 = main.increment2+4;
				System.out.println("				Level Up!!");
				System.out.println("");
				System.out.println("			Congrats!! You are now level "+main.lv+"!!");
				System.out.println("			You can now spend 3 attribute points");
				for (int k = 0;k<10 ;k++ )
					System.out.println("");
				System.out.println("Enter any key to continue...");
				main.anykey = s.next();
				lvloop : for (int pt = 0;pt<statPoint ;pt++ )
				{
					for (int k = 0;k<main.clearscreen ;k++ )
						System.out.println("");
					main.callClassType=3;
					CharacterStat UserStat = new CharacterStat();
					main.callClassType=1;
					System.out.println("");System.out.println("");System.out.println("");
					System.out.println("enter : [1] Strength, [2] Agility, [3] Intel, [4] Stamina");
					main.UserOrder2 = s.nextInt();												// stats
					switch (main.UserOrder2)
					{
					default :
						pt--;
						break;
					case 1 :
						main.sa++;
						break;
					case 2 :
						main.sb++;
						break;
					case 3 :
						main.sc++;
						break;
					case 4 :
						main.sd++;
						break;
					}
					for (int k = 0;k<main.clearscreen ;k++ )
						System.out.println("");
					UserStat = new CharacterStat();
				}
				System.out.println("");System.out.println("");
				main.callClassType=3;
				CharacterStat UserStat = new CharacterStat();
				main.callClassType=1;
				System.out.println("");System.out.println("");System.out.println("");
				System.out.println("You spent all stat points. press any key to proceed");
				main.anykey = s.next();
				for (int k = 0;k<main.clearscreen ;k++ )
					System.out.println("");
				if (main.lv >=5)
				{
					sssloop : for (int pt = 0;pt<2 ;pt++ )							// skills
					{
						System.out.println("");
						System.out.println("You can now train with two spells or enchantments");
						System.out.println("");
						SkillTab SKT = new SkillTab(main.skstab, main.skdoublestrike, main.skheal, main.skdebuff, main.skpsionicstorm, main.skallin, skConfringo, skProtego);
						SKT.print();
						main.UserOrder2 = s.nextInt();
						switch (main.UserOrder2)
						{
						default :
							pt--;
							continue sssloop;
						case 2 :
							if (main.skstab <= 5)
								main.skstab++;
							else 
							{
								pt--;continue sssloop;
							}
							break;
						case 3 :
							if (main.skdoublestrike <= 10)
								main.skdoublestrike++;
							else 
							{
								pt--;continue sssloop;
							}
							break;
						case 8 :
							if (main.skheal <= 10)
								main.skheal++;
							else 
							{
								pt--;continue sssloop;
							}
							break;
						case 9 :
							if (main.skdebuff <= 10)
								main.skdebuff++;
							else 
							{
								pt--;continue sssloop;
							}
							break;
						case 4 :
							if (main.skpsionicstorm <= 10)
								main.skpsionicstorm++;
							else 
							{pt--;continue sssloop;}
							break;
						case 5 :
							if (main.skallin <= 10)
								main.skallin++;
							else 
							{
								pt--;continue sssloop;
							}
							break;
						case 6 :
							if (main.skConfringo <= 10)
								main.skConfringo++;
							else 
							{pt--;continue sssloop;}
							break;
						case 7 :
							if (main.skProtego <= 10)
								main.skProtego++;
							else 
							{pt--;continue sssloop;}
							break;
						case 11 :
							if (main.pskCritical <= 10)
								main.pskCritical++;
							else 
							{pt--;continue sssloop;}
							break;
						case 12 :
							if (main.pskManaRest <= 10)
								main.pskManaRest++;
							else 
							{pt--;continue sssloop;}
							break;
						case 13 :
							if (main.pskDodge <= 10)
								main.pskDodge++;
							else 
							{pt--;continue sssloop;}
							break;
						}
						for (int k = 0;k<main.clearscreen ;k++ )
							System.out.println("");
						SKT = new SkillTab(main.skstab, main.skdoublestrike, main.skheal, main.skdebuff, main.skpsionicstorm, main.skallin, skConfringo, skProtego);
						SKT.print();
						if (pt==statPoint-1)
							break;
						
					}
				}
				System.out.println("You spent all stat and skill points. press any key to continue");
				main.anykey = s.next();
			}
	}


}

