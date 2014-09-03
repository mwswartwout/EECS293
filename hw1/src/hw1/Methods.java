package hw1;

import java.util.Arrays; //Needed to sort arrays
import java.util.Scanner; //Needed to read strings

public class Methods 
{
	static Scanner scan = new Scanner(System.in);
	
	//Gets a string from the scanner
	public static String getString()
	{
		System.out.println("Enter a string");
		return scan.next();
	}
	
	//Turns a string into a integer array based on character value, and sorts that array
	public static int[] convertToIntArray(String string)
	{
		int[] intArray = new int[string.length()];
				
		for (int i = 0; i < string.length(); i++)
		{
			intArray[i] = Character.getNumericValue(string.charAt(i));
		}
		
		Arrays.sort(intArray);
		
		return intArray;
	}
	
	//Checks if two arrays are equals
	public static void determineIfEqual(int[] int1, int[] int2)
	{
		if (Arrays.equals(int1, int2))
			System.out.println("The strings are equivalent");
		else
			System.out.println("The strings are not equivalent");
	}
}
