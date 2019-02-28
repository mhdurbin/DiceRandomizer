package DiceRandomizer;

import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.Random;

/**
 * Next task is to improve upon the (Pseudo) Random number generator
 * May want to look at timing/efficiency of the program
 * May want to consider a GUI or some other interface
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
		long frac = (long)(range*randomVal.nextDouble());
		
		int randomNum = (int)(frac + startRange);
		
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
	// diceRolls will take the following variables
		// the type of dice being rolled
		// the number of dice being rolled
		// the number of times the dice set should be rolled
		// the string determining if the lowest die should be subtracted
	
	public static void diceRolls(int diceNum, int diceType, int iter, String dndStats) {
		int[] randomNumArray = new int[diceNum];
		int sum = 0;
		
		for (int i = 0; i < iter; i++) {
			for(int j = 0; j < diceNum; j++) {
				randomNumArray[j] = randomNumGen(diceType);
				sum += randomNumArray[j];
			}
			Arrays.sort(randomNumArray);
			if (dndStats.equals("No")) {
				System.out.println("Roll " + (i+1) + " " + Arrays.toString(randomNumArray));
			}else {
				sum = sum - randomNumArray[0];
				System.out.print("Roll " + (i+1) + " " + Arrays.toString(randomNumArray));
				System.out.println(" : Sum of Roll " + (i+1) + " is " + sum);
			}
			sum = 0;		
		}
		
		//JOptionPane.showMessageDialog(null, Arrays.toString(randomNumArray));
	}
	
	
	
	public static void main(String[] args) {
		
		// Declaration of variables
		// diceNum will indicate the number of dice that need to be rolled
		// diceType will indicate the type of dice to be rolled i.e. d4, d6, etc
		// num is to get the integer value from the String input
		// numberOfDice and typeOfDice are the String inputs to get diceNum and diceType
		// numberOfRolls are the String inputs to determine how many times the set is being rolled
		// dndStats is to do determine if a die should be subtracted
		
		int diceNum = 0;
		int diceType = 0;
		int iterations = 0;
		int num = 0;
		String numString = null;
		
		String dndStats = JOptionPane.showInputDialog(null, "Do you want to roll for DND stats (4d6 - lowest)? (Yes or No)", JOptionPane.QUESTION_MESSAGE);
		
		if (dndStats == null) {
			JOptionPane.showMessageDialog(null, "The program has been cancelled.");
			System.exit(0);
		}
		
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
		
		String numberOfRolls = JOptionPane.showInputDialog(null, "How many times would you like the set of dice to be rolled?", JOptionPane.QUESTION_MESSAGE);
		
		if (numberOfRolls == null) {
			JOptionPane.showMessageDialog(null, "The program has been cancelled.");
			System.exit(0);
		}
		
		iterations = Integer.parseInt(numberOfRolls);
		
		// The if statement is to determine the size of the dice being used
		
		if(typeOfDice.length() == 2 && typeOfDice.charAt(0) == 'd') {
			numString = typeOfDice.substring(1);
			num = Integer.parseInt(numString);
			diceType = num;
		}else if(typeOfDice.length() > 2) {
			numString = typeOfDice.substring(1, typeOfDice.length());
			num = Integer.parseInt(numString);
			diceType = num;
		}else {
			JOptionPane.showMessageDialog(null, "Please provide the type of dice in the 'd#' format");
			typeOfDice = JOptionPane.showInputDialog(null, "What type of dice would you like rolled? (d4 = 4, d6 = 6, etc)", JOptionPane.QUESTION_MESSAGE);
			num = Integer.parseInt(typeOfDice.substring(1, typeOfDice.length()));
			diceType = num;
		}
		
		System.out.println("Below are your results for rolling " + diceNum + typeOfDice + " dice " + iterations + " times!");
		diceRolls(diceNum, diceType, iterations, dndStats);
		
		// Used for troubleshooting purposes
//		System.out.println("Number of Dice String: " + numberOfDice);
//		System.out.println("Number of Dice Int: " + diceNum);
//		System.out.println("Type of Dice String: " + typeOfDice);
//		System.out.println("Type of Dice Int: " + diceType);
	}
}
