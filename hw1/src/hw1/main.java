package hw1;

public class main 
{
	public static void main(String[] args) 
	{
		//Create strings
		String string1 = Methods.getString();
		String string2 = Methods.getString();
		
		//Convert to int arrays
		int[] int1 = Methods.convertToIntArray(string1);
		int[] int2 = Methods.convertToIntArray(string2);
		
		//Determine if equal
		Methods.determineIfEqual(int1, int2);
	}
}