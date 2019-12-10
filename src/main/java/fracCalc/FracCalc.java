/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		String str = "";
		while (!str.equals("quit")) {
			System.out.print("Please enter your equation: ");
			str = userInput.nextLine();
			if (!str.equals("quit")) {
				System.out.println(produceAnswer(str));
			}

		}
		userInput.close();

	}

	// TODO: Read the input from the user and call produceAnswer with an equation

	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"
	public static String produceAnswer(String str) {
		// TODO: Implement this function to produce the solution to the input
		int firstSpace = str.indexOf(" "); // find the index of the first space
		String firstOperand = str.substring(0, firstSpace); // substring of beginning to space before operator

		String firstString = str.substring(firstSpace + 1, str.length());
		int secondSpace = firstString.indexOf(" "); // find the index of the second space
		String firstOperator = firstString.substring(0, secondSpace);

		String secondString = firstString.substring(secondSpace, firstString.length());
		String secondOperand = secondString.substring(secondSpace, secondString.length()); // substring of space after
																							// operator to the end

		// Multiple operations
		while (secondOperand.indexOf(" ") > 0) {
			int thirdSpace = secondOperand.indexOf(" ");
			String value = secondOperand.substring(0, thirdSpace);
			String thirdString = secondOperand.substring(thirdSpace + 1, secondOperand.length());
			int fourthSpace = thirdString.indexOf(" ");
			String secondOperator = thirdString.substring(fourthSpace - 1, fourthSpace);
			secondOperand = thirdString.substring(fourthSpace + 1, thirdString.length());
			String newEquation = firstOperand + " " + firstOperator + " " + value;
			firstOperand = produceAnswer(newEquation);
			firstOperator = secondOperator;
		}

		// Parsing fractions- First Operand
		String firstWhole = firstOperand; // hi_
		String firstNum = "";
		String firstDenom = "";
		int firstSlash = firstOperand.indexOf("/");

		if (firstSlash > 0) {
			int firstUnderscore = firstOperand.indexOf("_");
			if (firstUnderscore > 0) {
				firstWhole = firstOperand.substring(0, firstUnderscore);
				firstNum = firstOperand.substring(firstUnderscore + 1, firstSlash);
				firstDenom = firstOperand.substring(firstSlash + 1, firstOperand.length());
			} else {
				firstWhole = "0";
				firstNum = firstOperand.substring(firstUnderscore + 1, firstSlash);
				firstDenom = firstOperand.substring(firstSlash + 1, firstOperand.length());
			}

		} else {
			firstNum = "0";
			firstDenom = "1";
		}

		// Parsing fractions- Second Operand
		String secondWhole = secondOperand;
		String secondNum = "";
		String secondDenom = "";
		int secondSlash = secondOperand.indexOf("/");

		if (secondSlash > 0) {
			int secondUnderscore = secondOperand.indexOf("_");
			if (secondUnderscore > 0) {
				secondWhole = secondOperand.substring(0, secondUnderscore);
				secondNum = secondOperand.substring(secondUnderscore + 1, secondSlash);
				secondDenom = secondOperand.substring(secondSlash + 1, secondOperand.length());
			} else {
				secondWhole = "0";
				secondNum = secondOperand.substring(secondUnderscore + 1, secondSlash);
				secondDenom = secondOperand.substring(secondSlash + 1, secondOperand.length());
			}

		} else {
			secondNum = "0";
			secondDenom = "1";
		}
		// this is changing string into integers.
		int intFirstWhole = Integer.parseInt(firstWhole);
		int intFirstNum = Integer.parseInt(firstNum);
		int intFirstDenom = Integer.parseInt(firstDenom);

		int intSecondWhole = Integer.parseInt(secondWhole);
		int intSecondNum = Integer.parseInt(secondNum);
		int intSecondDenom = Integer.parseInt(secondDenom);

		// converts to an improper fraction
		intFirstNum += intFirstDenom * Math.abs(intFirstWhole);
		if (intFirstWhole < 0) {
			intFirstNum *= -1;
		}

		intSecondNum += intSecondDenom * Math.abs(intSecondWhole);
		if (intSecondWhole < 0) {
			intSecondNum *= -1;

		}

		int intFinalWhole = 0;
		int intFinalNum = 0;
		int intFinalDenom = 0;

		// invalid if user inputs 0 as denom
		if (intFirstDenom == 0 || intSecondDenom == 0) {
			return "Invalid input";

		}
		// if user inputs wrong syntax, invalid...
		if (firstOperator.length() > 1) {
			return "Invalid input";
		}
		// add.calculation
		if (firstOperator.equals("+")) {
			intFirstNum *= intSecondDenom;
			intSecondNum *= intFirstDenom;
			intFinalNum = intFirstNum + intSecondNum;
			intFinalDenom = intFirstDenom * intSecondDenom;

		}
		// subt.calculation
		if (firstOperator.equals("-")) {
			intFirstNum *= intSecondDenom;
			intSecondNum *= intFirstDenom;
			intFinalNum = intFirstNum - intSecondNum;
			intFinalDenom = intFirstDenom * intSecondDenom;
		}
		// mult.calculation
		if (firstOperator.equals("*")) {
			intFinalNum = intFirstNum * intSecondNum;
			intFinalDenom = intFirstDenom * intSecondDenom;
			if (intFirstNum == 0 || intSecondNum == 0) {
				return 0 + "";
			}
		}

		// div. calculation
		if (firstOperator.equals("/")) {
			intFinalNum = intFirstNum * intSecondDenom;
			intFinalDenom = intFirstDenom * intSecondNum;
		}

		// make num negative instead of denom
		if (intFinalDenom < 0 && intFinalNum > 0) {
			intFinalDenom *= -1;
			intFinalNum *= -1;
		}

		// convert to a mixed fraction if num is positive.
		while (intFinalNum / intFinalDenom >= 1) {
			intFinalNum -= intFinalDenom;
			intFinalWhole += 1;
		}

		// convert to a mixed fraction if num is negative.
		while (intFinalNum / intFinalDenom <= -1) {
			intFinalNum += intFinalDenom;
			intFinalWhole -= 1;
		}

		// remove the sign from num and denom if there is whole num.
		if (intFinalWhole != 0) {
			intFinalNum = Math.abs(intFinalNum);
			intFinalDenom = Math.abs(intFinalDenom);

		}

		// reduce fraction
		int gcd = 1;
		for (int j = 1; j <= Math.abs(intFinalNum) && j <= Math.abs(intFinalDenom); j++) {
			if (intFinalNum % j == 0 && intFinalDenom % j == 0)
				gcd = j;
		}
		intFinalNum /= gcd;
		intFinalDenom /= gcd;

		// final output!
		if (intFinalWhole == 0) {
			if (intFinalNum == 0) {
				return "0";
			} else {
				return intFinalNum + "/" + intFinalDenom;
			}
		} else if (intFinalNum == 0 || intFinalDenom == 1) {
			return intFinalWhole + "";

		} else {
			return intFinalWhole + "_" + intFinalNum + "/" + intFinalDenom;
		}

	}

}
// TODO: Fill in the space below with any helper methods that you think you will
// need
