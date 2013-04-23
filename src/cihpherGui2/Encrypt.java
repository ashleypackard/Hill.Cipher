/* Ashley Packard
 * & Beck Price
 * November 2012
 */

package cihpherGui2;

import java.util.ArrayList;
import java.util.Random;

public class Encrypt extends Alphabet
{
	private String secretMessage;
	private String encryptedMessage;
	private int groupsNeeded;
	private int additonalChars;
	
	// Create a three by three matrix, if I wasn't so lazy I'd let the user input a matrix number....:D
	private double[][] matrixA;
	private int ROWS;
    private int COLS;
    
    // Create an array list to hold as many characters as the user inputs!
    ArrayList<Double> beforeEncryption;
    ArrayList<Double> afterEncryption;
    
	
	public Encrypt(String m, int size)
	{
		
		ROWS = size;
		COLS = size;
		
		beforeEncryption = new ArrayList<Double>();
		afterEncryption = new ArrayList<Double>();
		
		secretMessage = m;
		encryptedMessage = "";
		
		int length = secretMessage.length();
		
		// Since the matrix is a 3x3 then groups of three characters are needed.
		// So divide the inputted string by the number of rows of the matrix.
		groupsNeeded = length/ROWS;
		
		// Check to see if an even number of groups was produced by modding.
		int modLength = length%ROWS;
		
		if(modLength != 0)
		{
			//Additional chars to be added
			additonalChars = (ROWS - modLength);
			
			for(int add = 0; add < additonalChars; add++)
			{
				// If the last group has one character in it then duplicate the last character twice.
				String nextLetter = secretMessage.substring(length-1, length);
				secretMessage = secretMessage + nextLetter;
			}
			
			groupsNeeded++;

		}
		
		matrixA = new double[ROWS][COLS];
		
		// Create Matrix using random numbers
		Random randomGenerator = new Random();
		
		for(int x = 0; x < ROWS; x++)
		{
			for(int y = 0; y < COLS; y++)
			{				
				// Get a random number between 0 and 58 inclusive
				int randNum = randomGenerator.nextInt(getAlphaLength());
				matrixA[x][y] = randNum;
			}
		}
		
	}
	
	//Chops up string into chars to get corresponding index from alphabet
	public void chop()
	{
		for(int i = 0; i < secretMessage.length(); i++)
		{
			// Take one character at a time
			String nextChar = secretMessage.substring(i, i+1);
					
			// Add the value to the array list
			beforeEncryption.add((double) getIndex(nextChar));
		}
	}
	
	public void matrixMultiplication()
	{
		// Repeat the matrix multiplication for every group
		for(int i = 0; i < groupsNeeded; i++)
		{
			double[] tempVector = new double[ROWS];

			// Prep the vector for multiplying!
			// Add the first three values in the arra list to a temp vector
			// This way multiplying will be easier.
			for(int j = 0; j < ROWS; j++)
			{
				tempVector[j] = beforeEncryption.get(j); 
			}
			
			// Delete the first three values from the array list, backwards of course,
			// this way when the loop starts over the next three values will be added to the temp vector
			for(int m = (ROWS - 1); m >= 0; m--)
			{
				beforeEncryption.remove(m);
			}
	
			
			// This is where the magic happens.
			for(int k = 0; k < ROWS; k++)
			{
				double tempIndex = 0;
				
				for(int c = 0; c < COLS; c++)
				{
					// What this says is take the first element in the row and multiply it by the first
					// element in the vector. Then take the second element in the row and multiply it by the
					// second element in the vector. Take the third element in the row and multiply it by the
					// third element in the vector. Add this values and you have the first element for the new
					// vector. Repeat process using the next row and this will be the value for the next element
					// in the vector.
					tempIndex = tempIndex + (matrixA[k][c]*tempVector[c]);
	
				}
				
				
				// Modding is required to get the large number produced by matrix mult.
				// to be equivalent to an index.
				tempIndex = tempIndex%getAlphaLength();
				
				
				// Add the encrypted value to the array list
				afterEncryption.add(tempIndex);
				
			}
		}
		
	}
	
	public String printEncryptedMessage()
	{
		for(int i = 0; i < afterEncryption.size(); i++)
		{
			// Convert the values to their appropriate characters
			encryptedMessage = encryptedMessage + returnCharacter(afterEncryption.get(i));
		}
		
		return encryptedMessage;
	}
	
	// Print the matrix just so everyone knows it works and can follow along.
	public String printMatrix()
	{

		String matrix = "";

		for(int x = 0; x < ROWS; x++)
		{
			for(int y = 0; y < COLS; y++)
			{
				
				matrix = matrix + "[" + matrixA[x][y] + "]";
				
			}
		
			matrix = matrix + "\n";
		}
		
		return matrix;
	}
	
	
	public int getGroups()
	{
		return groupsNeeded;
	}
	
	public int getROWS()
	{
		return ROWS;
	}
	
	public int getCOLS()
	{
		return COLS;
	}
	
	public ArrayList<Double> getEncryptedVector()
	{
		return afterEncryption;
	}
	
	public ArrayList<Double> getorig()
	{
		return beforeEncryption;
	}
	
	public double[][] getMatrix()
	{
		return matrixA;
	}
	
	public int getAdditionalChars()
	{
		return additonalChars;
	}
	
	
}
