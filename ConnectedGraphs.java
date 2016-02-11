import java.util.*;

public class ConnectedGraphs {

	static int computeNumConnectedGraphsWithVerticesEdges(int numVertices, int numEdges) {

		int maxNumEdges = (numVertices * (numVertices - 1)) / 2;
		boolean invalidParameters = numVertices < 0 || numEdges < 0 || numEdges > maxNumEdges;
		if( invalidParameters ) {
			return 0;
		}

		return 0;
	}

	private static TestSuite buildSuite() {
		TestSuite ts = new TestSuite();
		ts.addToSuite(TestCase.buildTestCaseWithVerticesEdgesCorrectAnswer(0,0,1));
		ts.addToSuite(TestCase.buildTestCaseWithVerticesEdgesCorrectAnswer(1,0,1));
		ts.addToSuite(TestCase.buildTestCaseWithVerticesEdgesCorrectAnswer(1,1,0));

		return ts;
	}

	public static void main(String[] args) {
		TestSuite ts = buildSuite();
		ts.runTests();
	}

	static class TestSuite {

		private List<TestCase> testCases;
		private int numTestCases;
		private int numTestCasesPassed;

		public TestSuite() {
			this.testCases = new ArrayList<TestCase>();
			this.numTestCases = 0;
			this.numTestCasesPassed = 0;
		}

		void addToSuite(TestCase tc) {
			testCases.add(tc);
			++this.numTestCases;
		}

		void runTests() {
			for( TestCase tc : testCases ) {
				tc.runTest();
				if( tc.passes() ) {
					++this.numTestCasesPassed;
				}
			}
			outputSummary();
		}

		private void outputSummary() {
			int numTestCasesFailed = this.numTestCases - this.numTestCasesPassed;
			long percentPassed = Math.round((100.0 * this.numTestCasesPassed) / this.numTestCases);
			long percentFailed = 100 - percentPassed;

			String output = "*****\n";
			output += "number of test cases:        " + this.numTestCases + "\n";
			output += "number of test cases passed: " + this.numTestCasesPassed + " (" + percentPassed + "%)\n";
			output += "number of test cases failed: " + numTestCasesFailed + " (" + percentFailed + "%)\n";

			System.out.print(output);
		}

	}

	static class TestCase {

		private int numVertices;
		private int numEdges;
		private int correctAnswer;

		private int computedAnswer;
		private boolean testPassed;

		private TestCase() {}

		public static TestCase buildTestCaseWithVerticesEdgesCorrectAnswer(int numVertices, int numEdges,
			int correctAnswer) {

			TestCase tc = new TestCase();
			tc.numVertices = numVertices;
			tc.numEdges = numEdges;
			tc.correctAnswer = correctAnswer;
			return tc;
		}

		void runTest() {
			this.computedAnswer = ConnectedGraphs.computeNumConnectedGraphsWithVerticesEdges(numVertices, numEdges);
			this.testPassed = (this.computedAnswer == this.correctAnswer);
			this.outputResult();
		}

		private void outputResult() {
			String output = "*****\n";
			output += "numVertices:    " + this.numVertices + "\n";
			output += "numEdges:       " + this.numEdges + "\n";
			output += "--\n";
			output += "your answer:    " + this.computedAnswer + "\n";
			output += "correct answer: " + this.correctAnswer + "\n";
			output += (this.testPassed ? "test passed!" : "TEST FAILED :(") + "\n";

			System.out.print(output);
		}

		boolean passes() {
			return this.testPassed;
		}

	}

}
