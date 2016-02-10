import java.util.*;

public class Factorial {

	public static void main(String[] args) {
		runTests();
	}

	static int computeNumZeroesAtEndOfFactorialOf(int n) {
		int numZeroes = 0;
		int powerOfFive = 5;
		while(powerOfFive <= n) { // executes O(log n) times
			int numMultiples = n/powerOfFive;
			numZeroes += numMultiples;
			powerOfFive *= 5;
		}
		return numZeroes;
	}
	// assuming O(log n) time for arithmetic operations, overall running time is O((log n)^2)

	private static void runTests() {
		new TestCase(0,0).runTest(); // 0! == 1
		new TestCase(1,0).runTest(); // 1! == 1
		new TestCase(2,0).runTest(); // 2! == 2
		new TestCase(3,0).runTest(); // 3! == 6
		new TestCase(4,0).runTest(); // 4! == 24
		new TestCase(5,1).runTest(); // 5! == 120
		new TestCase(6,1).runTest(); // 6! == 720
		new TestCase(7,1).runTest(); // 7! == 5040
		new TestCase(8,1).runTest(); // 8! == 40320
		new TestCase(9,1).runTest(); // 9! == 362880
		new TestCase(10,2).runTest(); // 10! == 3628800
		new TestCase(15,3).runTest();
		new TestCase(20,4).runTest();
		new TestCase(25,6).runTest();
		new TestCase(50,12).runTest();
		new TestCase(100,24).runTest();
		new TestCase(200,49).runTest();
		new TestCase(300,74).runTest();
		new TestCase(500,124).runTest();
	}

	static class TestCase {

		private int input;
		private int computedNumberOfZeroes;
		private int correctNumberOfZeroes;
		private boolean testPassed;

		TestCase(int input, int correctNumberOfZeroes) {
			this.input = input;
			this.correctNumberOfZeroes = correctNumberOfZeroes;
		}

		void runTest() {
			this.computedNumberOfZeroes = Factorial.computeNumZeroesAtEndOfFactorialOf(input);
			this.testPassed = (this.computedNumberOfZeroes == this.correctNumberOfZeroes);
			this.outputTestResult();
		}

		private void outputTestResult() {
			String output = "---\n";
			output += "input:          " + this.input + "\n";
			output += "your answer:    " + this.computedNumberOfZeroes + "\n";
			output += "correct answer: " + this.correctNumberOfZeroes + "\n";
			output += (this.testPassed ? "test passed!" : "test failed :(") + "\n";
			output += "---\n";

			System.out.println(output);
		}

	}

}
