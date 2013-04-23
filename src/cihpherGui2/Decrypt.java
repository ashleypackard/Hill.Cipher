/* Ashley Packard
 * & Beck Price
 * November 2012
 */

package cihpherGui2;

import java.util.ArrayList;

public class Decrypt extends Alphabet
{
	// We need the inverse of each index so as to correctly determine the inverse matrix
	private double[][] multiplicationTable;
	private double[] inverseOfAlphabet;

	private double[][] matrixA;
	private double[][] identityMatrix;
	private int ROWS;
    private int COLS;
    
    private int extraChars;
	private int groupsNeeded;
	private String decryptedMessage;
    ArrayList<Double> beforeDecryption;
    ArrayList<Double> afterDecryption;


	public Decrypt(ArrayList<Double> message, double[][] theMatrix, int numOfRows, 
			int groups, int additionalCharacters)
	{
		multiplicationTable = new double[getAlphaLength()][getAlphaLength()];
		inverseOfAlphabet = new double[getAlphaLength()];
		
		ROWS = numOfRows;
		COLS = numOfRows;
		
		matrixA = theMatrix;
		identityMatrix = new double[ROWS][COLS];
		
		// Set the identity matrix up
		for(int r = 0; r < ROWS; r++)
		{
			for(int c = 0; c < COLS; c++)
			{
				// Only the diagonal need be 1
				if(r == c)
				{
					identityMatrix[r][c] = 1;
				}
				else
				{
					identityMatrix[r][c] = 0;

				}
			}
		}
		
		groupsNeeded = groups;
		extraChars = additionalCharacters;
		
		decryptedMessage = "";
		beforeDecryption = message;
		afterDecryption = new ArrayList<Double>();

	}
	
	public void determineInversesOfIndex()
	{
		// Create a multiplication table mod your alphabet length
		for(int i = 0; i < getAlphaLength(); i++)
		{
			for(int j = 0; j < getAlphaLength(); j++)
			{
				multiplicationTable[i][j] = ((i*j)%getAlphaLength());
			}
		}
		
		for(int d = 0; d < getAlphaLength(); d++)
		{
			for(int k = 0; k < getAlphaLength(); k++)
			{
				if(multiplicationTable[d][k] == 1)
				{
					inverseOfAlphabet[d] = k;
				}
			}
		
		}
		
	}
	
	public void matrixOperations()
	{
		double multiplier;
		int r = 0;
		
		// rowTracker keeps track of the rows beneath the diagonal of 1's
		int rowTracker = 1;
		// This is a crappy way of doing this but.... :D
		int rowResetter = 2;
		
		for(int col = 0; col < COLS; col++)
		{
			// The diagonal entry cannot be a zero
			if(matrixA[r][col] == 0)
			{
				for(int swappingR = r; swappingR < ROWS; swappingR++)
				{
					if(matrixA[swappingR][col] != 0)
					{
						swap(r, swappingR);
					}
					
				}
			}
						
			multiplier = (inverseOfAlphabet[(int) (matrixA[r][col])]);
			
			// Turn entry into a one
			multByInverse(multiplier, r);
			modMatrix();

			while(rowTracker < ROWS)
			{
				if(matrixA[rowTracker][col] != 0)
				{
					
					// Turn all entries below into a zero
					multiplier = -(matrixA[rowTracker][col]);
					multByScalar(multiplier, rowTracker, r);

				}

				modMatrix();
				rowTracker++;
				
			}
			
		
			r++;
			rowTracker = rowResetter;
			rowResetter++;
			modMatrix();


		}
		
		
		// At this point the matrix should look like this:
		// [1][x][x]
		// [0][1][x]
		// [0][0][1]
		// Now we need to get the x's to be zeros working backwards if the x's aren't already a 0
				
		
		rowTracker = (ROWS - 2);
		rowResetter = rowTracker - 1;
		r = (ROWS - 1);
		for(int backwardsCols = (COLS - 1); backwardsCols > 0; backwardsCols--)
		{
			while(rowTracker >= 0)
			{
				if(matrixA[rowTracker][backwardsCols] != 0)
				{
					// Turn all entries above into a zero
					multiplier = -(matrixA[rowTracker][backwardsCols]);
					multByScalar(multiplier, rowTracker, r);
				}

				modMatrix();
				rowTracker--;
			}
			rowTracker = rowResetter;
			rowResetter--;
			r--;
			modMatrix();
			
		}
		
		
	}
	
	
	public void multByInverse(double mult, int row)
	{
		for(int column = 0; column < COLS; column++)
		{
			matrixA[row][column] = (matrixA[row][column]*mult);
			identityMatrix[row][column] = (identityMatrix[row][column]*mult);
		}
	}
	
