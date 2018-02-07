/*
Program written by Joshua Croft
Winter 2018 Trent University
COIS 2240H Prof: Alaadin Addas
Student ID: 0584983
Description: 	This program compares the execution time of calculating and printing the
				Fibonacci numbers with iteration(loops) vs. recursion.
				The program accepts user input to determine the start and end positions
				of which to sequentially print the Fibonacci series.
				The program uses nested loops/recursion in order to print out the series in the console.
*/
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// Initialize variables.
		long startTime, endTime, elapsedTimeRec, elapsedTimeLoop;
		int startPos = 0, endPos = 0;
		Scanner reader = new Scanner(System.in);
		String input;
		String invalidInput = "Your input was invalid, please try again.";
		Boolean successful = false;
		
		// User input.
		System.out.println("This program prints Fibonacci numbers given a start position and an end position.");
		// Get start position integer. 
		while(!successful) {
		System.out.println("Please enter the start postition (integer): ");
		input = reader.nextLine();
		try {	// Error checking. 
			startPos = Integer.parseInt(input);
			successful = true;
			} catch(Exception e) {
				System.out.println(invalidInput);
			}
		}
		successful = false;
		// Get end position integer.
		while(!successful) {
			System.out.println("Please enter the last postition (integer): ");
			input = reader.nextLine();
			try {	// Error checking. 
				endPos = Integer.parseInt(input);
				successful = true;
			} catch(Exception e) {
					System.out.println(invalidInput);
			}
		}
		
		// Run recursive Fibonacci series method. Get elapsed time. Output results.
		startTime = System.nanoTime();
		fiboSeriesRec(startPos, endPos);
		endTime = System.nanoTime();
		elapsedTimeRec = endTime - startTime;
		System.out.println(" > The elapsed time of nested recursions is: " + ((float)elapsedTimeRec / 1000000) + " milliseconds.");
		
		// Run iterative Fibonacci series method. Get elapsed time. Output results.
		startTime = System.nanoTime();
		fiboSeriesLoop(startPos, endPos);
		endTime = System.nanoTime();
		elapsedTimeLoop = endTime - startTime;
		System.out.println(" > The elapsed time of nested loops is: " + ((float)elapsedTimeLoop / 1000000) + " milliseconds.");
		
		// Compare elapsed times and output time ratio.
		if (elapsedTimeRec > elapsedTimeLoop) {
			System.out.println(" > The iterative method was faster than the recursive method by a factor of " + outputTimeFactor(elapsedTimeRec,elapsedTimeLoop) );
		} else if (elapsedTimeRec < elapsedTimeLoop) {
			System.out.println(" > The recursive method was faster than the iterative method by a factor of " + outputTimeFactor(elapsedTimeLoop, elapsedTimeRec));
		} else { // elapsedTimeRec == elapsedTimeLoop
			System.out.println(" > The recursive method and the iterative method took the same amount of time at " + (elapsedTimeLoop / 1000000) + " milliseconds." );
		}
	}
	
	// Method Name:	fiboSeriesRec
	// Parameters:	startPos: The starting position to print within the Fibonacci series.
	//				lastPos: The last inclusive value to print from the Fibonacci series.
	// Behaviour: 	Prints to the console all Fibonacci numbers from the given position
	// 				(starting at 0) to and including the ceiling position.
	//				Uses recursion to call fiboAtPosRec, printing each integer returned.
	public static void fiboSeriesRec(Integer startPos, Integer lastPos) {
		if (startPos <= lastPos) {
			System.out.println(fiboAtPosRec(startPos));
			fiboSeriesRec(startPos + 1, lastPos);
		}
	}
	
	// Method Name:	fiboAtPosRec
	// Parameters:	pos: The position of the integer within the Fibonacci series to return.
	// Behaviour: 	Returns the integer at the given position of the Fibonacci Series.
	//				Uses recursion to call itself to return the specific Fibonacci integer.
	//				Negative positions will return 0.
	public static int fiboAtPosRec(Integer pos) {
		if (pos < 0)
			return 0;
		if ((pos == 0) || (pos == 1))
			return pos;
		return fiboAtPosRec(pos - 1) + fiboAtPosRec(pos - 2);
	}
	
	// Method Name:	fiboSeriesLoop
	// Parameters:	startPos: The starting position to print within the Fibonacci series.
	//				lastPos: The last inclusive value to print from the Fibonacci series.
	// Behaviour: 	Prints to the console all Fibonacci numbers from the given position
	// 				(starting at 0) to and including the ceiling position.
	//				Uses a ForLoop to call fiboAtPosLoop, printing each integer returned.
	public static void fiboSeriesLoop(Integer startPos, Integer lastPos) {
		int i;
		for(i = startPos; i <= lastPos; i++){
			System.out.println(fiboAtPosLoop(i));
		}
	}
	
	// Method Name:	fiboAtPosLoop
	// Parameters:	pos: The position of the integer within the Fibonacci series to return.
	// Behaviour: 	Returns the integer at the given position of the Fibonacci Series.
	//				Uses iteration within a loop to return the specific Fibonacci integer.
	//				Negative positions will return 0.
	public static int fiboAtPosLoop(Integer pos) {
		int fiboInteger = 0;
		int currentPosSubTwo = 0;
		int currentPosSubOne = 1;
		if (pos <= currentPosSubTwo) {
			return currentPosSubTwo;
		}
		if (pos == currentPosSubOne) {
			return currentPosSubOne;
		}
		int i;	// 2 is the next position after the required base cases (0 an 1).
		for (i = 2; i <= pos; i++) {
			fiboInteger = currentPosSubTwo + currentPosSubOne;	// Get current value at index.
			currentPosSubTwo = currentPosSubOne;	// Increment values within series to
			currentPosSubOne = fiboInteger;			// setup for next potential iteration. 
		}
		return fiboInteger;
	}
	
	// Method Name: outputTimeFactor
	// Parameters: 	larger, smaller
	// Behaviour:	Divides the larger by the smaller to return the factor (double) by
	//				which the larger is greater than the smaller.
	public static double outputTimeFactor(long larger, long smaller) {
		return (double)larger / smaller;
	}
}