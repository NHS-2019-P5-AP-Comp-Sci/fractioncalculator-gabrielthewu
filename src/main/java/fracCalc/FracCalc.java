/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Please enter your fraction: ");
		String input = userInput.nextLine();
		System.out.println(produceAnswer(input));
		
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
	public static String produceAnswer(String input){
		// TODO: Implement this function to produce the solution to the input

		String firstOperand = "";
		String operator = "";
		String secondOperand = "";
		int i = 0;
		while (firstOperand == "") {
			if (input.charAt(i) == (' ')) {
				firstOperand = input.substring(0, i);
				operator = input.substring(i + 1, i + 2);
				secondOperand = input.substring(i + 3, input.length());

			} else {
				i++;
			}
		}
			return secondOperand;
		

		// TODO: Fill in the space below with any helper methods that you think you will
		// need
	}
}
