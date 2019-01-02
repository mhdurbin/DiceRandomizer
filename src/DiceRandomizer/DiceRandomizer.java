package DiceRandomizer;

import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.Random;

/**
 * Next task is to improve upon the (Pseudo) Random number generator
 * May want to look at timing/efficiency of the program
 * May want to consider a GUI or some other interface
 * May want to set up multiple dice type rolls
 * Another possible task is to implement this in a situation of inheritance for a DND Character sheet
 */

/**
 * @author Matthew Durbin
 *
 */
public class DiceRandomizer {
	
	// A private method to generate random numbers based on a range determined by the diceType
	
	private static int randomNumRange(int startRange, int endRange, Random randomVal) {
		
		// Error in case of illegal start number
		if(startRange > endRange) {
			throw new IllegalArgumentException("Start of Range cannot exceed End of Range.");
		}
		// Gets the range, using long to avoid overflow problems
		long range = (long)endRange - (long)startRange + 1;
		// Computes a fraction of the range
		long fraction = (long)(range*randomVal.nextDouble());
		
		int randomNum = (int)(fraction + startRange);
		
		return randomNum;
	}
	
	// This will be called to generate random numbers for the dice rolls
	// This will call another method to verify the number is within a specific range based on diceType
	
	private static int randomNumGen(int diceType) {
		final int start = 1;
		int returnVal = 0;
		Random randomNum = new Random();
		returnVal = randomNumRange(start, diceType, randomNum);
		return returnVal;
	}
	
	// This will be called to create an array of dice rolls
	// It will call a random number generator to get the random dice rolls
	// diceRolls will take in the type of dice being rolled and the number of dice being rolled
	
	public static void diceRolls(int diceNum, int diceType) {
		int[] randomNumArray = new int[diceNum];
		
		for(int i = 0; i < diceNum; i++) {
			randomNumArray[i] = randomNumGen(diceType);
		}
		
		System.out.println(Arrays.toString(randomNumArray));
		//JOptionPane.showMessageDialog(null, Arrays.toString(randomNumArray));
	}
	
	
	
	public static void main(String[] args) {
		
		// Declaration of variables
		// diceNum will indicate the number of dice that need to be rolled
		// diceType will indicate the type of dice to be rolled i.e. d4, d6, etc
		// radix is to get the integer value from the String input
		// numberOfDice and typeOfDice are the String inputs to get diceNum and diceType
		
		int diceNum = 0;
		int diceType = 0;
		int radix = 0;
		String radixString = null;
		
		String numberOfDice = JOptionPane.showInputDialog(null, "How many dice would you like rolled?", JOptionPane.QUESTION_MESSAGE);
		
		if (numberOfDice == null) {
			JOptionPane.showMessageDialog(null, "The program has been cancelled.");
			System.exit(0);
		}
		
		diceNum = Integer.parseInt(numberOfDice);
		
		String typeOfDice = JOptionPane.showInputDialog(null, "What type of dice would you like rolled? (d4 = 4, d6 = 6, etc)", JOptionPane.QUESTION_MESSAGE);
		
		if (typeOfDice == null) {
			JOptionPane.showMessageDialog(null, "The program has been cancelled.");
			System.exit(0);
		}
		
		// The if statement is to determine the size of the dice being used
		
		if(typeOfDice.length() == 2 && typeOfDice.charAt(0) == 'd') {
			radixString = typeOfDice.substring(1);
			radix = Integer.parseInt(radixString);
			diceType = radix;
		}else if(typeOfDice.length() > 2) {
			radixString = typeOfDice.substring(1, typeOfDice.length());
			radix = Integer.parseInt(radixString);
			diceType = radix;
		}else {
			JOptionPane.showMessageDialog(null, "Please provide the type of dice in the 'd#' format");
			typeOfDice = JOptionPane.showInputDialog(null, "What type of dice would you like rolled? (d4 = 4, d6 = 6, etc)", JOptionPane.QUESTION_MESSAGE);
			radix = Integer.parseInt(typeOfDice.substring(1, typeOfDice.length()));
			diceType = radix;
		}
		
		System.out.println("Below are your results for rolling " + diceNum + typeOfDice + " dice!");
		diceRolls(diceNum, diceType);
		
		// Used for troubleshooting purposes
//		System.out.println("Number of Dice String: " + numberOfDice);
//		System.out.println("Number of Dice Int: " + diceNum);
//		System.out.println("Type of Dice String: " + typeOfDice);
//		System.out.println("Type of Dice Int: " + diceType);
	}
}
