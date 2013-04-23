/* Ashley Packard
 * & Beck Price
 * November 2012
 */

package cihpherGui2;

public class Alphabet 
{
	private static int INDEX = 89;
	
	private String[] alphabetLetters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j","k","l",
			"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", ".", "!", "?", 
			"%", "$", "*", "#",	"@", "&", "(", ")", "^", "+", "-", ";", "[", "]", "<", ">", ":", 
			"_", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ",", "'", "\n", " ", "A", "B", 
			"C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", 
			"T", "U", "V", "W", "X", "Y", "Z", "\"", "=" };
	
	
	// Is empty because encrypt and decrypt inherit it 
	public Alphabet()
	{
	}
	
	
	// Returns the length of my alphabet, which is 59, a prime number
	public int getAlphaLength()
	{
		return INDEX;
	}
	
	// Takes in a character and finds the matching character 
	// in the above array and returns the index value.
	public int getIndex(String letter)
	{
		for(int i = 0; i < INDEX; i++)
		{
			if(letter.equals(alphabetLetters[i]))
			{
				return (i);
			}
		}
		
		System.out.println("Failed to find index of " + letter);
		System.exit(1);
		return -1;
		
		
	}
	
	// Takes an integer and finds the corresponding character in the array.
	public String returnCharacter(double i)
	{
		int pos = (int) i;
		return alphabetLetters[pos];
	}
	
	
}
