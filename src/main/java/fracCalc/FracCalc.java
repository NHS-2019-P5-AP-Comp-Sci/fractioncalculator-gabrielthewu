/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		System.out.print("Please enter your fraction equation: ");
		String str = userInput.nextLine();
		while (!str.equals("quit")) {
			System.out.println(produceAnswer(str));
			System.out.print("Enter another fraction problem(type quit if you wish to exit): ");
			str = userInput.nextLine();
		}
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

		String firstOperand = "";
		String operator = "";
		String secondOperand = "";
		int i = 0;
		while (firstOperand == "") {
			if (str.charAt(i) == (' ')) {
				firstOperand = str.substring(0, i);
				operator = str.substring(i + 1, i + 2);
				secondOperand = str.substring(i + 3, str.length());

			} else {
				i++;

			}
		}

		String secondOperandWhole = findWhole(secondOperand);
		String secondOperandNum = findNum(secondOperand);
		String secondOperandDenom = findDenom(secondOperand);

		String chk2Answer = "whole:" + secondOperandWhole + " numerator:" + secondOperandNum + " denominator:"
				+ secondOperandDenom;

		return chk2Answer;

		// TODO: Fill in the space below with any helper methods that you think you will
		// need
	}

	public static String findWhole(String str) {
		if (str.contains("_")) {
			return str.substring(0, str.indexOf('_'));
		} else if (str.contains("/")) {
			return "0";
		} else
			return str;
	}

	public static String findNum(String str) {
		if (str.contains("_")) {
			return str.substring(str.indexOf('_') + 1, str.indexOf('/'));
		} else if (str.contains("/")) {
			return str.substring(0, str.indexOf('/'));

		} else {
			return "0";
		}
	}

	public static String findDenom(String str) {
		if (str.contains("/")) {
			return str.substring(str.indexOf("/") + 1);
		} else {
			return "1";
		}
	}
}