	public void multByScalar(double mult, int row, int multiplyingRow)
	{
		for(int column = 0; column < COLS; column++)
		{
			matrixA[row][column] = ((mult*matrixA[multiplyingRow][column]) + matrixA[row][column]);
			identityMatrix[row][column] = ((mult*identityMatrix[multiplyingRow][column]) + identityMatrix[row][column]);
			
		}
	}
	
	public void modMatrix()
	{
		for(int x = 0; x < ROWS; x++)
		{
			for(int y = 0; y < COLS; y++)
			{
				identityMatrix[x][y] = ((identityMatrix[x][y])%getAlphaLength());
				matrixA[x][y] = ((matrixA[x][y])%getAlphaLength());
				
				// When modding negative numbers the result if negative and I need it to be positive
				if(identityMatrix[x][y] < 0)
				{
					// To fix this add the alphalength to get the appropriate positve #
					identityMatrix[x][y] = ((identityMatrix[x][y]) + getAlphaLength());

				}
				if(matrixA[x][y] < 0)
				{
					matrixA[x][y] = ((matrixA[x][y]) + getAlphaLength());
				}
			}
			
		
		}
	}
	
	public void swap(int row, int swapWith)
	{
		double tempA[] = new double[COLS];
		double tempIdent[] = new double[COLS];
			
		for(int j = 0; j < COLS; j++)
		{
			tempA[j] = matrixA[row][j];
			tempIdent[j] = identityMatrix[row][j];
		}
								
		// Perform Swap!
		for(int k = 0; k < COLS; k++)
		{
			matrixA[row][k] = matrixA[swapWith][k];
			identityMatrix[row][k] = identityMatrix[swapWith][k];
		}
					
		for(int m = 0; m < COLS; m++)
		{
			matrixA[swapWith][m] = tempA[m];
			identityMatrix[swapWith][m] = tempIdent[m];
		}
					
	
	}
	
	// InverseMatrix multiplication to get original message
	public void inverseMatrixMultiplication()
	{
		// Repeat the matrix multiplication for every group
		for(int i = 0; i < groupsNeeded; i++)
		{
			double[] tempVector = new double[ROWS];

			// Prep the vector for multiplying!
			// Add the first three values in the array list to a temp vector
			// This way multiplying will be easier.
			for(int j = 0; j < ROWS; j++)
			{
				tempVector[j] = beforeDecryption.get(j); 
			}
			
			
			// Delete the first three values from the array list, backwards of course,
			// this way when the loop starts over the next three values will be added to the temp vector
			for(int m = (ROWS - 1); m >= 0; m--)
			{
				beforeDecryption.remove(m);
			}
	
			// This is where the magic happens.
			for(int k = 0; k < ROWS; k++)
			{
				double tempValue = 0;

				for(int c = 0; c < COLS; c++)
				{
					tempValue = tempValue + (identityMatrix[k][c]*tempVector[c]);

				}
				
				// Modding is required to get the large number produced by matrix mult.
				// to be equivalent to an index.
				tempValue = tempValue%getAlphaLength();
				
				// Add the encrypted value to the array list
				afterDecryption.add(tempValue);
			}
		}
		
	}
	
	
	public String printIdentityMatrix()
	{

		String matrix = "";
		
		for(int x = 0; x < ROWS; x++)
		{
			for(int y = 0; y < COLS; y++)
			{
				
				matrix = matrix + "[" + identityMatrix[x][y] + "]";
				
			}
			
			matrix = matrix + "\n";
		}
		
		return matrix;
		
	}

	public String printDecryptedMessage()
	{
		for(int i = 0; i < afterDecryption.size(); i++)
		{
			// Convert the values to their appropriate characters
			String findChar = returnCharacter(afterDecryption.get(i));

			decryptedMessage = decryptedMessage + findChar;
		}
		
		//Additional chars to be deleted
		if(extraChars != 0)
		{
			for(int delete = 0; delete < extraChars; delete++)
			{
				String temp = decryptedMessage.substring(0, (decryptedMessage.length() - 1));
				decryptedMessage = temp;
			}
				
		}

	
		return decryptedMessage;
	}
	
	public double[][] getInverse()
	{
		return identityMatrix;
	}
	
	public ArrayList<Double> getUncryptedVector()
	{
		return beforeDecryption;
	} 
	
	public ArrayList<Double> getDecryptedVector()
	{
		return afterDecryption;
	} 
	

}

